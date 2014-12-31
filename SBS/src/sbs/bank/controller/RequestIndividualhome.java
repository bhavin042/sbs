package sbs.bank.controller;

/*import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;*/
import java.util.List;











import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sbs.bank.DTO.Account;
import sbs.bank.DTO.AuthUser;
import sbs.bank.DTO.PaymentRequest;
import sbs.bank.DTO.TrialDTO;
import sbs.bank.DTO.User;
import sbs.bank.form.ForgetPasswordFormBean;
import sbs.bank.form.TrialFormBean;
import sbs.bank.service.TrialService;

//import sbs.bank.form.TrialFormBean;
@Controller
public class RequestIndividualhome {

	@Autowired
	TrialService trialService;
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public ModelAndView home(){
		
		ModelAndView model1 = new ModelAndView("IndividualUser");
		return model1;
		
	}
	@RequestMapping(value = "/MerchantHome", method = RequestMethod.GET)
	public ModelAndView MerchantHome(){
		
		ModelAndView model1 = new ModelAndView("merchantUser");
		return model1;
		
	}
	@RequestMapping(value = "/profileCreation", method = RequestMethod.GET)
	public ModelAndView homePage(){
		
		ModelAndView model1 = new ModelAndView("createProfile");
		return model1;
		
	}
	@RequestMapping(value = "/creatUserProfile", method = RequestMethod.GET)
	public String viewUserCreationPage(ModelMap map)   {
		
		User user = new User();
		Account account = new Account();
		
		map.addAttribute("user",user);
		map.addAttribute("account",account);
		return "createProfile";
		
	}
	
	@RequestMapping(value = "/creatUserProfile", method = RequestMethod.POST)
	public ModelAndView SaveUser(
			@ModelAttribute("user") User user,
			BindingResult result,@ModelAttribute("account") Account acc, BindingResult result1,ModelMap model, HttpSession session,HttpServletRequest request,HttpServletResponse response, ServletRequest servletRequest)   {
		ModelAndView model1;
		try
		{
		System.out.println("User in Controller"+user);
		//recaptcha
		String challangeField=request.getParameter("recaptcha_challenge_field").toString();
		String responseField=request.getParameter("recaptcha_response_field").toString();
		System.out.println("Secret="+challangeField);
		System.out.println("Secret="+responseField);
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaImpl recaptcha = new ReCaptchaImpl();
		
		recaptcha.setPrivateKey("6Lc9Qf0SAAAAAA9zpSqFhUBFM5k0dzgc-Zh1DLfC");
		ReCaptchaResponse reCaptchaResponse = recaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		System.out.println("After response");
		if(!reCaptchaResponse.isValid())
		{
			model1 = new ModelAndView("createProfile");
			model1.addObject("errorMessage", "Invalid Captcha");
			System.out.println("Invalid captcha");
			return model1;
		}
		
		
		trialService.createProfile(user);
		trialService.UserAccount(acc);
		
		acc.setUserName(user.getUserName());
		model1 = new ModelAndView("profileCreated");
		model1.addObject("UserCreated", "New User has been added");
		session.setAttribute("userName", user.getUserName());
		}
		catch(Exception ex)
		{
			System.out.println("Entered exception");
			ex.printStackTrace();
			model1 = new ModelAndView("createProfile");
			model1.addObject("errorMessage", ex.getMessage());
			
		}
		return model1;
		
	}
	
