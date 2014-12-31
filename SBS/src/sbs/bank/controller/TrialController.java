package sbs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sbs.bank.form.TrialFormBean;
import sbs.bank.service.TrialService;
import sbs.bank.DTO.TrialDTO;

@Controller
@SessionAttributes
public class TrialController {
	@Autowired
	TrialService trialService;
	
	@RequestMapping(value = "/trial")
	public ModelAndView trialMethod(@ModelAttribute("trialFormBean") TrialFormBean trialFormBean,
			BindingResult result, ModelMap model){
		model.put("trialFormBean", trialFormBean);
		return new ModelAndView("sample2",model);
		
	}
	
	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public ModelAndView addContact(
			@ModelAttribute("trialFormBean") TrialFormBean trialFormBean,
			BindingResult result, ModelMap model)   {
		
		System.out.println("addContact name: "+trialFormBean.getName() + " " + " id: "+trialFormBean.getId());
		
		trialService.addContact(trialFormBean.getId(), trialFormBean.getName());
		
		return new ModelAndView("sample2",model);
		
	}
	@RequestMapping(value = "/displayContact", method = RequestMethod.POST)
	public ModelAndView displayContact(
			@ModelAttribute("trialFormBean") TrialFormBean trialFormBean,
			BindingResult result, ModelMap model)  {
		System.out.println("in controll");
		
		List<TrialDTO> trialList= trialService.displayContact();
		model.addAttribute("trialList",trialList);
		return new ModelAndView("sample3",model);
	}
}
