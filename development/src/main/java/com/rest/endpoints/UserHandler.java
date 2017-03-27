package com.rest.endpoints;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.rest.dto.Users;
import com.rest.factory.SecurityFilterFactory;
import com.rest.factory.UserFactory;

@Path("/user")
public class UserHandler {

	@POST
	@Path("create")
	public String setUsersData(@FormParam("user_data") String userData) {

		try {
			JSONArray usersDataJson = new JSONArray(userData);
			JSONObject usersDataJsonObject = (JSONObject) usersDataJson.get(0);
			UserFactory userFactory = new UserFactory();
			UUID uuid = UUID.randomUUID();
			String userId = uuid.toString();
			String res = "";
			String userDetails = userFactory.getUserdetails(userId, usersDataJsonObject.getString("phoneNumber"));
			if (userDetails.equals("0")) {
				return String.format("{\"response_message\":\"%s\",\"status_code\":\"%s\",\"status\":\"%s\"}",
						"Phone Number already exists!! Use a different one.", 403, Status.FORBIDDEN);
			} else if (userDetails.equals("2")) {
				res = userFactory.setUsersData(userId, usersDataJsonObject.getString("userName"),
						usersDataJsonObject.getString("phoneNumber"), usersDataJsonObject.getString("eMail"),
						usersDataJsonObject.getString("gender"), usersDataJsonObject.getString("age"),
						usersDataJsonObject.getString("password"), usersDataJsonObject.getString("deviceId"),
						usersDataJsonObject.getString("osType"));
				userDetails = userFactory.getUserdetails(userId);
			}
			if (res.equals("1")) {
				return userDetails;
			} else {
				return String.format("{\"response_message\":\"%s\",\"status_code\":\"%s\",\"status\":\"%s\"}",
						"Error creating the User", 403, Status.FORBIDDEN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return String.format("{\"response_message\":\"%s\",\"status_code\":\"%s\",\"status\":\"%s\"}",
					"Error creating the User", 403, Status.FORBIDDEN);
		}
	}

	@POST
	// @Produces("application/json")
	// @Consumes("application/x-www-form-urlencoded")
	@Path("signin")
	public String authenticateUser(@FormParam("phone_number") String phoneNumber,
			@FormParam("password") String password) {
		SecurityFilterFactory securityFilterFactory = new SecurityFilterFactory();
		UserFactory userFactory = new UserFactory();
		try {
			String validPhone = securityFilterFactory.authenticate(phoneNumber, password);
			if (validPhone == null || validPhone.isEmpty()) {
				return "failure";
			} else if (validPhone.equals(password)) {
				String userId = userFactory.getUserId(phoneNumber);
				String userDetails = userFactory.getUserdetails(userId, phoneNumber);
				// String userId = userFactory.getUserId(phoneNumber);
				// JsonObject response = new JsonObject();
				// String token = SecurityFilterFactory.buildJWT(validPhone);
				// response.addProperty("token", token);
				// boolean tf = SecurityFilterFactory.validateJWT(token);
				// System.out.println(tf);
				return userDetails;
			} else {
				return "failure";
			}
		} catch (Exception ex) {
			return "failure";
		}
	}

	public static void main(String[] args) {
		UserHandler handler = new UserHandler();
		String i = "[{\"userName\":\"kiran\",\"phoneNumber\":\"07786868787979\",\"eMail\":\"dvrjguutspfb19@gmail.com\",\"gender\":\"male\",\"age\":\"23\",\"password\":\"password\",\"deviceId\":\"kirsdglsrgvjsnbvdnlboBP_device_id\",\"osType\":\"Android\"}]";
		System.out.println(handler.setUsersData(i));
		// System.out.println(handler.authenticateUser("800105505579", "test"));

	}
}
