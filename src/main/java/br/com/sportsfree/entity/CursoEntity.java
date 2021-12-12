package br.com.sportsfree.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sportsfree.entity.embeddable.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "curso")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEntity extends AbstractEntity<Long> {
    
    private String descricao;
    @JoinColumn(name="idProfessor")
    @ManyToOne  
    private ProfessorEntity professor;
    @JoinColumn(name="idEsporte")
    @ManyToOne
    private EsporteEntity esporte;
    @Embedded
    private Endereco local;
    private String dia;
    private String horario_inicio;
    private String horario_fim;
    private int quantidade_max_alunos;

}
