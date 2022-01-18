package br.com.sportsfree.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

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
    @JsonFormat(pattern="HH:mm")
    private LocalTime horarioInicio;
    @JsonFormat(pattern="HH:mm")
    private LocalTime horarioFim;
    private int quantidadeMaxAlunos;
}
