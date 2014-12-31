package sbs.bank.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sbs.bank.form.CertificateFormBean;
import sbs.bank.form.UploadFileForm;
import sbs.bank.service.PKIService;

@Controller
@SessionAttributes
@RequestMapping(value = "PKI")
public class PKIController {
	@Autowired
	PKIService pkiService;
	
	@RequestMapping(value = "/generateCertificate")
	public ModelAndView genCerti(@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model){
		System.out.println("Inside generate certificate controller");
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String uName = auth.getName();
			 certificateFormBean = pkiService.generateCertificate(uName);
			 System.out.println("certificate generated controller");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Inside Certificate Generation Exception: "+e);
		}		
		
		//model.addAttribute("certificateFormBean", certificateFormBean);
		//return new ModelAndView("dispCertificate",model);
		model.addAttribute("pkiSuccessMessage","Certificate has been mailed at registered emailID");
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("IndividualUser",model);
		
	}
	
	@RequestMapping(value="/uploader", method = RequestMethod.GET)
	public String fileUploader(){
		return "uploadCertificate";
	}
	
	@RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    public ModelAndView uploadFiles(@ModelAttribute("uploadForm") UploadFileForm uploadForm,BindingResult result,
            ModelMap model) throws IllegalStateException, IOException {
        
    	List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
        
        MultipartFile f= crunchifyFiles.get(0); 
        byte[] b=f.getBytes();
        System.out.println("Name "+uploadForm.getUname());
        String path="C:\\keystore";
        
        File dir = new File(path + File.separator + "Uploaded Certi");
        System.out.println("Dir"+dir.getAbsolutePath());
        System.out.println(dir.getPath()+"  "+dir.toString());
        if (!dir.exists())
            dir.mkdirs();
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + f.getOriginalFilename());

        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(b);
        stream.close();
        
        System.out.println("Inside verify certificate controller");
		try{
			boolean verified = false;
	        try {//System.out.println(pkiFormBean.getRuName());
	        	
				  verified = pkiService.verifyCerti(f.getOriginalFilename().toString(),uploadForm.getUname());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	        if(verified){
	        	System.out.println("verify OK");
	        	//model.put("certificateFormBean", pkiFormBean);
	        	model.addAttribute("pkiSuccessMessage", "Certificate verified for the user.");
	    		return new ModelAndView("IndividualUser",model);
	        }
	        
	        	System.out.println("failed verification");
			 System.out.println("after failed veri");
			 
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Inside Certificate Generation Exception: "+e);
		}	
        
		
		model.addAttribute("pkiErrorMessage", "Certificate not verified. User is not authenticated.");
		return new ModelAndView("IndividualUser",model);
        
        /*PKIFormBean pkiFormBean = new PKIFormBean();
        model.put("PKIFormBean", pkiFormBean);
		return new ModelAndView("pkiUsername",model);*/
    }
	/*@RequestMapping(value = "/verifyUpCerti")
	public ModelAndView verifyUpCerti(@ModelAttribute("PKIFormBean") PKIFormBean pkiFormBean,
			BindingResult result, ModelMap model){
		System.out.println("Inside verify certificate controller");
		try{
			boolean verified = false;
	        try {System.out.println(pkiFormBean.getRuName());
	        	
				  verified = pkiService.verifyCerti(pkiFormBean.getRuName());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	        if(verified){
	        	System.out.println("verify OK");
	        	//model.put("certificateFormBean", pkiFormBean);
	        	model.addAttribute("pkiSuccessMessage", "Certificate verified for the user.");
	    		return new ModelAndView("IndividualUser",model);
	        }
	        
	        	System.out.println("failed verification");
			 System.out.println("certificate generated controller");
			 
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Inside Certificate Generation Exception: "+e);
		}		
		
		//model.addAttribute("certificateFormBean", certificateFormBean);
		//return new ModelAndView("dispCertificate",model);
		model.addAttribute("pkiSuccessMessage", "Certificate not verified. User is not authenticated.");
		return new ModelAndView("IndividualUser",model);
	}*/
	
	
}