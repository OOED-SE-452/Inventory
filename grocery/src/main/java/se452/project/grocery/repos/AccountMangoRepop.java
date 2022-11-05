//package se452.project.grocery.repos;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import se452.project.grocery.entities.Account;
//
//
//public interface AccountMangoRepop extends MongoRepository<Account, Integer>{
//	
//	@Query("{name:'?0'}")
//	public Account findAccountByEmail(String email);
//
//}
