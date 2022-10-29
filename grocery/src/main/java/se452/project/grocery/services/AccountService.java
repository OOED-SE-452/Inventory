package se452.project.grocery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.repos.AccountRepo;

@Service
public class AccountService {
	
	@Autowired
	AccountRepo accountRepo;
	
	public boolean createAccount(Account account) {
		
		Account findAccount = accountRepo.findAccountByEmail(account.getEmail());
		
		if(account != null) {
			if(findAccount == null) {
				if(account.getEmail()!=null && account.getPassword()!=null) {
					account.setRole(Role.USER);
					accountRepo.save(account);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean loginAccount(Account account) {
		
		Account findAccount = accountRepo.findAccountByEmail(account.getEmail());
		
		if(account != null) {
			if(findAccount!=null) {
				//System.out.println("Account pass: " + account.getPassword());
				if(account.getPassword()
						.equals(findAccount.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Role getRole(Account account) {
		
		Account findAccount = accountRepo.findAccountByEmail(account.getEmail());
		
		if(account != null) {
			if(findAccount!=null) {
				//System.out.println("Account pass: " + account.getPassword());
				if(findAccount.getRole().equals(Role.ADMIN)) {
					return Role.ADMIN;
				}
				
				else if (findAccount.getRole().equals(Role.USER)) {
					return Role.USER;
				}
			}
		}
		return null;
	}

}
