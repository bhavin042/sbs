package sbs.bank.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbs.bank.dao.OTPDAO;

@Repository
public class OTPDAOImplement implements OTPDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public int persistOTP(String id, String otp) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();		
		String d=dateFormat.format(date);
		//Date d1=dateFormat.parse(d);
		System.out.println("OTP Time"+dateFormat.format(date));
		Query q=sessionFactory.getCurrentSession().createQuery("update UserDTO u set u.otp = :otp, u.otpTime =:d where u.username = :id");
		q.setParameter("otp", otp);
		q.setParameter("id", id);
		q.setParameter("d", d);
		int row=q.executeUpdate();
		System.out.println("Rows updated :"+row);
		return row;
	}

	@Transactional
	@Override
	public String getOTP(String id) throws Exception {
		Query q=sessionFactory.getCurrentSession().createQuery("select u.otp from UserDTO u where u.id = :id");
		q.setParameter("id", id);
		String otp=q.uniqueResult().toString();
		System.out.println("In DAO, ID:"+id+" otp: "+otp);
		return otp;
	}
	@Transactional
	@Override
	public String getEmailID(String id) throws Exception {
		System.out.println("In get email");
		Query q=sessionFactory.getCurrentSession().createQuery("select u.emailId from User u where u.userName = :id");
		q.setParameter("id", id);
		String emailId=q.uniqueResult().toString();
		System.out.println("In DAO, ID:"+id+" otp: "+emailId);
		return emailId;
	}
	@Transactional
	@Override
	public long getOtpTimeDiff(String id) throws Exception {
		Query q1=sessionFactory.getCurrentSession().createQuery("select u.otpTime from UserDTO u where u.id = :id");
		q1.setParameter("id", id);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String otptime=q1.uniqueResult().toString();
		Date date = new Date();		
		String d=dateFormat.format(date);
		Date d1=dateFormat.parse(d);
		Date d2=dateFormat.parse(otptime);
		long td = Math.abs(d1.getTime() - d2.getTime());
		td= td / (60 * 1000);
		System.out.println("time difference :"+td);
		return td;
	}

	@Transactional
	@Override
	public int changePassword(String uname, String password) throws Exception {
		Query q=sessionFactory.getCurrentSession().createQuery("update UserDTO u set u.password = :password where u.username = :uname");
		q.setParameter("password", password);
		q.setParameter("uname", uname);
		int row=q.executeUpdate();
		System.out.println("Rows updated :"+row);
		return row;
	}
	
}