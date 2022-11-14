package se452.project.grocery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.extern.log4j.Log4j2;
import se452.project.grocery.entities.*;
import se452.project.grocery.repos.AccountMangoRepo;
import se452.project.grocery.services.AccountServiceMango;

@Log4j2
@SpringBootApplication
// @EnableJpaRepositories
@EnableMongoRepositories
public class GroceryApplication {

	@Autowired
	AccountMangoRepo accountRepo;

	@Autowired
	AccountServiceMango accountService;

	/*
	 * @Value("${}") private String env;
	 */

	public static void main(String[] args) {
		SpringApplication.run(GroceryApplication.class, args);

	}

	@Bean
	CommandLineRunner loadAdmin() throws Exception {
		return args -> {
			log.info("check admin...");

			if (accountService.getAccount("10000") == null) {
				AccountMango admin = new AccountMango();
				admin.setEmail("1234@1234");
				admin.setRole(Role.ADMIN);
				admin.setPassword("pqjpLXKt1RKmXrTR");
				admin.setVerified("809878d680061eb986b5fee46e4e7a127e9379630640a0bb55a833614c087d4f");
				admin.setUid("10000");
				log.info(accountRepo.save(admin).toString());
				log.info("create admin");
			} else {
				log.info("has admin");
			}

		};
	}

	@Bean
	public CommandLineRunner showLogLevel() {
		return (args) -> {
			// System.out.println(env);
			log.debug("Debug");
			log.info("Info");
			log.warn("Warning");
			log.error("Error");
		};
	}

}
