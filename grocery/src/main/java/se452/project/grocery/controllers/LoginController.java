package se452.project.grocery.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
	
	
	@RequestMapping("/")
	public String loginPage() {
		return "loginPage";
	}
	
	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/";
	}

}
