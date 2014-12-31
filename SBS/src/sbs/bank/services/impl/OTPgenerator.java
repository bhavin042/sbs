package sbs.bank.services.impl;

import java.io.IOException;
import java.util.UUID;

/*

 * OneTimePasswordAlgorithm.java
 * OATH Initiative,
 * HOTP one-time password algorithm
 *


*/

public class OTPgenerator {
	
	public void emailOTP(String emailId, String otp) throws IOException{
		SendEmail mail = new SendEmail();
		mail.send(emailId, otp, "OTP");
		//System.out.println("Send PKI");
		//mail.send(emailId, null, "PKI");
	}
	
	public String generateOTP() {
		Long otp = UUID.randomUUID().getLeastSignificantBits();
		if(otp < 0) {
			otp = otp * -1;
		}
		return new String(otp.toString());
	}	
}
