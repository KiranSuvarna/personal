package com.msg91.endpoint;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.msg91.Msg91;

@Path("/gen")
public class Endpoint {
	@Path("sms")
	@POST
	public static String generateSms(@FormParam("phoneNumber") String phoneNumber){
		String res = Msg91.msg91(phoneNumber);
		
		return String.format("%s", res);
		
	}

}
