package com.blood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blood.dao.BloodlocRepo;
import com.blood.dao.UserRepo;
import com.blood.entities.Bloodloc;
import com.blood.entities.User;
import com.blood.helper.Message;

@Controller
public class HomeController {
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	BloodlocRepo bloodlocRepo;
	
	
	/*------------ Root page--------------------- */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title", "India's Blood Bank");
		return "index";
	}
	
	/*--------------------- Sign-Up---------------------------*/
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - India's Blood Bank");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	/*--------------------- Do_register---------------------------*/
	@RequestMapping(value="/do_register",method = RequestMethod.POST)
	public String Do_register(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam(value="agreement", defaultValue="false") boolean agreement, Model model,HttpSession session) throws Exception {
		model.addAttribute("title", "Register - India's Blood Bank");
		  
			try 
			{
				if(result.hasErrors()) {throw new Exception("Please fill all the Fields");}
				
				if(!agreement) {throw new Exception("You haven't agreed the terms and conditions");}	
				
				user.setImageUrl("default.png"); 
				user.setEnabled(true);
				user.setRole("ROLE_USER");
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				 
				this.userRepo.save(user);
				
				model.addAttribute("user", new User()); 
				session.setAttribute("message",new Message("Succesfully Registered","alert-success"));
				return "signup";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("user", user);
				/* FieldError fieldError = result.getFieldError(); */
				session.setAttribute("message",new Message("Something went Wrong "+e.getMessage(),"alert-danger"));
				return "signup";
			}
		
	}
	
	/*--------------------- Login---------------------------*/
	@RequestMapping("/signin")
	public String customLogin(Model model) {
		model.addAttribute("title","Login Page");
		return "login";
	}
	
	/*----------------------------------------------- For blood Bank-------------------------------*/
	@RequestMapping("/bloodbank")
	public String bloodbank(Model model) {
		model.addAttribute("title","Blood-Bank");
		model.addAttribute("center", "my Location is for Center");
		return "bloodbank";
	}
	
//	-----------------Showing blood bank center using city----------------------
	
	@RequestMapping("/change")
	public String bloodbank1(@ModelAttribute("Bloodloc") Bloodloc bloodloc,Model model,@RequestParam("activity") String city) {
		model.addAttribute("title","Blood-Bank");
		List<Bloodloc> data = bloodlocRepo.findByCitynames(city);	
		model.addAttribute("data",data); 
		String no = "No.";
		model.addAttribute("no", no);
		String centerName = "Blood Bank Center";
		model.addAttribute("centerName", centerName);
		return "bloodbank";
	}
	
//	----------------------------Journey of the blood--------------------------
	@RequestMapping("/journey")
	public String journey(Model model) {
		model.addAttribute("title","Blood:Journey");
		return "pages/journeyOfBlood";
	}
	
//	----------------------------Why donate Blood--------------------------
	@RequestMapping("/whydonate")
	public String whydonate(Model model) {
		model.addAttribute("title","Blood:Whydonate");
		return "pages/whydonate";
	}
	
//	----------------------------FAQs Blood--------------------------
	@RequestMapping("/faqs")
	public String faqs(Model model) {
		model.addAttribute("title","Blood:FAQs");
		return "pages/BloodDonationFAQ";
	}
	
}
