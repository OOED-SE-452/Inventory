package se452.project.grocery.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.DeleteMapping; 
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.entities.Item;
import se452.project.grocery.services.ItemService;

@RequestMapping("/admin")
@Controller
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	 @PostMapping("/createItem")
	public ModelAndView createItem(Item item, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddNewItem");
        
		boolean itemCreated = itemService.createItem(item);
		if(itemCreated) {
			model.addAttribute("itemAddInfo", "item created!");
		}
		else {
			model.addAttribute("itemAddInfo", "item already exist!");
		}
		return mv;
	}
	


    @DeleteMapping("/deleteItem")  
    private void removeItem(Item item, Model model)   
    {  
        itemService.removeItem(item);  
    } 

	@RequestMapping("/AddNewItemPage")
	public String createAccountPage() {
		return "AddNewItem";
	}
	
	@RequestMapping("/SearchItemPage")
	public String searchItemPage(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<Item> items = itemService.findAll();
		model.addAttribute("items", items);
		session.setAttribute("items", items);
		return "searchItemPage";
	}
	
	@RequestMapping("/searchItem")
	public String returnSearchItem(String name, Model model){
		Item item = itemService.findItemByName(name);
		
		model.addAttribute("item", item);
		
		return "searchItemPage";
	}
	
	@RequestMapping("/editItem")
	public String editItem(Item item) {
		
		itemService.save(item);
		
		return "searchItemPage";
		
	}
	
}
