package main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.javacode"})
@EntityScan("com.javacode.model")
@EnableJpaRepositories("com.javacode.repository")
public class SpringMainApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringMainApp.class, args);
    }
}
