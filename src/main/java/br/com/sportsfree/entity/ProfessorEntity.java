package br.com.sportsfree.entity;

import br.com.sportsfree.entity.embeddable.Endereco;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEntity extends AbstractEntity<Long>{

    private String nome;
    @Column(updatable = false)
    private String email;
    private String cpf;
    private String rg;
    @Embedded
    private Endereco endereco;
}
