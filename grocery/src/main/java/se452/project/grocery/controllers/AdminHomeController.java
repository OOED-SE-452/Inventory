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
		int uid = accountService.loginAccount(account);
		boolean loggedIn = uid>=0;
		account = accountService.getAccount(uid);
		if(loggedIn && account.getRole()==Role.ADMIN) {
			req.getSession().setAttribute("UID",uid);
			return "adminHomePage";
		}
		model.addAttribute("msgAdmin", "Wrong info");
		return "loginPage";
	}
	
	@GetMapping("/homePage")
	public String homePageLanding(Model model, HttpSession  session) {
		try{
			Object obj = session.getAttribute("UID");
		
			if(accountService.getAccount((int)obj).getRole()==Role.ADMIN)
				return "adminHomePage";
			return "redirect:/";

		}
		catch(Exception e){
			return "redirect:/";
		}
	}
	
	
	

}
