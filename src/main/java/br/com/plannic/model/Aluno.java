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
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaluno")
    private int idAluno;

    @Column(name = "idusuarioaluno")
    private int idUsuarioAluno;

    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuarioaluno", insertable=false, updatable=false)
    private Usuario usuarioAluno;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idmateriabase", insertable=false, updatable=false)
    private MateriaBase materiaBase;
}