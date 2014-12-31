package sbs.bank.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;


@Entity
@Table(name="AuthUser")
public class AuthUser {
	
	
		@Id 
		private String userId;		
	    private double updatedBalance;
		private String transactiontype;
		private int authval;
		
		
		
		@Override
		public String toString() {
			return "AuthUser [userId=" + userId + ", updatedBalance=" + updatedBalance +", transactiontype=" + transactiontype
					+ ",  authval=" + authval
					+  "]";
		}
	
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public double getUpdatedBalance() {
			return updatedBalance;
		}
		public void setUpdatedBalance(double updatedBalance) {
			this.updatedBalance = updatedBalance;
		}
		public String getTransactiontype() {
			return transactiontype;
		}
		public void setTransactiontype(String firstName) {
			this.transactiontype = firstName;
		}
		
		public int getAuthval() {
			return authval;
		}
		public void setAuthVal(int authval) {
			this.authval = authval;
		}
	}



