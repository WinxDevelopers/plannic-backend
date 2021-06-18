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
    @Column(name = "idmateriabase")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateriaBase;

    @Column(name = "materiabase")
    private String materiabase;
}
