
import java.util.ArrayList;

import java.util.List;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class NumberVerificationHandler {
		public static String generateCode(String phoneNumber){
			
			//generates the verification code and send an SMS to the given number.
			
			String verificationCode;
			String extractedNum=phoneNumber.substring(phoneNumber.length()-10);
			String res=null;
	    			extractedNum=phoneNumber.substring(phoneNumber.length()-10);
	    			System.out.println(extractedNum);
	    			int length=extractedNum.length();
	    			System.out.println(extractedNum.length());
	    			if(!extractedNum.substring(length-2,length).equals("00")){
	    				System.out.println(extractedNum.substring(length-2,length));
	    				res=extractedNum.substring(length-2,length);
	    				System.out.println(res);
	    				}
	    				else{
	    					res=extractedNum.substring(length-10,length-8);
	    		}
	    				
			int res2=((98-((Integer.parseInt(res))*100) % 97))%97;
			StringBuilder sb=new StringBuilder();
			sb.append(res);
			sb.append(res2); 
			verificationCode=sb.toString();
			System.out.println(verificationCode);
			return verificationCode;	
	}

	public static Message generateSms(String phoneNumber,String verificationCode) throws TwilioRestException{
			final String ACCOUNT_SID = "ACa7d696a0601d185be1af37d0eba0ae56";
		 final String AUTH_TOKEN = "a3377bd895f8445e28e392650d8ec628";
				
				//generates the verification code and send an SMS to the given number.
				TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
				Account account = client.getAccount();
				MessageFactory messageFactory = account.getMessageFactory();
				List<NameValuePair> params = new ArrayList<NameValuePair>();	
		        params.add(new BasicNameValuePair("To",phoneNumber )); // Replace with a valid phone number for your account.
		        params.add(new BasicNameValuePair("From", "+16504379909")); // Replace with a valid phone number for your account.
		        params.add(new BasicNameValuePair("Body", "Your Youplus Verification code is "+verificationCode));
		        Message message=messageFactory.create(params);
		        System.out.println(message.getStatus());
		        return message;
	}
	
	public static void main(String[] args){
		String code=generateCode("+918105505579");
		try {
			generateSms("+918105505579",code);
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}
	}
}
