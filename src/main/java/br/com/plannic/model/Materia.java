package br.com.plannic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmateria")
    private int idMateria;

    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "materia")
    private String nomeMateria;

    @Column(name = "descricao")
    private String descricao;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idmateriabase", insertable=false, updatable=false)
    @JsonBackReference
    private MateriaBase materiaBase;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference
    private Usuario usuario;

    public Materia(String nomeMateria) {
        this.nomeMateria=nomeMateria;
    }
}
