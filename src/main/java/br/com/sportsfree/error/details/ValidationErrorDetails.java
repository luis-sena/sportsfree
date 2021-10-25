package br.com.sportsfree.error.details;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ValidationErrorDetails extends ErrorDetails {
    private String fields;

    @Builder
    private ValidationErrorDetails(String titulo, int status, String detalhes, LocalDateTime timestamp, String mensagem, String fields) {
        super(titulo, status, detalhes, timestamp, mensagem);
        this.fields = fields;
    }
}
