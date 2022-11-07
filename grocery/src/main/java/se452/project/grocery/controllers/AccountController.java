package se452.project.grocery.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.entities.AccountMango;
import se452.project.grocery.services.AccountService;
import se452.project.grocery.services.AccountServiceMango;
@RequestMapping("/admin")
@Controller
public class AccountController {
	
	@Autowired
	AccountServiceMango accountService;
	
	@PostMapping("/createAccount")
	public String createAccount(@Valid @ModelAttribute("account") AccountMango account, BindingResult bindResult, Model model) {

		if(bindResult.hasErrors()) {
			return "createAccountPage"; 
		}
		
		boolean accountCreated = accountService.createAccount(account);
		if(accountCreated) {
			model.addAttribute("createAccountStatus", "Account created!");
		}
		else {
			model.addAttribute("createAccountStatus", "Account already exist!");
			return "createAccountPage";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/createAccountPage")
	public String createAccountPage(Model model) {
		model.addAttribute("account", new Account());
		return "createAccountPage";
	}
}

