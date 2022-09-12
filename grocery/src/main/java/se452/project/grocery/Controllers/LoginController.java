package se452.project.grocery.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String loginPage() {
		return "loginPage";
	}
	
	@PostMapping("/login")
	public String homePage(String email, String password, Model model) {
		System.out.println(email);
		System.out.println(password);
		System.out.println(model);
		model.addAttribute("email", "TETS");
		return "homePage";
	}

}
