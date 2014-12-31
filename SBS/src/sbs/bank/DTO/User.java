package sbs.bank.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

@Entity
@Table(name="USER_DTLS")
public class User {
	@Id
	private String userName;
	private String password;
	private String confirmPassword;	
	private String firstName;
	private String lastName;
	private String emailId;
	private String address;
	private String enabled;
	//private String contact;
	private String idType;
	private String idNo;
	//private String DOB;
	private int roleId;
	private String securityQues1;
	private String securityQues2;
	private String securityQues3;
	private String securityAns1;
	private String securityAns2;
	private String securityAns3;
	
	
	@Override
	public String toString() {
		return "User [userId=" + userName + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", address=" + address
				 + ", idType=" + idType + ", enabled=" + enabled + ", idNo="
				+ idNo + ", roleId=" + roleId
				+ ", securityQues1=" + securityQues1 + ", securityQues2="
				+ securityQues2 + ", securityQues3=" + securityQues3
				+ ", securityAns1=" + securityAns1 + ", securityAns2="
				+ securityAns2 + ", securityAns3=" + securityAns3 + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	/*public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}*/
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/*public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}*/
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getSecurityQues1() {
		return securityQues1;
	}
	public void setSecurityQues1(String securityQues1) {
		this.securityQues1 = securityQues1;
	}
	public String getSecurityQues2() {
		return securityQues2;
	}
	public void setSecurityQues2(String securityQues2) {
		this.securityQues2 = securityQues2;
	}
	public String getSecurityQues3() {
		return securityQues3;
	}
	public void setSecurityQues3(String securityQues3) {
		this.securityQues3 = securityQues3;
	}
	public String getSecurityAns1() {
		return securityAns1;
	}
	public void setSecurityAns1(String securityAns1) {
		this.securityAns1 = securityAns1;
	}
	public String getSecurityAns2() {
		return securityAns2;
	}
	public void setSecurityAns2(String securityAns2) {
		this.securityAns2 = securityAns2;
	}
	public String getSecurityAns3() {
		return securityAns3;
	}
	public void setSecurityAns3(String securityAns3) {
		this.securityAns3 = securityAns3;
	}
	
}
