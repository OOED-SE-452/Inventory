package se452.project.grocery.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.entities.AccountMango;
import se452.project.grocery.services.AccountService;
import se452.project.grocery.services.AccountServiceMango;

@Log4j2
@RequestMapping("/user")
@Controller
public class HomeController {
	
	@Autowired
	AccountServiceMango accountService;
	
	@PostMapping("/login")
	public String homePage(AccountMango account, Model model, HttpServletRequest req) {
		// HttpSession session = req.getSession();
		
		// boolean loggedIn = accountService.loginAccount(account);
		// log.info("account login state: "+loggedIn);

		// if(loggedIn && (accountService.getRole(account)==Role.USER)) {
		// 	log.info("login success");
		// 	model.addAttribute("msg", "Welcome USER");
		// 	session.setAttribute("msg", "hello");
		// 	return "homePage";
		// }
		// log.info("login failed");
		// model.addAttribute("msg", "Wrong info");
		return "loginPage";
	}
	
	
	

	
	

}
