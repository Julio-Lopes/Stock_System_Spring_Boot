package br.com.springboot;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class SpringbootApplication {
    @Autowired
    private Flyway flyway;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Bean
    @Order(2) // Define a ordem de inicialização
    public CommandLineRunner flywayRunner() {
        return args -> {
            flyway.migrate(); // Executa as migrações do Flyway
        };
    }

    @Bean
    @Order(1) // Define a ordem de inicialização
    public CommandLineRunner hibernateRunner() {
        return args -> {
            // Nenhuma lógica relacionada ao Hibernate é necessária aqui
        };
    }
}
