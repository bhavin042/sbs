package sbs.bank.services.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Date;
import java.util.Enumeration;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v1CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.x509.*;

import sun.security.x509.X500Name;

import javax.security.cert.X509Certificate;
import javax.servlet.ServletContext;
public class GenerateCerti {
	
	//java.security.cert.X509Certificate[] cerArray = null;

	public java.security.cert.X509Certificate[] generateCert(KeyStore.PrivateKeyEntry priKey, KeyPair kP) {
		
		java.security.cert.X509Certificate[] cerArray = null;
		try {
			System.out.println("before construct");		
				Date certDate = new Date(System.currentTimeMillis()-24*60*60*1000); 
				Date certEndDate = new Date(System.currentTimeMillis()+365*24*60*60*1000);
				BigInteger certSerialNum = BigInteger.ONE;		
				X500Principal issName = new X500Principal("CN=SBS Group 2");
				//SubjectPublicKeyInfo subPubKeyInfo = (SubjectPublicKeyInfo) kP.getPublic();
				System.out.println("just before construct");
				X509v1CertificateBuilder certGen = 
						new JcaX509v1CertificateBuilder(issName, certSerialNum, certDate, certEndDate,issName, kP.getPublic());
				System.out.println("after construct");
				//System.out.println("hello"+certGen+" "+priKey);
				
			
				
				/*certGen.setNotBefore(certDate);
				certGen.setNotAfter(certEndDate);
				certGen.setSerialNumber(certSerialNum);
				certGen.setIssuerDN(issName);			
				certGen.setSubjectDN(issName);				
				certGen.setPublicKey(kP.getPublic());
				System.out.println("before sha");
				certGen.setSignatureAlgorithm("SHA1withDSA");
				System.out.println("after sha");*/
			
				Security.addProvider(new BouncyCastleProvider());
				//System.out.println(priKey.getPrivateKey().toString());
				//System.out.println("Prikey"+priKey.toString());
				
				//java.security.cert.X509Certificate certificate = certGen.generate(priKey.getPrivateKey());
				 JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256withRSA");
				 ContentSigner contentSigner = contentSignerBuilder.build(priKey.getPrivateKey());
				  
				 X509CertificateHolder certHolder = certGen.build(contentSigner);
				 
				 JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
				 java.security.cert.X509Certificate certificate = certConverter.getCertificate(certHolder);
				
				System.out.println("before x509 ceraaray");
				cerArray = new java.security.cert.X509Certificate[] { certificate };
				
				System.out.println("Generated Certificate: " + certificate.toString());
		} 
		catch (Exception e) {
			System.out.println("Exception in GenerateCerti.java class");
			}

		return cerArray;
	}
	
