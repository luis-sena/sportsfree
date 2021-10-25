package br.com.sportsfree.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestParamException extends RuntimeException {

    public RequestParamException(String message) {
        super(message);
    }
}
