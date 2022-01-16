package br.com.sportsfree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportsFreeApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(SportsFreeApplication.class, args);
        /*Map<String, Object> claims = new HashMap<>();
        claims.put("scope", "ADMIN");
        FirebaseAuth.getInstance().setCustomUserClaims("UCzD0DrxV5ZjauPrca5zLUZg8J42", claims);*/
    }

}
