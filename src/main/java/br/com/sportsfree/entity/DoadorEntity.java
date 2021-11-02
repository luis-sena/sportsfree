package br.com.sportsfree.entity;

import br.com.sportsfree.entity.embeddable.Contato;
import br.com.sportsfree.entity.embeddable.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "doador")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DoadorEntity that = (DoadorEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
