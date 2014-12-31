package sbs.bank.services.impl;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbs.bank.services.impl.GenerateCerti;
import sbs.bank.dao.OTPDAO;
import sbs.bank.form.CertificateFormBean;
import sbs.bank.service.PKIService;

@Service
public class PKIServiceImpl implements PKIService {
	
	@Autowired
	private OTPDAO otpdao;
	
	@Override
	@Transactional
	public CertificateFormBean generateCertificate(String uName) throws Exception{
		CertificateFormBean certFormBean = null;
	
		try {			
			boolean isInKeyStore = isCertiExists(uName);
			String emailId= otpdao.getEmailID(uName);
			if(isInKeyStore){
				System.out.println("Certi already exists");
				emailCerti(emailId,"C:/keystore/"+uName+".cer");
			}
			else{
	
				System.out.println("Creating New User Certi");
				GenerateCerti generateCertificate = new GenerateCerti();
				generateCertificate.genCert(uName);
				emailCerti(emailId,"C:/keystore/"+uName+".cer");
			}
					
		} catch (Exception e) {
			System.out.println("In else exception : " + e);
			e.printStackTrace();
		}	
		
		return certFormBean;
	}
	public void emailCerti(String emailId, String path) throws Exception{
		
		try{
		SendEmail mail = new SendEmail();
		mail.send(emailId, path, "PKI");
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Email does not work ");
			
		}
	}
	
	
	public KeyStore loadKS(){
		 KeyStore ks = null;
		try{
			
			ks = KeyStore.getInstance("JKS");	
			 
			java.io.FileInputStream fInpStream = null;
			
			try 
			{ 
				fInpStream = new	java.io.FileInputStream("C:/keystore/SBSKeyStore.jks"); 
				ks.load(fInpStream,"123456".toCharArray()); 
			}catch(Exception e){
				e.printStackTrace();
			}
			 
			finally {
				 if (fInpStream != null) 
				 { fInpStream.close();}
			  }	
	}catch(Exception e){
		System.out.println("In loadKeyStore exception : "+e);
	}
		return ks;
	}

	public boolean isCertiExists(String uName){
		try{
		KeyStore ks = loadKS();
		
	    if(ks.getCertificate(uName)!=null){
			 return true;
		 }
		
		
		}
		catch (Exception e) {
				System.out.println("In isCertiExists exception");// TODO: handle exception
			}
		return false;
	}
	
	public boolean verifyCerti(String fName,String ruName){
		InputStream inStream = null; 
		try {
		     inStream = new FileInputStream("C:/keystore/Uploaded Certi/"+fName);
		     CertificateFactory cf = CertificateFactory.getInstance("X.509");
		     java.security.cert.Certificate cert = cf.generateCertificate(inStream);     
		     System.out.println("uploaded certi mali gyu");
		     KeyStore kStore = loadKS();
		     System.out.println("keystore loaded");
		     java.security.cert.Certificate pub = kStore.getCertificate(""+ruName);
		     System.out.println("junu certi malyu");
		     cert.verify(pub.getPublicKey());
		     System.out.println("verify karyu");
		     return true;
		 }catch(Exception certException){
			 certException.printStackTrace();
			 return false;	 
		 }
		 
	}
}
