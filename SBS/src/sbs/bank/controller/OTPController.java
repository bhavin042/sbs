package sbs.bank.controller ;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sbs.bank.form.ForgetPasswordFormBean;
import sbs.bank.service.OTPService;
import sbs.bank.validations.formValidations;

@Controller
@SessionAttributes
@RequestMapping(value="/OTP")
public class OTPController {
	
	@Autowired
	OTPService otpService;
	
	@RequestMapping(value="/generateOTP", method = RequestMethod.GET)
	public String generateOTP(@ModelAttribute("forgetPasswordFormBean") ForgetPasswordFormBean forgetPasswordFormBean,
		BindingResult result, ModelMap model) throws Exception{
		 
			return "otp/oneTimePassword";
		
	}
	@RequestMapping(value="/forgetPassword", method = RequestMethod.POST)
	public ModelAndView persistOTP(@ModelAttribute("forgetPasswordFormBean") ForgetPasswordFormBean forgetPasswordFormBean,
			BindingResult result, ModelMap model) throws Exception{
		int row=0;
		try{
			System.out.println("In contro");
			String uname= forgetPasswordFormBean.getUserName();	
			
			String otp=otpService.generateOTP();
			
			System.out.println("Generated OTP:"+otp);
			row=otpService.persistOTP(uname, otp);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(row>0){
			model.addAttribute("otpGenerationMessage", "OTP has been mailed to you at registered email.");
			return new ModelAndView("/otp/validateOTP",model);
		}else{
			//model.addAttribute("changePasswordFormBean",forgetPasswordFormBean);
			model.addAttribute("errorMessage", "Invalid Username");
			return new ModelAndView("/otp/oneTimePassword", model);
		}
		
	}
	@RequestMapping(value="/validateOTP", method = RequestMethod.POST)
	public ModelAndView validateOTP(@ModelAttribute("forgetPasswordFormBean") ForgetPasswordFormBean forgetPasswordFormBean,
			BindingResult result, ModelMap model) throws Exception{
				Boolean r=false;
				String uname= forgetPasswordFormBean.getUserName();
				String enteredOTP=forgetPasswordFormBean.getOtp();
				r = otpService.validateOTP(uname,enteredOTP);
				long diff=otpService.getOtpTimeDiff(uname);
					if(diff>15){
						model.addAttribute("errorMessage", "Your OTP has been expired !! Please generate new OTP");
						return new ModelAndView("/otp/oneTimePassword",model);
					}
					if(r){
						model.addAttribute("successMessage", "Please enter new password");
						return new ModelAndView("/otp/changePassword",model);
					}
					else{
						model.addAttribute("errorMessage", "Please enter Valid OTP");
						return new ModelAndView("/otp/validateOTP",model);
						
					}		
	}
	@RequestMapping(value="/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute("forgetPasswordFormBean") ForgetPasswordFormBean forgetPasswordFormBean,
			BindingResult result, ModelMap model) throws Exception{
				
				System.out.println("in change password");
				int row=0;
				String uname= forgetPasswordFormBean.getUserName();
				String password=forgetPasswordFormBean.getPassword();
				
				try {
					formValidations.specialCharacterValidation(uname, "User Name",12);
					formValidations.specialCharacterValidation(password, "Password",12);			
					formValidations.passwordValidation(password,password, "Password");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					model.addAttribute("serverErrorMessage", e1.getMessage());
					return new ModelAndView("/otp/changePassword",model);
				}
				
				
				System.out.println("New password "+password);
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(password);
				System.out.println("Hashed password : "+hashedPassword);
				try{
					row = otpService.changePassword(uname, hashedPassword);
				}catch(Exception e){
					e.printStackTrace();
					model.addAttribute("serverErrorMessage", e.getMessage());
				}
				if(row>0){
						model.addAttribute("successMessage", "Please login using new password");
						return new ModelAndView("login",model);
				}
				else{
					model.addAttribute("errorMessage", "Please Check username or Password");
					return new ModelAndView("/otp/changePassword",model);
				}
	}
}