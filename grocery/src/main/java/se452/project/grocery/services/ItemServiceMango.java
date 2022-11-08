package se452.project.grocery.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.project.grocery.entities.ItemMango;
import se452.project.grocery.repos.ItemMangoRepo;

@Service
public class ItemServiceMango {
	
	@Autowired
	ItemMangoRepo itemRepo;
	
	public boolean createItem(ItemMango item) {
		
		ItemMango findItem = itemRepo.findItemMangoByName(item.getName());
		
		if(item != null) {
			if(findItem == null) {
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
	
    public boolean removeItem(ItemMango item){
    	
    	ItemMango findItem = itemRepo.findItemMangoByName(item.getName());
    	System.out.println(findItem);
        if(item != null) {
			itemRepo.delete(findItem);
			return true;
		}
		return false;
    }
    
    
    public ItemMango findItemByName(String name) {
    	
    	ItemMango findItem = itemRepo.findItemMangoByName(name);
    	
    	if(findItem!=null) {
    		return findItem;
    	}
    	return null;
    }

	public void save(ItemMango item) {
		ItemMango i = itemRepo.findItemMangoByName(item.getName());
		if(i!=null) {
			i.setName(item.getName());
			i.setPrice(item.getPrice());
			i.setQuantity(item.getQuantity());
			itemRepo.save(i);
		}
	}
	
	public List<ItemMango> findAll(){
		
		return itemRepo.findAll();
		
	}

}
