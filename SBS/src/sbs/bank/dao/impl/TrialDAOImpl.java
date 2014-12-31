package sbs.bank.dao.impl;



import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbs.bank.DTO.Account;
import sbs.bank.DTO.AuthUser;
import sbs.bank.DTO.PaymentRequest;
import sbs.bank.DTO.TrialDTO;
import sbs.bank.DTO.User;
import sbs.bank.DTO.UserDTO;
import sbs.bank.dao.TrialDAO;
import sbs.bank.sessionfactory.SessionFactoryUtil;

@Repository
public class TrialDAOImpl implements TrialDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void addContact(TrialDTO trialDTO){
		System.out.println("DAO id:"+trialDTO.getId() );
		sessionFactory.getCurrentSession().saveOrUpdate(trialDTO);
	}

	@Transactional
	@Override
	public List<TrialDTO> displayContact() {
		Query q= sessionFactory.getCurrentSession().createQuery("from TrialDTO");
		@SuppressWarnings("unchecked")
		List<TrialDTO> trialList = q.list();
		return trialList;
	}
	
	 @Transactional
	 public void createProfile(User user) throws Exception{
		 	Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	User usr = null;
		 	try
		 	{
		 		System.out.println("User = "+user);
		 		tx=session.beginTransaction();
		 		String hql = "FROM User U WHERE U.userName = '"+user.getUserName()+"'";
		 		Query query = session.createQuery(hql);
		 		List list=query.list();
		 		if(!list.isEmpty())
		 		{
		 			throw new Exception("User already exists*");
		 		}
		 		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(user.getPassword());
				user.setPassword(hashedPassword);
		 		
		 		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
				String cp = passwordEncoder.encode(user.getConfirmPassword());
				user.setConfirmPassword(cp);
				user.setEnabled("0");
				session.save(user);
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
	}
	 
	 @Transactional
	 public void UserAccount(Account acc) throws Exception
	 {
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	Account acnt = null;
		 	try
		 	{
		 		System.out.println("Account = "+acc);
		 		tx=session.beginTransaction();
		 		String hql = "FROM Account A WHERE A.accountNum = '"+acc.getAccountNum()+"'";
		 		Query query = session.createQuery(hql);
		 		List list=query.list();
		 		if(!list.isEmpty())
		 		{
		 			throw new Exception("Account number already exists*");
		 		}
		 		session.save(acc);
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
		 
	 }
	 @Transactional
	 public void AddCredit(String id, double balance)throws Exception
	 {
		 Account account = null;
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	try
		 	{
		 		System.out.println("id = "+id);
		 		tx=session.beginTransaction();
		 		String hql = "FROM Account A WHERE A.userName = '"+id+"'";
		 		Query query = session.createQuery(hql);
		 		List list=query.list();
		 		account=(Account)list.get(0);
		 		
		 		account.setBalance( balance + account.getBalance());
		 		session.update(account);
		 		
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
		 
	 }
	 
	 @Transactional
	 public void debit(String id, double balance) throws Exception
	 {
		 Account account = null;
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	try
		 	{
		 		System.out.println("id = "+id);
		 		tx=session.beginTransaction();
		 		String hql = "FROM Account A WHERE A.userName = '"+id+"'";
		 		Query query = session.createQuery(hql);
		 		List list=query.list();
		 		account=(Account)list.get(0);
		 		if(account==null)
		 			throw new Exception("Account details not found for the user");
		 		
		 		if(account.getBalance() < balance)
		 			throw new Exception("Insufficient balance to perform debit/transfer");
		 	
		 		account.setBalance(account.getBalance() - balance);
		 		
		 		session.update(account);
		 		
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
		 
	 }
	 
	 @Transactional
	 public void transfer(String userId, String amtToId, double balance) throws Exception
	 {
		 Account account1 = null;
		 Account account2 = null;
		 
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	try
		 	{
		 		System.out.println("id = "+userId);
		 		tx=session.beginTransaction();
		 		String hql1 = "FROM Account A WHERE A.userName = '"+userId+"'";
		 		String hql2 = "FROM Account A WHERE A.userName = '"+amtToId+"'";
		 		Query query1 = session.createQuery(hql1);
		 		Query query2 = session.createQuery(hql2);
		 		List list1=query1.list();
		 		List list2=query2.list();
		 		account1=(Account)list1.get(0);
		 		account2=(Account)list2.get(0);
		 		
		 		if(account1==null)
		 			throw new Exception("Account details not found for the user");
		 		
		 		if(account2==null)
		 			throw new Exception("Account details not found for the user");
		 		if(account1.getBalance() < balance)
		 			throw new Exception("Insufficient balance to perform debit/transfer");
		 		if(account1.getBalance() >= balance)
		 		{
		 		account2.setBalance(account2.getBalance() + balance);
		 		account1.setBalance(account1.getBalance() - balance);
		 		}
		 		session.update(account1);
		 		session.update(account2);
		 		
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
		 
	 }
	 @Transactional
	 public void submitRequest(PaymentRequest payReq)throws Exception
	{
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	try
		 	{
		 		System.out.println("Account = "+ payReq);
		 		tx=session.beginTransaction();
		 		session.save(payReq);
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
		 
				}
	 
	 @Transactional
	 public List<PaymentRequest> displayRequest(String userID)
	 {	
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 		System.out.println("id = "+userID);
		 		tx=session.beginTransaction();
		 		//Add yes constraint as well
		 		int t = 1;
		 		String hql = "FROM PaymentRequest RP WHERE RP.fromID = '"+userID+"' and  RP.paymentDue = '"+t+"' ";
		 		Query query = session.createQuery(hql);
		 		List list=query.list();
		 		tx.commit();
		 		return list;
	 	
	 }
	 
	 @Transactional
	 public void submitRequestedPayment(String userId, String reqid) throws Exception
	 {
		 Account account1 = null;
		 Account account2 = null;
		 PaymentRequest paymentReq = null;
		 
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	try
		 	{
		 		System.out.println("id = "+userId);
		 		tx=session.beginTransaction();
		 		System.out.println("Request ID :"+reqid);
		 		String hql1 = "FROM PaymentRequest P WHERE P.reqID = '"+reqid+"'";
		 		String hql2 = "FROM Account A WHERE A.userName = '"+userId+"'";
		 		Query query1 = session.createQuery(hql1);
		 		Query query2 = session.createQuery(hql2);
		 		List list1=query1.list();
		 		List list2=query2.list();
		 		System.out.println("List "+list1);
		 		paymentReq=(PaymentRequest)list1.get(0);
		 		account1=(Account)list2.get(0);
		 		String ToID = paymentReq.getToID();
		 		String hql3 = "FROM Account B WHERE B.userName = '"+ToID+"'";
		 		Query query3 = session.createQuery(hql3);
		 		List list3=query3.list();
		 		account2=(Account)list3.get(0);
		 		if(account1.getBalance() >=paymentReq.getAmount() )
		 		{
		 		account2.setBalance(account2.getBalance() + paymentReq.getAmount());
		 		account1.setBalance(account1.getBalance() - paymentReq.getAmount());
		 		paymentReq.setPaymentDue(0);
		 		}
		 		session.update(account1);
		 		session.update(account2);
		 		session.update(paymentReq);
		 		
		 		tx.commit();
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
	 }
	 @Transactional
	 public List<User> displayProfile(String userID) {
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	User user = null;
		 	try
		 	{
		 		//System.out.println("UserId = "+ userID);
		 		tx=session.beginTransaction();
		 		String hql = "FROM User U WHERE U.userName = '"+userID+"'";
		 		Query query = session.createQuery(hql);
		 		List<User> userList = query.list();
		 		tx.commit();
				return userList;				
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
	}
	 
	 public List<Account> displayAccountDetails(String userId)
	 {
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	User user = null;
		 	try
		 	{
		 		//System.out.println("UserId = "+ userID);
		 		tx=session.beginTransaction();
		 		System.out.println("user Id"+userId);
		 		String hql = "FROM Account A WHERE A.userName = '"+userId+"'";
		 		Query query = session.createQuery(hql);
		 		List<Account> accList = query.list();
		 		System.out.println("after List");
		 		tx.commit();
				return accList;				
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
	 }
	 
	 @Transactional
	 public List<User> displayUserDetails(String enableValue) {
		 System.out.println("In dao");
		 Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 	Transaction tx = null;
		 	User user = null;
		 	UserDTO userdto =null;
		 	try
		 	{
		 		//System.out.println("UserId = "+ userID);
		 		tx=session.beginTransaction();
		 		
		 		
		 		String hql = "FROM User U where U.enabled ='" + enableValue +"'";
		 	
		 		
		 		List<User> userList = session.createQuery(hql).list();
		 		tx.commit();
			System.out.println("out of dao");
		 		return userList;				
		 	}
		 	catch(Exception e)
		 	{
		 		tx.rollback();
		 		throw e;
		 	}
		 	finally
		 	{
		 		session.close();
		 	}
	}
	 
	 
	 @Transactional
		@Override
		public void addUser(String userId){
			User user = null;
			UserDTO userDto = null;
			System.out.println("User name aapo ne bhai");
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			 	Transaction tx = null;
			 	try
			 	{
			 		System.out.println("in try");
			 		System.out.println("id = "+userId);
			 		tx=session.beginTransaction();
			 		String hql = "FROM User U WHERE U.userName = '"+userId+"'";
			 		
			 		Query query = session.createQuery(hql);
			 		
			 		List list=query.list();
			 		user=(User)list.get(0);
			 		
			 		
			 		System.out.println("user DTO ");
			 		user.setEnabled("1");
			 		user.setUserName(userId);
			 		
			 		session.update(user);
			 		
			 		
			 		tx.commit();
			 	}
			 	catch(Exception e)
			 	{
			 		tx.rollback();
			 		throw e;
			 	}
			 	finally
			 	{
			 		session.close();
			 	}
			 	sessionFactory.getCurrentSession().saveOrUpdate(user);
			 
			
		}
		@Override
		public void deleteUser(String userId){
			User user = null;
			
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			 	Transaction tx = null;
			 	try
			 	{
			 		System.out.println("delete id = "+userId);
			 		tx=session.beginTransaction();
			 		String hql = "FROM User U WHERE U.userName = '"+userId+"'";
			 		
			 		Query query = session.createQuery(hql);
			 		List list=query.list();
			 		user=(User)list.get(0);
			 		System.out.println("user DTO for delete ");
			 		user.setEnabled("0");
			 		user.setUserName(userId);
			 		
			 		session.update(user);
			 		
			 		
			 		tx.commit();
			 	}
			 	catch(Exception e)
			 	{
			 		tx.rollback();
			 		throw e;
			 	}
			 	finally
			 	{
			 		session.close();
			 	}
				
		}
		
		//employee
		
		@Transactional
		@Override
		public void authTrans(String userId){
			AuthUser user = null;
			UserDTO userDto = null;
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			 	Transaction tx = null;
			 	try
			 	{
			 		System.out.println("id = "+userId);
			 		tx=session.beginTransaction();
			 		String hql = " FROM AuthUser U WHERE U.userId = '"+userId+"'";
			 		
			 		Query query = session.createQuery(hql);
			 		
			 		List list=query.list();
			 		user=(AuthUser)list.get(0);
			 		
			 		
			 		System.out.println("user DTO ");
			 		user.setAuthVal(1);
			 		user.setUserId(userId);
			 		
			 		session.update(user);
			 		
			 		
			 		tx.commit();
			 	}
			 	catch(Exception e)
			 	{
			 		tx.rollback();
			 		throw e;
			 	}
			 	finally
			 	{
			 		session.close();
			 	}
			 	sessionFactory.getCurrentSession().saveOrUpdate(user);
			 
			
		}
		@Transactional
		@Override
		public void unauthTrans(String userId){
			AuthUser user = null;
			UserDTO userDto = null;
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			 	Transaction tx = null;
			 	try
			 	{
			 		System.out.println("id = "+userId);
			 		tx=session.beginTransaction();
			 		String hql = "FROM AuthUser U WHERE U.userId = '"+userId+"'";
			 		
			 		Query query = session.createQuery(hql);
			 		
			 		List list=query.list();
			 		user=(AuthUser)list.get(0);
			 		
			 		
			 		System.out.println("user DTO ");
			 		user.setAuthVal(0);
			 		user.setUserId(userId);
			 		
			 		session.update(user);
			 		
			 		
			 		tx.commit();
			 	}
			 	catch(Exception e)
			 	{
			 		tx.rollback();
			 		throw e;
			 	}
			 	finally
			 	{
			 		session.close();
			 	}
			 	sessionFactory.getCurrentSession().saveOrUpdate(user);
			 
			
		}
		
		@Transactional
		 public List<AuthUser> authenticateUserdetails(String authval) {
			 Session session = SessionFactoryUtil.getSessionFactory().openSession();
			 	Transaction tx = null;
			 	AuthUser user = null;
			 	
			 	try
			 	{
			 		//System.out.println("UserId = "+ userID);
			 		tx=session.beginTransaction();
			 		
			 		
			 		String hql = "FROM AuthUser U where U.authval ='" + authval +"'";
			 	
			 		
			 		List<AuthUser> userList = session.createQuery(hql).list();
			 		tx.commit();
					return userList;				
			 	}
			 	catch(Exception e)
			 	{
			 		tx.rollback();
			 		throw e;
			 	}
			 	finally
			 	{
			 		session.close();
			 	}
		}
		 
}
