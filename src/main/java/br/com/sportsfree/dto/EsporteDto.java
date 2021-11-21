package br.com.sportsfree.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class EsporteDto extends AbstractDto {
    private String nome;
    private String descricao;
    private String urlImagem;
}
