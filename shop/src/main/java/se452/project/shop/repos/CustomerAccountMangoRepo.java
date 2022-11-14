package se452.project.shop.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import se452.project.shop.entities.CustomerMangoAccount;

@Repository
public interface CustomerAccountMangoRepo extends MongoRepository<CustomerMangoAccount, String> {

	@Query("{username:'?0'}")
	public CustomerMangoAccount findCustomerMangoAccountByUsername(String username);

	@Query("{cid:'?0'}")
	public CustomerMangoAccount findCustomerMangoAccountByCid(String cid);

}
