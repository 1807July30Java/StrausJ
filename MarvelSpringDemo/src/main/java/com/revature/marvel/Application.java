package com.revature.marvel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(HeroRepository repository) {
        return args -> {
            repository.save(new Hero("Tim", "Allen"));
            repository.save(new Hero("Dean", "Winchester"));
            repository.save(new Hero("Aretha", "Franklin"));
            repository.save(new Hero("Captain", "America"));
            repository.save(new Hero("Wade", "Wilson"));
            repository.save(new Hero("Ms.", "Higgins"));
            repository.save(new Hero("Sam", "Sato"));
            repository.save(new Hero("Tim", "Hortons"));
            repository.save(new Hero("Dunkin", "Doughnuts"));
            repository.save(new Hero("Grandmaster", "Tep"));
        };
    }
}
