package br.com.sportsfree.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto extends AbstractDto{
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private EnderecoDto endereco;
}
