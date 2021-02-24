package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notasMateria")
public class NotasMateria {

    @Id
    @GeneratedValue
    private Long idNotaMateria;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "idMateira")
    private Long idMateira;

    @Column(name = "notaMateria")
    private Integer notaMateria;

    @Column(name = "tipoNota")
    private String tipoNota;

    @Column(name = "dataNascimento")
    private String dataNascimento;


}
