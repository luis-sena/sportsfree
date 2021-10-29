package br.com.sportsfree.entity;

import br.com.sportsfree.entity.embeddable.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "professor")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEntity extends AbstractEntity<Long>{

    private String nome;
    private String email;
    private String cpf;
    private String rg;
    @Embedded
    private Endereco endereco;
}