	@RequestMapping(value = "/AddCredit", method = RequestMethod.GET)
	public ModelAndView AddCreditPage(HttpSession session){
		
		ModelAndView model1 = new ModelAndView("Credit");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        model1.addObject("UpdatedAcc", targetUrl);
		return model1;
		
	}
	
	
	//public ModelAndView AddCredit(@RequestParam("accountNum") String AccNum,@RequestParam("balance") double balance,HttpSession session)  
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public ModelAndView AddCredit(@RequestParam("balance") String bal,HttpSession session)  
	{
		ModelAndView model1;
		double balance = 0;
		try
		{
			balance = Double.valueOf(bal);
			System.out.println("Entered credit post"+balance);
			
			
		}
		catch(Exception ex)
		{
			model1 = new ModelAndView("Credit");
			model1.addObject("errorMessage", "Invalid Amount Format");
		}
		try
		{
			if(balance <=0)
			{
				throw new Exception("Invalid Amount");
			}
			else if(balance >1000000)
			{
				throw new Exception("Invalid Amount. Amount cannot exceed 1 Million");
			}
	     Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	    
	    //String userName = "28";
	       System.out.println("reaching here");
	    trialService.AddCredit(userName,balance);
	    System.out.println("after trail service    ");
	     model1 = new ModelAndView("AccountUpdated");
	    model1.addObject("UpdatedAcc", targetUrl);
		}
		catch(Exception ex)
		{
			/*//ex.printStackTrace();
			model1 = new ModelAndView("Credit");
			model1.addObject("errorMessage", ex.getMessage());*/
			
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			 String role = auth.getAuthorities().toString();
		      String targetUrl = "";
		        if(role.contains("ROLE_INDIVIDUAL")) {
		            targetUrl = "/SBS/homePage";
		        }
		        else if(role.contains("ROLE_MERCHANT")) {
		            targetUrl = "/SBS/MerchantHome";
		        }
		        model1 = new ModelAndView("Credit");
			model1.addObject("UpdatedAcc", targetUrl);
			model1.addObject("errorMessage", ex.getMessage());
			
		}
		
	    return model1;
	    
}
	
	@RequestMapping(value = "/Debit", method = RequestMethod.GET)
	public ModelAndView debitPage(HttpSession session){
		
		ModelAndView model1 = new ModelAndView("debit");
		/*Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        model1.addObject("UpdatedAcc", targetUrl);*/
		return model1;
		
	}
	
	@RequestMapping(value = "/updateDebitAmount", method = RequestMethod.POST)
	public ModelAndView DebitAmt(@RequestParam("balance") String bal,HttpSession session)  
	{
	 //trialService.AddCredit(balance);
	    /*ModelAndView model1 = new ModelAndView("AccountUpdated");
		model1.addObject("UpdatedAcc", "Account ");
		String s = (String) session.getAttribute("userID");
		System.out.println("userId is" + s);
		return model1;*/
		ModelAndView model1;
		double balance = 0;
		try
		{
			balance = Double.valueOf(bal);
			System.out.println("Entered debit post"+balance);
			
			
		}
		catch(Exception ex)
		{
			model1 = new ModelAndView("debit");
			model1.addObject("errorMessage", "Invalid Amount Format");
		}
		try
		{
			
			if(balance <=0)
			{
				throw new Exception("Invalid Amount");
			}
			else if(balance >1000000)
			{
				throw new Exception("Invalid Amount. Amount cannot exceed 1 Million");
			}
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		     String userName=auth.getName();
		     session.setAttribute("userName", userName);
	    //String userName = "28";
		     String role = auth.getAuthorities().toString();
		      String targetUrl = "";
		        if(role.contains("ROLE_INDIVIDUAL")) {
		            targetUrl = "/SBS/homePage";
		        }
		        else if(role.contains("ROLE_MERCHANT")) {
		            targetUrl = "/SBS/MerchantHome";
		        }
			trialService.debit(userName,balance);

	   // model1 = new ModelAndView("transfer");
			model1 = new ModelAndView("AccountUpdated");
	    model1.addObject("UpdatedAcc", targetUrl);
	    
	}
	catch(Exception ex)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		 String role = auth.getAuthorities().toString();
	      String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
		model1 = new ModelAndView("debit");
		model1.addObject("UpdatedAcc", targetUrl);
		model1.addObject("errorMessage", ex.getMessage());
		
	}
	
