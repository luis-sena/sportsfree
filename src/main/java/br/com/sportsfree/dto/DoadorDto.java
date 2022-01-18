package br.com.sportsfree.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class DoadorDto extends AbstractDto{
    private String nome;
    private String cpfCnpj;
    private String rg;
    private EnderecoDto endereco;
    @Valid
    private ContatoDto contato;
}
