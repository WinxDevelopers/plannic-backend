package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notastutor")
public class NotasTutor {

    @Id
    @GeneratedValue
    @Column(name = "idnotatutor")
    private int idNotaTutor;

    @Column(name = "idusuarioaluno")
    private int idUsuarioAluno;

    @Column(name = "idusuariotutor")
    private int idUsuarioTutor;

    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @Column(name = "notatutor")
    private Integer notaTutor;

    @Column(name = "descricaonota")
    private String descricaoNota;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    private Usuario usuarioAluno;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    private Usuario usuarioTutor;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idmateriabase", insertable=false, updatable=false)
    private MateriaBase materiaBase;

}