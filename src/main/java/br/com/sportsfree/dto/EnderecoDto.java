package br.com.sportsfree.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {
    private String cep;
    private String cidade;
    private String uf;
    private String rua;
    private Integer numero;
    private String bairro;
    private String complemento;
}
