package sbs.bank.service;

import java.security.PublicKey;
import sbs.bank.form.CertificateFormBean;

public interface PKIService {
	
	public CertificateFormBean generateCertificate(String uName) throws Exception;
	public boolean verifyCerti(String fName,String ruName) throws Exception;
	
}
