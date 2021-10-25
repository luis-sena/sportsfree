package br.com.sportsfree.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorSaidaDto extends AbstractDto{
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String cep;
    private String cidade;
    private String uf;
    private String rua;
    private Integer numero;
    private String bairro;
    private String complemento;
}
