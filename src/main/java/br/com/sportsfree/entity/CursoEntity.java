package br.com.sportsfree.entity;

import javax.persistence.*;

import br.com.sportsfree.entity.embeddable.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "curso")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEntity extends AbstractEntity<Long> {
    
    private String descricao;
    @ManyToOne
    @JoinColumn(name="id_professor")
    private ProfessorEntity professor;
    @ManyToOne
    @JoinColumn(name="id_esporte")
    private EsporteEntity esporte;
    @Embedded
    private Endereco local;
    private String dia;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private int quantidadeMaxAlunos;

}
