package br.com.sportsfree.entity;

import br.com.sportsfree.entity.embeddable.Contato;
import br.com.sportsfree.entity.embeddable.Endereco;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "doador")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DoadorEntity extends AbstractEntity<Long>{

    private String nome;
    private String cpfCnpj;
    private String rg;
    @Lob
    private String observacoes;
    @Embedded
    private Endereco endereco;
    @Embedded
    private Contato contato;
}
