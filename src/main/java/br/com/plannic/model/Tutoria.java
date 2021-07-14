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
@Table(name = "tutoria")
public class Tutoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtutoria")
    private int idTutoria;

    @Column(name = "idusuarioaluno")
    private int idUsuarioAluno;

    @Column(name = "idusuariotutor")
    private int idUsuarioTutor;

    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuarioaluno", insertable=false, updatable=false)
    private Usuario usuarioAluno;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuariotutor", insertable=false, updatable=false)
    private Usuario usuarioTutor;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idmateriabase", insertable=false, updatable=false)
    private MateriaBase materiaBase;
}