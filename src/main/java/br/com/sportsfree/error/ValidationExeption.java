package br.com.sportsfree.error;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidationExeption extends RuntimeException {

    public ValidationExeption(String message) {
        super(message);
    }
}
