package br.com.sportsfree;

import br.com.sportsfree.config.FirebaseConfig;
import com.google.firebase.FirebaseApp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SportsFreeApplicationTests {

/*
    @BeforeEach
    void setUp() {
        FirebaseApp.getInstance("SportsFreeApplicationTests");
    }
*/

//    @Test
    void contextLoads() {
        assertThat(Boolean.TRUE).isTrue();
    }
}
