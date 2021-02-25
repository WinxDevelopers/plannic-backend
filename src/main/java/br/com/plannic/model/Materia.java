package br.com.plannic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "materia")
    private String materia;

    @Column(name = "descricao")
    private String descricao;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference
    private Usuario usuario;

}
