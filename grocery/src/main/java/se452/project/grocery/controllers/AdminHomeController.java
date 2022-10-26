package se452.project.grocery.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.services.AccountService;

@Log4j2
@RequestMapping("/admin")
@Controller
public class AdminHomeController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/login")
	public String homePage(Account account, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		boolean loggedIn = accountService.loginAccount(account);
		log.info("account login state: "+loggedIn);
		if(loggedIn && (accountService.getRole(account)==Role.ADMIN)) {
			log.info("login success");
			model.addAttribute("msg", "Welcome Admin");
			session.setAttribute("msg", "hello");
			return "adminHomePage";
		}
		log.info("login failed");
		model.addAttribute("msg", "Wrong info");
		return "adminLoginPage";
	}
	
	@GetMapping("/homePage")
	public String homePageLanding() {
		return "adminHomePage";
	}
	
	
	

}
