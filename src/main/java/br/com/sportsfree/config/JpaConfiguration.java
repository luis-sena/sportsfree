package br.com.sportsfree.config;


import br.com.sportsfree.repository.AbstractRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Primary
@Configuration
@EnableJpaRepositories(repositoryBaseClass = AbstractRepositoryImpl.class, basePackages = "br.com.sportsfree.repository")
public class JpaConfiguration {}
