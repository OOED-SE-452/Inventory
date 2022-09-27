package se452.project.grocery.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.project.grocery.Entities.Account;
import se452.project.grocery.Repos.AccountRepo;

@Service
public class AccountService {

	@Autowired
	AccountRepo accountRepo;

	public boolean createAccount(Account account) {
		if (account != null) {
			if (accountRepo.findAccountByEmail(account.getEmail()) == null) {
				if (account.getEmail() != null && account.getPassword() != null) {
					accountRepo.save(account);
					return true;
				}
			}
		}
		return false;
	}

	public boolean loginAccount(Account account) {
		if (account != null) {
			if (accountRepo.findAccountByEmail(account.getEmail()) != null) {
				if (account.getPassword()
						.equals(accountRepo.findAccountByEmail(account.getEmail()).getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

}
