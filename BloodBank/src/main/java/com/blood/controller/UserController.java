package com.blood.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blood.dao.UserRepo;
import com.blood.entities.User;
import com.blood.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {

		@Autowired
		UserRepo userRepository;
		
		@Autowired
		 private BCryptPasswordEncoder passwordEncoder;
		
		//method for  adding common data to response
		@ModelAttribute
		public void addCommonData(Model model,Principal principal) {
			String userName=principal.getName();
			User user=userRepository.getUserByUserName(userName);
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("age", age);
			model.addAttribute("user", user);
		}
		
		
		//method for Dashboard
		@RequestMapping("/index")
		public String dashboard(User user,Model model,Principal principal) {	
			model.addAttribute("title","User Dashboard");
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("age", age);
			
			return "normal/user_dashboard";
		}
		
		@RequestMapping("/edit_profile")
		public String edit_profile(Model model,User user) {
			System.out.println("in the edit profile");
			model.addAttribute("title", "Edit_Profile");
			
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("age", age);
			
			return "normal/edit_profile";
		}
		
		@RequestMapping("/edit")
		public String edit(@ModelAttribute("user") User user, Model model, @RequestParam("id") int id) {
			System.out.println("in the edit only");
			User user1=userRepository.getUserByUserId(id);
			user1.setName(user.getName());
			user1.setEmail(user.getEmail());
			user1.setPassword(passwordEncoder.encode(user.getPassword()));
			user1.setAbout(user.getAbout());
			user1.setCity(user.getCity());
			user1.setState(user.getState());
			user1.setDesignation(user.getDesignation());
			user1.setDob(user.getDob());
			user1.setContact(user.getContact());
			user1.setAddress(user.getAddress());
			userRepository.save(user1);
			
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("title", "Edit_Profile");
			model.addAttribute("age", age);
			
			return "normal/user_dashboard";
		}
		
		@RequestMapping("/request")
		public String request(@ModelAttribute("user") User user, Model model) {
			
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("title", "Edit_Profile");
			model.addAttribute("age", age);
			
			return "normal/request";
		}
		
		
		@RequestMapping("/requesting")
		public String requesting(@ModelAttribute("user") User user, Model model, @RequestParam("id") int id) {
			System.out.println("int the requesting");
			User user1=userRepository.getUserByUserId(id);
			user1.setGender(user.getGender());
			user1.setRequiredDate(user.getRequiredDate());
			user1.setBloodGroup(user.getBloodGroup());
			userRepository.save(user1);
			
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("age", age);
			model.addAttribute("title", "Edit_Profile");
			return "normal/user_dashboard";
		}
		
		@RequestMapping("/donate")
		public String donate(@ModelAttribute("user") User user, Model model) {
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears();
			model.addAttribute("age", age);
			model.addAttribute("title", "Edit_Profile");
			if(age<18) {
				System.out.println("user can't Donate the blood");
				return "normal/user_dashboard";
			}
				
			return "normal/donate";
		}
		
		@RequestMapping("/donating")
		public String donating(@ModelAttribute("user") User user, Model model, @RequestParam("id") int id, HttpSession session) throws Exception {
			LocalDate dob = LocalDate.parse(user.getDob());
			LocalDate curDate = LocalDate.now();
			int age = Period.between(dob, curDate).getYears(); 
			model.addAttribute("age", age);
			try {
				LocalDate dob1 = LocalDate.parse(user.getLastdateofDonation());
				int difference = Period.between(dob1, curDate).getMonths();
				if(difference<6) {throw new Exception("Sorry, you can't donate blood there should be difference of six months");}
				User user1=userRepository.getUserByUserId(id);
				user1.setGender(user.getGender());
				user1.setLastdateofDonation(user.getLastdateofDonation());
				user1.setBloodGroup(user.getBloodGroup());
				userRepository.save(user1);
				model.addAttribute("title", "Edit_Profile");
				return "normal/user_dashboard";
			}catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("user", user);
				model.addAttribute("title", "Edit_Profile");
				session.setAttribute("message",new Message(e.getMessage(),"alert-danger"));
				return "normal/donate";
			}
		}
		
		@RequestMapping("/admin_index/{page}")
		public String admin_index(@PathVariable Integer page,User user, HttpSession session,Model model) {
			int id=user.getId();
			int i=0;
			try {
				if(id!=i) {throw new Exception("Access Denied");}
				Pageable pageable=PageRequest.of(page,6);
				Page<User> data = userRepository.getAllData(pageable);
				model.addAttribute("data", data);
				model.addAttribute("currentPage", page);
				model.addAttribute("totalPages", data.getTotalPages());
//				model.addAttribute("id", id);
				return "normal/admin_index";
			}catch(Exception e) {
				session.setAttribute("message",new Message(e.getMessage(),"alert-danger"));
				return "normal/user_dashboard";
			}
			
		}
		

		
//		Deleting the profile from the admin page
		@RequestMapping("/delete_profile/{id}")
		public String delete_profile(@PathVariable("id") int id) {
			userRepository.deleteById(id);
			System.out.println("suraj");
			return "redirect:/user/admin_index/0";
		}
		
}














