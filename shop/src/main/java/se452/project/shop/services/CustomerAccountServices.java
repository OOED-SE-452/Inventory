package se452.project.shop.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.entities.Item;
import se452.project.grocery.repos.ItemRepo;
import se452.project.shop.entities.CustomerAccount;
import se452.project.shop.entities.CustomerItem;
import se452.project.shop.repos.CustomerAccountRepos;
import se452.project.shop.repos.CustomerItemListRepo;


@Service
public class CustomerAccountServices {

	@Autowired
	CustomerAccountRepos accountRepos;
	
	@Autowired
	CustomerItemListRepo customerItemListRepo;
	
	
	@Autowired
	ItemRepo itemRepo;
	
	public boolean loginAccount(CustomerAccount account) {
		if(account != null) {
			if(accountRepos.findCustomerAccountByUsername(account.getUsername())!=null) {
				if(account.getPassword()
						.equals(accountRepos.findCustomerAccountByUsername(account.getUsername()).getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean createAccount(CustomerAccount account) {
		if(account != null) {
			if(accountRepos.findCustomerAccountByUsername(account.getUsername())==null) {
				if(account.getUsername()!=null && account.getPassword()!=null) {
					accountRepos.save(account);
					return true;
				}
			}
		}
		return false;
	}

	public List<CustomerItem> addItemToCart(String username, Item item) {
		CustomerItem items = new CustomerItem();
		
		CustomerAccount customerAccount = accountRepos.findCustomerAccountByUsername(username);

		
		Item itemss = itemRepo.findItemByName(item.getName());
		itemss.setQuantity(itemss.getQuantity()-1);
		itemRepo.save(itemss);
		
		items.setName(itemss.getName());
		items.setPrice(itemss.getPrice());
		items.setQuantity(itemss.getQuantity());
		customerItemListRepo.save(items);
		
		customerAccount.getShoppingCart().add(items);
		System.out.println(customerAccount.toString());
		System.out.println("-----------------------------------------------------------------------------------");
		if(customerAccount != null) {
			accountRepos.save(customerAccount);
			
			return customerAccount.getShoppingCart();
		}
		return null; 
		
	}
	
	
	
}
