package br.com.sportsfree.dto;

import br.com.sportsfree.validate.constraints.UsuarioExistenteConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class DoadorDto extends AbstractDto{
    private String nome;
    private String password;
    private String cpfCnpj;
    private String rg;
    private EnderecoDto endereco;
    private ContatoDto contato;
}
