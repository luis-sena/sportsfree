package br.com.sportsfree.dto;

import br.com.sportsfree.validate.constraints.UsuarioExistenteConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto extends AbstractDto{
    private String nome;
    @UsuarioExistenteConstraint
    private String email;
    private String password;
    private String cpf;
    private String rg;
    private EnderecoDto endereco;
}
