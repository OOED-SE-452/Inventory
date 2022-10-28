package se452.project.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.shop.entities.CustomerAccount;
import se452.project.shop.repos.CustomerAccountRepos;


@Service
public class CustomerAccountServices {

	@Autowired
	CustomerAccountRepos accountRepos;
	
	
	public boolean loginAccount(CustomerAccount account) {
		if(account != null) {
			if(accountRepos.findCustomerAccountByUsername(account.getUsername())!=null) {
				//System.out.println("Account pass: " + account.getPassword());
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
	
}
