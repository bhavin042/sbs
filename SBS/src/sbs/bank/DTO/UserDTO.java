package sbs.bank.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserDTO {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	
	@Column(name="accountNonExpired")
	private int accountNonExpired;
	
	@Column(name="accountNonLocked")
	private int accountNonLocked;
	
	@Column(name="credentialsNonExpired")
	private int credentialsNonExpired;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="otpTime")
	private String otpTime;
	
	public String getOtpTime() {
		return otpTime;
	}

	public void setOtpTime(String otpTime) {
		this.otpTime = otpTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public int getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(int credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
