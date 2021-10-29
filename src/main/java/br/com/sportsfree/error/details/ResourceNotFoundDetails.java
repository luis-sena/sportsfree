package br.com.sportsfree.error.details;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResourceNotFoundDetails extends ErrorDetails {

    @Builder
    public ResourceNotFoundDetails(String titulo, int status, String detalhes, LocalDateTime timestamp, String mensagem) {
        super(titulo, status, detalhes, timestamp, mensagem);
    }
}
