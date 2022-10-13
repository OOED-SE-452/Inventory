package se452.project.grocery.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import se452.project.grocery.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	
	public Account findAccountByEmail(String email);
}
