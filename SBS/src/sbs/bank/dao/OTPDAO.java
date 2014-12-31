package sbs.bank.dao;

public interface OTPDAO{
	public int persistOTP(String id, String otp) throws Exception;
	public String getOTP(String id) throws Exception;
	public int changePassword(String uname, String password) throws Exception;
	public long getOtpTimeDiff(String id) throws Exception;
	public String getEmailID(String id) throws Exception;
}