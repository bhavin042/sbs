package sbs.bank.form;

public class ForgetPasswordFormBean {
	
	private String otp;
	private String password;
	private String emailId;
	private String cpassword;
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	private String userName;
	private String otpMessage;
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOtpMessage() {
		return otpMessage;
	}
	public void setOtpMessage(String otpMessage) {
		this.otpMessage = otpMessage;
	}	
	
}