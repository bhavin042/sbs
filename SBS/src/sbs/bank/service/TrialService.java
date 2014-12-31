package sbs.bank.service;
import java.util.List;
















import sbs.bank.DTO.Account;
import sbs.bank.DTO.AuthUser;
import sbs.bank.DTO.PaymentRequest;
import sbs.bank.DTO.TrialDTO;
import sbs.bank.DTO.User;

public interface TrialService {

	public void addContact(Integer id, String name);
	public void createProfile(User user) throws Exception;
	public List<TrialDTO> displayContact();
	public void UserAccount(Account acc) throws Exception;
	public void AddCredit(String id, double balance) throws Exception;
	//public void debit(String id, double balance);
	public void debit(String userName, double balance) throws Exception;
	public void transfer(String userName, String amtToId, double balance) throws Exception;
	public void submitRequest(PaymentRequest payReq) throws Exception;
	//public List<PaymentRequest> displayRequest(String userID);
	public List<PaymentRequest> displayRequest(String userId);
	public void submitRequestedPayment(String userName, String reqid) throws Exception;
	public List<User> displayProfile(String userName);
	public List<Account> displayAccountDetails(String userName);
	
	public List<User> displayUserDetails(String enableValue);
	public void addUser( String userID);
	public void deleteUser( String userID);
	
	//empoyee
	public void authTrans( String userID);
	public void unauthTrans( String userID);
	public List<AuthUser> authenticateUserdetails(String UserID);
	
	
	
}


