package br.com.sportsfree.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco implements Serializable {
    private String cep;
    private String cidade;
    private String uf;
    private String rua;
    private Integer numero;
    private String bairro;
    private String complemento;
}
