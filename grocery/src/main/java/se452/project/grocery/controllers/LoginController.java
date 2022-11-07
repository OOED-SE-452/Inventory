package se452.project.grocery.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return "redirect:/";
	}
// 	@RequestMapping("/error")
// 	public String handleError(HttpServletRequest request) {
// 	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
   
// 	if (status != null) {
// 		Integer statusCode = Integer.valueOf(status.toString());
   
// 		if(statusCode == HttpStatus.NOT_FOUND.value()) {
// 			return "error-404";
// 		}
// 		else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
// 			return "error-401";
// 		}
// 	}
// 	return "loginPage";
//    }
}
