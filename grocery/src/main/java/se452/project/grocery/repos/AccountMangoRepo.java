package se452.project.grocery.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import se452.project.grocery.entities.AccountMango;

public interface AccountMangoRepo extends MongoRepository<AccountMango, String> {

	@Query("{email:'?0'}")
	public AccountMango findAccountMangoByEmail(String email);

	@Query("{uid:'?0'}")
	public AccountMango findAccountMangoByUid(String uid);

}
