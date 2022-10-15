package se452.project.grocery.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "loginPage";
	}

}
