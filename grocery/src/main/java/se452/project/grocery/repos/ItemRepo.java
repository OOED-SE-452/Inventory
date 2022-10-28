package se452.project.grocery.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se452.project.grocery.entities.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    public Item findItemByUid(Integer uid);
    public Item findItemByName(String name);
}
