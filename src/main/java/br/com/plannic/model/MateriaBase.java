package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materiasbase")
public class MateriaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @Column(name = "materiabase")
    private String materiaBase;

    public MateriaBase(String materiaBase) {
        this.materiaBase=materiaBase;
    }
}
