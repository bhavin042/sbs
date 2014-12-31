package sbs.bank.DTO;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="Payment_Request")
public class PaymentRequest {
	@Id 
	@GeneratedValue
	private Integer reqID;	
	private String fromID;
	private String toID;
	private double amount;
	private String description;
	private int paymentDue;
	
	public Integer getReqID() {
		return reqID;
	}
	public void setReqID(Integer reqID) {
		this.reqID = reqID;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getToID() {
		return toID;
	}
	public void setToID(String toID) {
		this.toID = toID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int isPaymentDue() {
		return paymentDue;
	}
	public void setPaymentDue(int paymentDue) {
		this.paymentDue = paymentDue;
	}
	
	

}
