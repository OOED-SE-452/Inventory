package se452.project.grocery.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import se452.project.grocery.Entities.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{

}
