package se452.project.shop.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.entities.Item;
import se452.project.grocery.repos.ItemRepo;
import se452.project.grocery.services.AccountService;
import se452.project.grocery.services.ItemService;
import se452.project.shop.entities.CustomerAccount;
import se452.project.shop.entities.CustomerItem;
import se452.project.shop.services.CustomerAccountServices;

@Controller
public class ShopController {
	
	
	@Autowired
	ItemService itemService;
	@Autowired
	ItemRepo itemrepo;
	
	@Autowired
	CustomerAccountServices accountService;
	
	
	@RequestMapping("/home")
	public String home() {
	
		return "loginPage";
		
	}
	
	
	@RequestMapping("/shop")
	public String shop(Item item) {
		
		Item items = itemService.findItemByName(item.getName());
		items.setQuantity(items.getQuantity()+item.getQuantity());
		itemService.save(items);
		
		System.out.println(items.getName());
		
		return "RedirectPage";
		 
	}
	
	
	@PostMapping("/RedirectPage")
	public String homePage(CustomerAccount account, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		boolean loggedIn = accountService.loginAccount(account);
		List<Item> allItems = new ArrayList<>();
		allItems.addAll(itemrepo.findAll());
		if(loggedIn) {
			session.setAttribute("allItems",allItems);
			session.setAttribute("user", account.getUsername());
			return "RedirectPage";
		}
		model.addAttribute("msg", "Wrong info");
		return "loginPage";
	}
	
	@RequestMapping("/createShopAccount")
	public String shop() {
		
		return "createShopAccountPage";
		
	}
	
	
	@PostMapping("/createShopAccount")
	public String createAccount(CustomerAccount account, Model model) {

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
	
	
	@GetMapping("/RedirectPageNav")
	public String homePageNav() {
		return "RedirectPage";
	}
	
	@GetMapping("/shoppingCart")
	public String shoppingCart() {
		return "shoppingCartPage";
	}
	
	@GetMapping("/addToCart")
	public String addToCart(Item item, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		
		
		List<CustomerItem> userItems = new ArrayList<>();
		
		List<CustomerItem> user_Itemss = accountService.addItemToCart(user, item);
		
		if(user_Itemss!=null) {
		
		userItems.addAll(user_Itemss);
		System.out.println(userItems.size());
		session.setAttribute("userItemss", userItems); 
		}
		
		return "shoppingCartPage";
		
	}

}



 