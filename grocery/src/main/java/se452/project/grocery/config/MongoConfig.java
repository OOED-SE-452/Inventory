package se452.project.grocery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class MongoConfig {

    @Autowired
    private Environment env;

    @Bean
    public MongoDatabaseFactory mongoDbFactorys() {
        MongoDatabaseFactory factorys = new SimpleMongoClientDatabaseFactory(
                env.getProperty("spring.data.mongodb.uri"));
        log.info("new DB factorys");
        return factorys;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactorys());
        log.info("new mongoTemplate " + mongoTemplate.getDb().getName());
        return mongoTemplate;

    }

}