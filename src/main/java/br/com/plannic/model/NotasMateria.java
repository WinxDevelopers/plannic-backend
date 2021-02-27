package br.com.plannic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notasmateria")
public class NotasMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotamateria")
    private int idNotaMateria;

    @Column(name = "idmateria")
    private int idMateria;

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "notamateria")
    private Double notaMateria;

    @Column(name = "tiponota")
    private String tipoNota;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy@hh:mm:ss")
    @Column(name = "datanota")
    private LocalDate dataNota;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference
    private Usuario usuario;

}
