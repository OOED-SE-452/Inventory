package se452.project.grocery.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.project.grocery.entities.Item;
import se452.project.grocery.repos.ItemRepo;

@Service
public class ItemService {
	
	@Autowired
	ItemRepo itemRepo;
	
	public boolean createItem(Item item) {
		if(item != null) {
			if(itemRepo.findItemByName(item.getName())==null) {
				if(item.getName()!=null) {
                    if(item.getPrice()<0.00)
                        item.setPrice(0.00);
                    if(item.getQuantity()<0)
                        item.setQuantity(0);
					itemRepo.save(item);
					return true;
				}
			}
		}
		return false;
	}
	
    public boolean removeItem(Item item){
        if(item != null) {
			if(itemRepo.findItemByName(item.getName())==null) {
                itemRepo.delete(item);
					return true;				
			}
		}
		return false;
    }
    
    
    public Item findItemByName(String name) {
    	
    	if(itemRepo.findItemByName(name)!=null) {
    		return itemRepo.findItemByName(name);
    	}
    	return null;
    }

}
