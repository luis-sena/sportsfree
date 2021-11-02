package br.com.sportsfree.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "esporte")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EsporteEntity extends AbstractEntity<Long> {
    
    private String nome;

}
