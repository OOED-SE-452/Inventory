package se452.project.grocery.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.DeleteMapping; 
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Item;

import se452.project.grocery.entities.ItemMango;

import se452.project.grocery.services.AccountService;
import se452.project.grocery.services.AccountServiceMango;
import se452.project.grocery.services.ItemService;
import se452.project.grocery.services.ItemServiceMango;

@RequestMapping("/admin")
@Controller
public class ItemController {
	
	@Autowired
	ItemServiceMango itemService;
	
	@Autowired
	AccountServiceMango accountService;

	 @PostMapping("/createItem")
	public ModelAndView createItem(@Valid ItemMango item, BindingResult result, Model model) {
		 ModelAndView mv = new ModelAndView();
	        mv.setViewName("AddNewItem");

		 if(result.hasErrors()) {
			 return mv;
		 }
		 
       
		boolean itemCreated = itemService.createItem(item);
		
		  if(itemCreated)
		  { model.addAttribute("itemAddInfo", "Created!"); } 
		  else
		  { model.addAttribute("itemAddInfo", "Item exist!"); }
		 
		return mv;
	}
	


    @RequestMapping("/deleteItem")  
    private String removeItem(ItemMango item, Model model)   
    {  
    	System.out.println(item);
        itemService.removeItem(item);  
        
        return "redirect:/admin/SearchItemPage";
    } 

	@RequestMapping("/AddNewItemPage")
	public String createAccountPage(Model model, HttpServletRequest req) {
		try{
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("UID");
		
			if(accountService.getAccount((String)obj).getRole()==Role.USER)
				return "redirect:/";
			model.addAttribute("item", new ItemMango());
			return "AddNewItem";

		}
		catch(Exception e){
			return "redirect:/";
		}
	}
	
	@RequestMapping("/SearchItemPage")
	public String searchItemPage(Model model, HttpServletRequest req) {
		try{
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("UID");
		
			if(accountService.getAccount((String)obj).getRole()==Role.USER)
				return "redirect:/";
			model.addAttribute("item", new Item());
			List<ItemMango> items = itemService.findAll();
			model.addAttribute("items", items);
			session.setAttribute("items", items);
			return "searchItemPage";

		}
		catch(Exception e){
			return "redirect:/";
		}
		

	}
	
	@RequestMapping("/searchItem")
	public String returnSearchItem(String name, Model model){
		ItemMango item = itemService.findItemByName(name);
		
		model.addAttribute("item", item);
		
		return "searchItemPage";
	}
	
	@RequestMapping("/editItem")
	public String editItem(ItemMango item, Model model) {
		//model.addAttribute("item", new Item());
		try{
			System.out.println(item);
			itemService.save(item);
		}
		catch(Exception e){}
		return "redirect:/admin/SearchItemPage";

	}
	
}
