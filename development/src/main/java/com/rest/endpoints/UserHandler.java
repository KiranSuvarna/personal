package com.rest.endpoints;

import java.util.UUID;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response.Status;

import com.rest.dto.Users;
import com.rest.factory.UserFactory;

@Path("/user")
public class UserHandler {
	@POST
	@Path("create")
	public String setUsersData(@FormParam("userName") String userName, @FormParam("phoneNumber") String phoneNumber,
			@FormParam("eMail") String eMail, @FormParam("gender") String gender, @FormParam("age") String age,
			@FormParam("password") String password, @FormParam("deviceId") String deviceId,
			@FormParam("osType") String osType) {

		try {
			UserFactory userFactory = new UserFactory();
			UUID uuid = UUID.randomUUID();
			String userId = uuid.toString();
			Users userRes = userFactory.setUsersData(userId,userName, phoneNumber, eMail, gender, age, password, deviceId, osType);
			System.out.println("success " + userRes.getUserId());
			return String.format("{\"response_message\":\"%s\",\"status_code\":\"%s\",\"status\":\"%s\"}",
					"User Created with userId : " +userRes.getUserId()+"", 200, Status.OK);
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("failure " +Status.FORBIDDEN);
			return String.format("{\"response_message\":\"%s\",\"status_code\":\"%s\",\"status\":\"%s\"}",
					"Error Creating User", 403, Status.FORBIDDEN);
		}
	}
	public static void main(String[] args) {
		UserHandler handler = new UserHandler();
		handler.setUsersData("Kiran", "8105505579", "kiransuvarna19@gmail.com", "male", "23", "password123", "test_device_id_11", "Android");
	}
}
