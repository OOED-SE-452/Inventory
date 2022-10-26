package se452.project.grocery.controllers;

import java.sql.SQLException;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.services.AccountService;
// @RequestMapping("/admin")
@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/createAccount/adminAccount")
	public String createAdminAccount(Account account, Model model) {
		if(account==null) return "createAccountPage";
		account.setRole(Role.ADMIN);
		return createAccount(account,model);
	}
	@PostMapping("/createAccount/userAccount")
	public String createUserAccount(Account account, Model model) {
		if(account==null) return "createAccountPage";
		account.setRole(Role.USER);
		return createAccount(account,model);
	}
	public String createAccount(Account account, Model model) {
		boolean accountCreated = accountService.createAccount(account);
		if(accountCreated) {
			model.addAttribute("createAccountStatus", "Account created!");
		}
		else {
			model.addAttribute("createAccountStatus", "Account already exist!");
			return "createAccountPage";
		}
		return "loginPage";
	}

	@GetMapping("/createAccount")
	public String createAccountPage() {
		return "createAccountPage";
	}
}