	/*public String signMessage(String message, PrivateKey privateKey){
		String signarureForMessage = null;
		
		try {
			
			Signature sig = Signature.getInstance("SHA1withDSA");
			sig.initSign(privateKey);
			sig.update(message.getBytes());
			byte[] signature = sig.sign();
			System.out.println(sig.getProvider().getInfo());
			System.out.println("\nSignature:");
			System.out.println(new String(signature, "UTF8"));
			
			
			signarureForMessage =  new String(signature);
			
			// verify the signature with the public key
			
		} catch (SignatureException se) {
			System.out.println("Signature failed");
		} catch (Exception e) {
			System.out.println("Other Exception");
			// return "Other Exception";
		}
		return ("Message : "+message);
	}
	
public boolean verifySignature(PublicKey senderPublicKey, String message, String signature){
	// verify the signature with the public key
			
		try{
				System.out.println("\nStart signature verification : "+signature);
				Signature sig = Signature.getInstance("SHA1withDSA");
				sig.initVerify(senderPublicKey);
				sig.update(message.getBytes());

				if (sig.verify(signature.getBytes())) {
					System.out.println("Signature verified");
					return true;
				} else
					System.out.println("Signature failed");
				return false;
			}  catch (Exception e) {
				System.out.println("Other Exception : "+e);
				// return "Other Exception";
				return false;
			}
		
	}
public void verifyUserSignatureUsingUserPublicKey(KeyPair keyPairUser) {

	try {
		System.out.println("User Public key : " + keyPairUser.getPublic());

		// Verify the message that is sent
		Signature sig = Signature.getInstance("SHA1withDSA");
		sig.initSign(keyPairUser.getPrivate());
		sig.update("Hello I am a genuine user".getBytes());
		byte[] signature = sig.sign();
		System.out.println(sig.getProvider().getInfo());
		System.out.println("\nSignature:");
		System.out.println(new String(signature, "UTF8"));
		//
		// verify the signature with the public key
		System.out.println("\nStart signature verification");
		sig.initVerify(keyPairUser.getPublic());
		sig.update("Hello I am a genuine user".getBytes());

		if (sig.verify(signature)) {
			System.out.println("Signature verified");
		} else
			System.out.println("Signature failed");
	} catch (SignatureException se) {
		System.out.println("Signature failed");
	} catch (Exception e) {
		System.out.println("Other Exception");
		// return "Other Exception";
	}
}
	public boolean verifyUserCertificateUsingBankPublicKey(PublicKey sbsPublicKey, X509Certificate senderCertRetrieved){
		
		try {
			senderCertRetrieved.verify(sbsPublicKey); // Banks public key
			System.out.println("Certificate Genuine");
			return true;
			

		} catch (CertificateException e) {
			
			
			return false;
			
		} catch (Exception e) {
			System.out.println("Other Exception in verifyUserCertificateUsingBankPublicKey : "+e);
		}
		return false;
	}

	public void verifyCertificate(KeyPair keyPairBank, KeyPair keyPairUser,
			X509Certificate cert) {

		try {
			cert.verify(keyPairBank.getPublic()); // Banks public key
			System.out.println("Certificate Genuine");

			verifyUserSignatureUsingUserPublicKey(keyPairUser);

		} catch (CertificateException e) {
			System.out.println("Certificate not genuine");
		} catch (Exception e) {
			System.out.println("Other Exception in verifyCertificate");
		}

	}
*/
	
public void genCert(String uName){	 
    try {  	
    
    	String strCom = "keytool -genkeypair -dname \"cn=SBS Group2, ou=Java, o=Oracle, c=US\" -alias "+uName+" -keypass 123456 -keystore C:/keystore/SBSKeyStore.jks -storepass 123456 -validity 180";
         
        Process p = Runtime.getRuntime().exec(strCom);
        Thread.sleep(5000);
        p.destroy();
        System.out.println("process done:"+strCom);
        
    }
    catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
	
	try {
	KeyStore kStore = null; 
	kStore = KeyStore.getInstance("JKS");
	java.io.FileInputStream fis = new java.io.FileInputStream("C:/keystore/SBSKeyStore.jks"); 
	 try {
		kStore.load(fis, "123456".toCharArray());
		
	} catch (NoSuchAlgorithmException e) {
		
		e.printStackTrace();
	} catch (CertificateException e) {
		
		e.printStackTrace();
	} 
	 System.out.println("timepass");
	 
		 FileOutputStream cos = new FileOutputStream("C:/keystore/"+uName+".cer");
		 
		 java.security.cert.Certificate pub = kStore.getCertificate(uName);
		 
		 cos.write(pub.getEncoded());
		 cos.close();
		 fis.close();
		 		 
		 updateKStore(kStore);	 
		 //pub.verify(pub.getPublicKey()); 
		 		 
	}
	catch (IOException e) {
		e.printStackTrace();
	} catch (KeyStoreException e) {		
		e.printStackTrace();
	} catch (CertificateEncodingException e) {
		e.printStackTrace();
	} /*catch (InvalidKeyException e) {
		e.printStackTrace();
	} catch (CertificateException e) {
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	} catch (NoSuchProviderException e) {
		e.printStackTrace();
	} catch (SignatureException e) {
		e.printStackTrace();
	} */
	} 
public void updateKStore(KeyStore kStore){
	 try { 
		 //System.out.println("Inside update keystore");
			 java.io.FileOutputStream fos = new java.io.FileOutputStream("C:/keystore/SBSKeyStore.jks");
			 //System.out.println("open update keystore");
			 kStore.store(fos,"123456".toCharArray());
			 System.out.println("updated keystore after");
			 fos.close();
	 	 }		 
	 catch (Exception e) {
		 e.printStackTrace();
	 }
		return;
}
}