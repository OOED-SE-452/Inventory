package se452.project.grocery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.entities.Account;
import se452.project.grocery.services.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/createAccount")
	public String createAccount(Account account, Model model) {

		boolean accountCreated = accountService.createAccount(account);
		if (accountCreated) {
			model.addAttribute("createAccountStatus", "Account created!");
		} else {
			model.addAttribute("createAccountStatus", "Account already exist!");
			return "createAccountPage";
		}
		return "loginPage";
	}

	@RequestMapping("/createAccountPage")
	public String createAccountPage() {
		return "createAccountPage";
	}

}
