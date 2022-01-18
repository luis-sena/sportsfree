package br.com.sportsfree.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "esporte")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EsporteEntity extends AbstractEntity<Long> {
    
    private String nome;
    @Lob
    private String descricao;
    private String urlImagem;

}
