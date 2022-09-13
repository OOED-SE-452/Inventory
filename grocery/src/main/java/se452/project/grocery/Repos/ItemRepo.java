package se452.project.grocery.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import se452.project.grocery.Entities.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
