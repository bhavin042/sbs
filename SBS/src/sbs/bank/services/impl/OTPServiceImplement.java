package sbs.bank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbs.bank.dao.OTPDAO;
import sbs.bank.service.OTPService;
import sbs.bank.validations.formValidations;

@Service
public class OTPServiceImplement implements OTPService{
	
	@Autowired
	private OTPDAO otpDao;

	@Transactional
	@Override
	public String generateOTP() throws Exception {
		OTPgenerator otpGenerator=null;
		String otp=null;
		otpGenerator= new OTPgenerator();
		otp=otpGenerator.generateOTP();
		return otp;
	}
	
	@Transactional
	@Override
	public int persistOTP(String id, String otp) throws Exception {
		int row = -1;
		OTPgenerator otpGenerator=null;
		try
		{	formValidations.specialCharacterValidation(id, "User Name",12);
			otpGenerator= new OTPgenerator();
			row=otpDao.persistOTP(id, otp);
			
			//System.out.println("email ID :"+emailID);
			if(row>0){
				String emailID=otpDao.getEmailID(id);
				otpGenerator.emailOTP(emailID, otp);
				System.out.println("Email sent");
			}
			System.out.println("In service row :"+row);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return row;
		
		
	}

	@Transactional
	@Override
	public boolean validateOTP(String id, String generatedOTP) throws Exception {
		
		String otp=null;
		try
		{
			otp = otpDao.getOTP(id);
			if(generatedOTP.equals(otp)){
				System.out.println("Validated OTP");
				return true;
			}
			else{
				System.out.println("Invalid OTP");
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public int changePassword(String uname, String password) throws Exception {
		int row = -1;
		
		try{
			row=otpDao.changePassword(uname, password);
			if(row>0)
				System.out.println("password updated");
			else
				System.out.println("password Not updated");
		}catch(Exception e){
			e.printStackTrace();
		}
		return row;
	}

	@Transactional
	@Override
	public long getOtpTimeDiff(String uname) throws Exception {
		long diff = 0;
		try{
			diff=otpDao.getOtpTimeDiff(uname);
		}catch(Exception e){
			e.printStackTrace();
		}
		return diff;
	}
	
}
