package se452.project.grocery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.DeleteMapping; 
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.Entities.Item;
import se452.project.grocery.Services.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	 @PostMapping("/createItem")
	public ModelAndView createItem(Item item, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddNewItem");

		System.out.println(item.getName());
		System.out.println(item.getPrice());
		
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
}
