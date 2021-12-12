package br.com.sportsfree.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto extends AbstractDto {
    private String descricao;
    private ProfessorDto professor;
    private EsporteDto esporte;
    private EnderecoDto local;
    private String dia;
    private String horario_inicio;
    private String horario_fim;
    private int quantidade_max_alunos;
}
