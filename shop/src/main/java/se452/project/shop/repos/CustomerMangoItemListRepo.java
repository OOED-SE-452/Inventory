package se452.project.shop.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import se452.project.shop.entities.CustomerMangoItem;

public interface CustomerMangoItemListRepo extends MongoRepository<CustomerMangoItem, String> {

	public CustomerMangoItem findCustomerMangoItemByName(String name);

	
}
