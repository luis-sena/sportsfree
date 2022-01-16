package br.com.sportsfree.dto;

import br.com.sportsfree.validate.constraints.UsuarioExistenteConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDto {

    @UsuarioExistenteConstraint
    private String email;
    private List<String> telefones;
    private String redeSocial;

}
