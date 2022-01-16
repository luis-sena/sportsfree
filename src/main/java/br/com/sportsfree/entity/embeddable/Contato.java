package br.com.sportsfree.entity.embeddable;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Contato implements Serializable {

    @Column(updatable = false)
    private String email;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ElementCollection
    @Builder.Default
    private List<String> telefones = new ArrayList<>();
    private String redeSocial;

    public void addTelefone(String telefone) {
        this.telefones.add(telefone);
    }

    public List<String> getTelefones() {
        return Collections.unmodifiableList(this.telefones);
    }
}
