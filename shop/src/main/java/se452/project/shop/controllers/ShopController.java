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

import se452.project.grocery.entities.ItemMango;
import se452.project.grocery.repos.ItemMangoRepo;
import se452.project.grocery.services.ItemServiceMango;
import se452.project.shop.entities.CustomerMangoAccount;
import se452.project.shop.entities.CustomerMangoItem;
import se452.project.shop.repos.CustomerAccountMangoRepo;
import se452.project.shop.services.CustomerAccountMangoServices;

@Controller
public class ShopController {
	
	
	@Autowired
	ItemServiceMango itemService;
	
	@Autowired
	ItemMangoRepo itemrepo;
	
	@Autowired
	CustomerAccountMangoRepo customerRepo;
	
	@Autowired
	CustomerAccountMangoServices accountService;
	
	
	@RequestMapping("/home")
	public String home() {
	
		return "loginPage";
		
	}
	
	
	@RequestMapping("/shop")
	public String shop(ItemMango item) {
		
		ItemMango items = itemService.findItemByName(item.getName());
		items.setQuantity(items.getQuantity()+item.getQuantity());
		itemService.save(items);
		
		System.out.println(items.getName());
		
		return "RedirectPage";
		 
	}
	
	
	@PostMapping("/RedirectPage")
	public String homePage(CustomerMangoAccount account, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		String state = accountService.loginAccount(account);
		boolean loggedIn = state!="";
		
		List<ItemMango> allItems = new ArrayList<>();
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
	public String createAccount(CustomerMangoAccount account, Model model) {

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
	public String shoppingCart(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		String user = (String) session.getAttribute("user");
		
		CustomerMangoAccount ca = customerRepo.findCustomerMangoAccountByUsername(user);
		System.out.println(ca);
		session.setAttribute("allCusItems", ca.getShoppingCart());
		
		return "shoppingCartPage";
	}
	
	@GetMapping("/addToCart")
	public String addToCart(ItemMango item, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		
		
		List<CustomerMangoItem> userItems = new ArrayList<>();
		
		List<CustomerMangoItem> user_Itemss = accountService.addItemToCart(user, item);
		
		if(user_Itemss!=null) {
		
		userItems.addAll(user_Itemss);
		System.out.println(userItems.size());
		session.setAttribute("allCusItems", userItems); 
		}
		
		return "shoppingCartPage";
		
	}

}



 