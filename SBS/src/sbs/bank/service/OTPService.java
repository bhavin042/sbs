package sbs.bank.service;

public interface OTPService{
	public String generateOTP() throws Exception;
	public int persistOTP(String id, String otp) throws Exception;
	public boolean validateOTP(String id, String otp) throws Exception;
	public int changePassword(String uname, String password) throws Exception;
	public long getOtpTimeDiff(String uname) throws Exception;
}