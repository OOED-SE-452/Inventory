package se452.project.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se452.project.shop.entities.CustomerAccount;

@Repository
public interface CustomerAccountRepos extends JpaRepository<CustomerAccount, Integer> {
	
	public CustomerAccount findCustomerAccountByUsername(String username);
	public CustomerAccount findCustomerAccountByCid(int cid);

}
