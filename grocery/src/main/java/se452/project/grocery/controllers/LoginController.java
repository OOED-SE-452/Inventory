package se452.project.grocery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.entities.Account;
import se452.project.grocery.services.AccountService;


@Controller
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/")
	public String loginPage() {
		return "loginPage";
	}
	
	@PostMapping("/login")
	public String homePage(Account account, Model model) {
		System.out.println(account.getEmail());
		System.out.println(account.getPassword());
		
		boolean loggedIn = accountService.loginAccount(account);
		if(loggedIn) {
			model.addAttribute("msg", "Welcome");
			return "homePage";
		}
		model.addAttribute("msg", "Wrong info");
		return "loginPage";
	}

}
