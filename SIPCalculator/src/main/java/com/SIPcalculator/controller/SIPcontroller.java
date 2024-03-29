package com.SIPcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.SIPcalculator.pojo.*;

@Controller
@RequestMapping("/SipCalculator")
public class SIPcontroller {

	@ModelAttribute("params")
	public parameters params() {
		return new parameters();
	}
	
	@GetMapping
	public String  getParams() {
		return "SipCalculator";
	}
	
	@PostMapping
	public String returnResult(@ModelAttribute("params") parameters param,Model model) {		
		double monthlyRate = param.getInterest()/12/100;
		int months = param.getNoOfyears()*12;
		double amount = param.getPrincipal()*(Math.pow(1+monthlyRate, months)-1)/(monthlyRate)*(1+monthlyRate);
		double interestEarned = amount- (param.getPrincipal()*12*param.getNoOfyears());
		model.addAttribute("amount", Math.round(amount));
		model.addAttribute("params", param);
		model.addAttribute("interestEarned", Math.round(interestEarned));
		model.addAttribute("principal", Math.round(param.getPrincipal()*12*param.getNoOfyears()));
		return "SipCalculator";
	}
	
}
