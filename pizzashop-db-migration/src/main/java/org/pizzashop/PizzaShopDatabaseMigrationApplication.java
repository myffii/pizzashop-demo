package org.pizzashop;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaShopDatabaseMigrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(PizzaShopDatabaseMigrationApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, Flyway flyway) {
        return args -> {
            flyway.migrate();
            SpringApplication.exit(ctx, () -> 0);
        };
    }
}
