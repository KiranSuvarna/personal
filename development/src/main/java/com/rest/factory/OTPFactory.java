package com.rest.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class OTPFactory {
	private static final String AUTHKEY_MSG91 = "dRVK8X195Xa9PJ5hYvt0--C20X2ot2yKn2B_3Ukk8ivFN-HlARjLn-cGEsv2hZc9VBlyWSm2A9TaQDLzj2gejpE6pseW7TutuQ5KvN80nRm_AoTpdm6fh2ENB2A9jYgtoRRRPAL8zSbZKY9il0iBOw==";
	public static String SENDER_ID = "";
	public static String BASE_URL = "https://sendotp.msg91.com/api";

	// public static String sendSms(String phoneNumber, String message) {
	// StringBuffer response = new StringBuffer();
	// StringBuilder sb = new StringBuilder(BASE_URL);
	// String encoded_message = URLEncoder.encode(message);
	// sb.append("authkey=" + AUTHKEY_MSG91);
	// sb.append("&mobiles=" + phoneNumber);
	// sb.append("&message=" + encoded_message);
	// sb.append("&sender=" + SENDER_ID);
	// sb.append("&route=4");
	// sb.append("&response=json");
	//
	// // BASE_URL = sb.toString();
	// try {
	// URL url = new URL(sb.toString());
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// conn.setRequestMethod("GET");
	// conn.setDoOutput(true);
	// BufferedReader br = new BufferedReader(new
	// InputStreamReader(conn.getInputStream()));
	//
	// String inputLine;
	// while ((inputLine = br.readLine()) != null)
	// response.append(inputLine);
	// br.close();
	// conn.disconnect();
	// } catch (IOException e) {
	// response.append(e);
	// e.printStackTrace();
	// }
	// return response.toString();
	// }

	public static String sendSms(String countryCode, String mobileNumber) {
		try {
			Client client = Client.create();
			String Url = BASE_URL + "/generateOTP";
			WebResource webResource = client.resource(Url);
			HashMap<String, String> requestBodyMap = new HashMap();
			requestBodyMap.put("countryCode", countryCode);
			requestBodyMap.put("mobileNumber", mobileNumber);
			requestBodyMap.put("getGeneratedOTP", "true");
			JSONObject requestBodyJsonObject = new JSONObject(requestBodyMap);
			String input = requestBodyJsonObject.toString();

			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
					.header("application-Key", AUTHKEY_MSG91).post(ClientResponse.class, input);

			String output = response.getEntity(String.class);
			System.out.println(output);
			return output;
			// fetch your oneTimePassword and save it to session or db
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
