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
@Table(name = "notasTutor")
public class NotasTutor {

    @Id
    @GeneratedValue
    private int idNotaUsuario;

    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "idMateria")
    private int idMateria;

    @Column(name = "notaTutor")
    private Integer notaTutor;

    @Column(name = "descricaoNota")
    private String descricaoNota;

    @Column(name = "idUsuarioTutor")
    private int idUsuarioTutor;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable=false, updatable=false)
    private Usuario usuario;

}
