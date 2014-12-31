package sbs.bank.services.impl;

import java.util.List;

























import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import org.hibernate.Session; 
import org.hibernate.cfg.Configuration;


import org.hibernate.service.ServiceRegistryBuilder;

import sbs.bank.DTO.Account;
import sbs.bank.DTO.AuthUser;
import sbs.bank.DTO.PaymentRequest;
import sbs.bank.DTO.TrialDTO;
import sbs.bank.DTO.User;
import sbs.bank.dao.TrialDAO;
import sbs.bank.service.TrialService;
import sbs.bank.validations.formValidations;

@Service
public class TrialServiceImpl implements TrialService{
	
	@Autowired
	TrialDAO trialDAO;
	//
	private static  SessionFactory sessionFactory;
	
	@Transactional
	public void addContact(Integer id, String name){
		System.out.println("in service impl" + id+name);
		TrialDTO trial = new TrialDTO();
		trial.setId(id);
		trial.setName(name);
		trialDAO.addContact(trial);
	}

	@Transactional
	@Override
	public List<TrialDTO> displayContact() {
		return trialDAO.displayContact();
	}

	
	@Transactional
	    public void createProfile(User user) throws Exception{
		formValidations.specialCharacterValidation(user.getFirstName(), "First Name",20);
		formValidations.specialCharacterValidation(user.getUserName(), "User Name",12);
		formValidations.specialCharacterValidation(user.getPassword(), "Password",12);
		formValidations.specialCharacterValidation(user.getConfirmPassword(), "Confirm Password",12);
		
		formValidations.passwordValidation(user.getPassword(),user.getConfirmPassword(), "Password");
		formValidations.specialCharacterValidation(user.getLastName(), "Last Name",20);
		formValidations.specialCharacterValidation(user.getAddress(), "Address",100);
		formValidations.specialCharacterValidation(user.getIdNo(), "Identification Number",10);
		formValidations.specialCharacterValidation(user.getIdType(), "Identification Type",10);
		formValidations.mailValidation(user.getEmailId(), "E-mail ID");
		formValidations.specialCharacterValidation(user.getSecurityAns1(), "Security Answer 1",20);
		formValidations.specialCharacterValidation(user.getSecurityQues1(), "Security Question 1",50);
		formValidations.specialCharacterValidation(user.getSecurityQues2(), "Security Question 2",50);
		formValidations.specialCharacterValidation(user.getSecurityQues3(), "Security Question 3",50);
		formValidations.specialCharacterValidation(user.getSecurityAns2(), "Security Answer 2",20);
		formValidations.specialCharacterValidation(user.getSecurityAns3(), "Security Answer 3",20);
		(trialDAO).createProfile(user);
	}
	
	@Transactional
    public void UserAccount(Account acc) throws Exception{
	formValidations.specialCharacterValidation(acc.getAccountNum(), "Account Number",10);
	(trialDAO).UserAccount(acc);
}
	@Transactional
	public void AddCredit(String id, double balance) throws Exception
	{
		formValidations.specialCharacterValidation(id, "User Name",20);
		String bal = new Double(balance).toString();
		formValidations.balanceValidation(bal, "Balance");
		//formValidations.specialCharacterValidation(balance, "Balance",10);
		trialDAO.AddCredit(id,balance);
	}
	
	/*@Transactional
	public void debit(String id, double balance)
	{
		trialDAO.debit(id,balance);
	}*/
	
	@Transactional
	public void debit(String id, double balance) throws Exception
	{
		formValidations.specialCharacterValidation(id, "User Name",20);
		String bal = new Double(balance).toString();
		formValidations.balanceValidation(bal, "Balance");
		trialDAO.debit(id,balance);
	}
	
	@Transactional
	public void transfer(String userId, String amtToId, double balance) throws Exception
	{
		formValidations.specialCharacterValidation(userId, "User Name",20);
		formValidations.specialCharacterValidation(amtToId, "Destination ID",20);
		String bal = new Double(balance).toString();
		formValidations.balanceValidation(bal, "Balance");
		trialDAO.transfer(userId,amtToId,balance);
	}
	
	@Transactional
	public void submitRequest(PaymentRequest payReq) throws Exception
			{
		formValidations.specialCharacterValidation(payReq.getToID(), "Destination ID",20);
		formValidations.specialCharacterValidation(payReq.getFromID(), "User Name",20);
		formValidations.specialCharacterValidation(payReq.getDescription(), "Description",70);
		String bal = new Double(payReq.getAmount()).toString();
		formValidations.balanceValidation(bal, "Balance");
		
		trialDAO.submitRequest(payReq);
			}
	@Transactional
	public List<PaymentRequest> displayRequest(String userID)
	{
		return trialDAO.displayRequest(userID);
	}
	@Transactional
	public void submitRequestedPayment(String userId, String reqid) throws Exception
	{
		System.out.println("in service make payment");
		trialDAO.submitRequestedPayment(userId,reqid);
	}
	
	@Transactional
	public List<User> displayProfile(String userID) {
		return trialDAO.displayProfile(userID);
	}
	
	@Transactional
	public List<Account> displayAccountDetails(String userId)
	{System.out.println("In service");
		return trialDAO.displayAccountDetails(userId);	
	}
	
	@Transactional
	public void addUser(String userId){
		System.out.println("in service impl" + userId);
		
		trialDAO.addUser(userId);
	}
	@Transactional
	public void deleteUser(String userId){
		System.out.println("in service impl for delete" + userId);
		
		trialDAO.deleteUser(userId);
	}
	
	@Transactional
	public List<User> displayUserDetails(String enableValue) {
		System.out.println("In service");
		return trialDAO.displayUserDetails(enableValue);
	}
	
	//employee
	@Transactional
	public void authTrans(String userId){
		System.out.println("in service impl for auth" + userId);
		
		trialDAO.authTrans(userId);
	}
	@Transactional
	public void unauthTrans(String userId){
		System.out.println("in service impl for unauth" + userId);
		
		trialDAO.unauthTrans(userId);
	}
	@Transactional
	public List<AuthUser> authenticateUserdetails(String userID) {
		return trialDAO.authenticateUserdetails(userID);
	}
}
