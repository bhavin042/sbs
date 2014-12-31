package sbs.bank.dao;

import java.util.List;

import sbs.bank.DTO.Account;
import sbs.bank.DTO.AuthUser;
import sbs.bank.DTO.PaymentRequest;
import sbs.bank.DTO.TrialDTO;
import sbs.bank.DTO.User;

public interface TrialDAO {
	public void addContact(TrialDTO trialDTO);
	public List<TrialDTO> displayContact();
	public void createProfile(User user) throws Exception;
	public void UserAccount(Account acc) throws Exception;
	public void AddCredit(String id, double balance) throws Exception;
	public void debit(String id, double balance) throws Exception;
	//public void transfer(String userId, double amtToId, double balance);
	public void transfer(String userId, String amtToId, double balance) throws Exception;
	public void submitRequest(PaymentRequest payReq) throws Exception;
	//public List<PaymentRequest> displayRequest(String userID);
	public List<PaymentRequest> displayRequest(String userID);
	public void submitRequestedPayment(String userId, String reqid) throws Exception;
	public List<User> displayProfile(String userID);
	public List<Account> displayAccountDetails(String userId);
	
	public void addUser(String user);
	public void deleteUser(String user);
	public List<User> displayUserDetails(String enableValue);
	
	//employee
	public void authTrans(String user);
	public void unauthTrans(String user);
	public List<AuthUser> authenticateUserdetails(String userID);
}



