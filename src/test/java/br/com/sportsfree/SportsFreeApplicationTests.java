package br.com.sportsfree;


import br.com.sportsfree.config.JpaConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SportsFreeApplicationTests {

    @Test
    void contextLoads() {
        assertThat(Boolean.TRUE).isTrue();
    }
}
