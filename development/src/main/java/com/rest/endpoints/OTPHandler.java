//package com.rest.endpoints;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Response.Status;
//
//import com.rest.factory.OTPFactory;
//
//@Path("gen")
//public class OTPHandler {
//	
//	@POST
//	@Path("otp")
//	public String getOTP(@FormParam("country_code") String countryCode, @FormParam("phone_number") String phoneNumber){
//		OTPFactory otpFactory = new OTPFactory();
//		otpFactory.sendSms(countryCode,phoneNumber);
//		return String.format("{\"response_message\":\"%s\",\"status_code\":\"%s\",\"status\":\"%s\"}",
//				"OTP : " +""+"", 200, Status.OK);
//	}
//	
//	public static void main(String[] args) {
//		OTPHandler otpHandler =  new OTPHandler();
//		otpHandler.getOTP("91", "9686981100");
//	}
//
//}
