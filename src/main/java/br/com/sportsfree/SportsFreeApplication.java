package br.com.sportsfree;

import br.com.sportsfree.repository.AbstractRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = AbstractRepositoryImpl.class)
public class SportsFreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsFreeApplication.class, args);
    }

}
