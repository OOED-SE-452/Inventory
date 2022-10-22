package se452.project.grocery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.project.grocery.entities.Item;
import se452.project.grocery.repos.ItemRepo;
import se452.project.grocery.services.ItemService;

public class ShopController {
	
	@Autowired
	ItemService itemService;
	@Autowired
	ItemRepo itemrepo;
	
	@RequestMapping("/inventoryHome")
	public String shop(Model model) {
		
		Item item = (Item)model.getAttribute("item");
		Item i2e = itemService.findItemByName(item.getName());
		
		i2e.setQuantity(i2e.getQuantity()-1);
		
		itemService.save(i2e);
		
		return "searchItemPage";
		 
	}

}