	    return model1;
	    
}
	@RequestMapping(value = "/TransferAmount", method = RequestMethod.GET)
	public ModelAndView TransferPage(HttpSession session){
		
		ModelAndView model1 = new ModelAndView("transfer");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        model1.addObject("UpdatedAcc", targetUrl);
		return model1;
		
	}
	
	@RequestMapping(value = "/AmountTransfer", method = RequestMethod.POST)
	public ModelAndView TransferAmt(@RequestParam("UserName") String AmtToId, @RequestParam("balance") String bal,HttpSession session)   
	{
		ModelAndView model1;
		double balance = 0;
		try
		{
			balance = Double.valueOf(bal);
			System.out.println("Entered debit post"+balance);
			
			
		}
		catch(Exception ex)
		{
			model1 = new ModelAndView("transfer");
			model1.addObject("errorMessage", "Invalid Amount Format");
		}
		try
		{
			
			/*if(balance <=0)
			{
				throw new Exception("Invalid Amount");
			}
			else if(balance >1000000)
			{
				throw new Exception("Invalid Amount. Amount cannot exceed 1 Million");
			}*/
	   // String userName = "28";
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		     String userName=auth.getName();
		     session.setAttribute("userName", userName);
		     String role = auth.getAuthorities().toString();

		        String targetUrl = "";
		        if(role.contains("ROLE_INDIVIDUAL")) {
		            targetUrl = "/SBS/homePage";
		        }
		        else if(role.contains("ROLE_MERCHANT")) {
		            targetUrl = "/SBS/MerchantHome";
		        }
		        model1 = new ModelAndView("AmountTransfered");
		        model1.addObject("UpdatedAcc", targetUrl);
		        if(balance <=0)
				{
					throw new Exception("Invalid Amount");
				}
				else if(balance >1000000)
				{
					throw new Exception("Invalid Amount. Amount cannot exceed 1 Million");
				}
	          trialService.transfer(userName,AmtToId,balance);
	     
	    model1.addObject("TranferConf", "Your tranfer to " + AmtToId + "has been successful");
		}
		catch(Exception ex)
		{
			/*model1 = new ModelAndView("transfer");
			model1.addObject("errorMessage", ex.getMessage());*/
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			 String role = auth.getAuthorities().toString();
		      String targetUrl = "";
		        if(role.contains("ROLE_INDIVIDUAL")) {
		            targetUrl = "/SBS/homePage";
		        }
		        else if(role.contains("ROLE_MERCHANT")) {
		            targetUrl = "/SBS/MerchantHome";
		        }
		    model1 = new ModelAndView("transfer");
			model1.addObject("UpdatedAcc", targetUrl);
			model1.addObject("errorMessage", ex.getMessage());
		}
	    return model1;
	    
}
	@RequestMapping(value = "/PaymentRequest", method = RequestMethod.GET)
	public ModelAndView paymentRequest(HttpSession session){
		
		ModelAndView model1 = new ModelAndView("RequestPayment");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        model1.addObject("UpdatedAcc", targetUrl);
		return model1;
		
	}
	@RequestMapping(value = "/SubmitRequest", method = RequestMethod.POST)
	public ModelAndView submitRequest(@ModelAttribute("SubmitRequest") PaymentRequest payReq,HttpSession session) 
	{
		
		//trialService.AddCredit(balance);
	    /*ModelAndView model1 = new ModelAndView("AccountUpdated");
		model1.addObject("UpdatedAcc", "Account ");
		String s = (String) session.getAttribute("userID");
		System.out.println("userId is" + s);
		return model1;*/
		ModelAndView model1;
		try
		{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		     String userName=auth.getName();
		     session.setAttribute("userName", userName);
		     String role = auth.getAuthorities().toString();

		        String targetUrl = "";
		        if(role.contains("ROLE_INDIVIDUAL")) {
		            targetUrl = "/SBS/homePage";
		        }
		        else if(role.contains("ROLE_MERCHANT")) {
		            targetUrl = "/SBS/MerchantHome";
		        }
		        model1 = new ModelAndView("RequestSubmitted");
		        model1.addObject("UpdatedAcc", targetUrl);
	    //String userName = "27";
	    payReq.setToID(userName);
	    payReq.setPaymentDue(1);
	    /*String id = "5";
	    payReq.setReqID(id);*/
	    trialService.submitRequest(payReq);
	    
	    model1.addObject("Request", "Your request to " + payReq.getFromID() + "has been submitted successfully");
		}
		catch(Exception ex)
		{
			model1 = new ModelAndView("RequestPayment");
			model1.addObject("errorMessage", ex.getMessage());
			
		}
	    return model1;
		
	}
	
	@RequestMapping(value = "/DisplayPaymentRequest", method = RequestMethod.GET)
	public ModelAndView displayRequest(HttpSession session)  {
		System.out.println("in controll");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     System.out.println("In display");
	     session.setAttribute("userName", userName);
		// String userName = "28";
		ModelMap model = new ModelMap();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        //model1.addObject("UpdatedAcc", targetUrl);
	        model.addAttribute("UpdatedAcc",targetUrl);
		List<PaymentRequest> requestList= trialService.displayRequest(userName);
		//String s = (String) session.getAttribute("userID");
		 for(PaymentRequest pr:requestList){
		        System.out.println(pr.getReqID() + pr.getAmount() + pr.getToID());
		    }
		//System.out.println("values of list" + requestList);
		// double amt = requestList.get(0).getAmount();
		// model.addAttribute("amt",amt);
	   model.addAttribute("requestList",requestList);
		return new ModelAndView("listOfRequests",model);
		
	}
	
	@RequestMapping(value = "/makePayment/{reqid}", method = RequestMethod.GET)
	public ModelAndView submitRequestedPayment(@PathVariable("reqid") String Reqid,HttpSession session) throws Exception  {
		ModelAndView model1;
		try
		{
		// String userName = "28";
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		     String userName=auth.getName();
		     session.setAttribute("userName", userName);
		     String role = auth.getAuthorities().toString();

		        String targetUrl = "";
		        if(role.contains("ROLE_INDIVIDUAL")) {
		            targetUrl = "/SBS/homePage";
		        }
		        else if(role.contains("ROLE_MERCHANT")) {
		            targetUrl = "/SBS/MerchantHome";
		        }
		        //model1.addObject("UpdatedAcc", targetUrl);
		       // model.addAttribute("UpdatedAcc",targetUrl);
		        System.out.println("in make payment");
		    trialService.submitRequestedPayment(userName,Reqid);
		  model1 = new ModelAndView("RequestPaymentDone");
		  model1.addObject("UpdatedAcc", targetUrl);
		    model1.addObject("paymentDone", "Your  requested payment has been done successfully");
		}
		catch(Exception ex)
		{
			model1 = new ModelAndView("listOfRequests");
			model1.addObject("errorMessage","no payment" /*ex.getMessage()*/);
			
		}
		    return model1;
		
	}
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView displayContact(HttpSession session)  {
		ModelMap model = new ModelMap();
		System.out.println("in controll");
		//String userName = "28";
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        model.addAttribute("UpdatedAcc", targetUrl);
		List<User> userList= trialService.displayProfile(userName);
		model.addAttribute("userList",userList);
		return new ModelAndView("profile",model);
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView displayAccountDetails(HttpSession session)  {
		ModelMap model = new ModelMap();
		System.out.println("in controll");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	     String userName=auth.getName();
	     session.setAttribute("userName", userName);
	     String role = auth.getAuthorities().toString();

	        String targetUrl = "";
	        if(role.contains("ROLE_INDIVIDUAL")) {
	            targetUrl = "/SBS/homePage";
	        }
	        else if(role.contains("ROLE_MERCHANT")) {
	            targetUrl = "/SBS/MerchantHome";
	        }
	        model.addAttribute("UpdatedAcc", targetUrl);
	        System.out.println("In controll before service:");
		//String userName = "28";
		List<Account> accList= trialService.displayAccountDetails(userName);
		System.out.println("after Service");
		model.addAttribute("accList",accList);
		return new ModelAndView("AccountDetails",model);
	}

	@RequestMapping(value = "/displayUser", method = RequestMethod.GET)
	public ModelAndView displayUserdetails()  {
		ModelMap model = new ModelMap();
		System.out.println("in controll in display user");
		
		String enableValue = "0";
		System.out.println("ebable =0");
		List<User> userList= trialService.displayUserDetails(enableValue);
		
		model.addAttribute("userList",userList);
		return new ModelAndView("displayUser",model);
	}
	@RequestMapping(value = "/deleteUserList", method = RequestMethod.GET)
	public ModelAndView deleteUserprofile()  {
		ModelMap model = new ModelMap();
		System.out.println("in delete");
		
		String enableValue = "1";
		List<User> userList= trialService.displayUserDetails(enableValue);
		model.addAttribute("userList",userList);
		return new ModelAndView("deleteUserList",model);
	}
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)	
	public ModelAndView addUserdetails(@RequestParam("userId") String userId)   {
		ModelAndView model1;
		try{
	trialService.addUser(userId);
	System.out.println("adduser name: "+userId );
	model1 = new ModelAndView("admin");
	}
	catch(Exception ex)
	{
    model1 = new ModelAndView("displayUser");
	}
	 
	return model1;
	}
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)	
	public ModelAndView deleteUserdetails(@RequestParam("userId") String userId)   {
		ModelAndView model1;
		try
		{
		System.out.println("deleteuser name: "+userId );
		trialService.deleteUser(userId);
		model1 = new ModelAndView("admin");
		}
		catch(Exception ex)
		{
	    model1 = new ModelAndView("deleteUserList");
		}
	return model1;
	}
	
	//employee
	
	@RequestMapping(value = "/authUser", method = RequestMethod.GET)
	public ModelAndView authenticateUserdetails()  {
		ModelMap model = new ModelMap();
		System.out.println("in authenticate");
		
		String authval = "0";
		List<AuthUser> userList= trialService.authenticateUserdetails(authval);
		model.addAttribute("userList",userList);
		return new ModelAndView("authUser",model);
	}
	@RequestMapping(value = "/unauthUser", method = RequestMethod.GET)
	public ModelAndView unauthenticateUserdetails()  {
		ModelMap model = new ModelMap();
		System.out.println("in unauthenticate");
		
		String authval = "1";
		List<AuthUser> userList= trialService.authenticateUserdetails(authval);
		model.addAttribute("userList",userList);
		return new ModelAndView("unauthUser",model);
	}
	
	@RequestMapping(value = "/authTrans", method = RequestMethod.POST)	
	public ModelAndView authenticateTrans(@RequestParam("userId") String userId)   {
		ModelAndView model1;
		try{
	trialService.authTrans(userId);
	System.out.println("authTrans name: "+userId );
	model1 = new ModelAndView("emp");
	}
	catch(Exception ex)
	{
    model1 = new ModelAndView("authUser");
	}
	 
	return model1;
	}
	@RequestMapping(value = "/unauthTrans", method = RequestMethod.POST)	
	public ModelAndView unauthenticateTrans(@RequestParam("userId") String userId)   {
		ModelAndView model1;
		try{
	trialService.unauthTrans(userId);
	System.out.println("unauthTrans name: "+userId );
	model1 = new ModelAndView("emp");
	}
	catch(Exception ex)
	{
    model1 = new ModelAndView("unauthUser");
	}
	 
	return model1;
	}
	
}
