 package se452.project.grocery;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.extern.log4j.Log4j2;
import se452.project.grocery.entities.*;

@Log4j2
@SpringBootApplication
//@EnableJpaRepositories
@EnableMongoRepositories
public class GroceryApplication {

	
	/*
	 * @Value("${}") private String env;
	 */
	
	
	public static void main(String[] args) {
		SpringApplication.run(GroceryApplication.class, args);	
		
		
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


