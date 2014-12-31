package sbs.bank.DTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Account_User")
public class Account {
	@Id
	private String accountNum;
	private String userName;
	private double balance;
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getUserName() {
		return userName;
	}
	/*public void setUserName(String userName) {
		this.userName = userName;
	}*/
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
		
	}
	


}
