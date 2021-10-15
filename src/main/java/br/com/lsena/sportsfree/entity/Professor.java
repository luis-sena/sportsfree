package br.com.lsena.sportsfree.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends AbstractEntity<Long>{

    private String nome;
}
