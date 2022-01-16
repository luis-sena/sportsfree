package br.com.sportsfree.config;

import com.google.firebase.FirebaseApp;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

    @SneakyThrows
    public FirebaseConfig() {
        FirebaseApp.initializeApp();
    }
}
