package se452.project.grocery.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import se452.project.grocery.entities.Item;
import se452.project.grocery.entities.ItemMango;

@Repository
public interface ItemMangoRepo extends MongoRepository<ItemMango, String> {
    public ItemMango findItemMangoByUid(Integer uid);
    public ItemMango findItemMangoByName(String name);
}
