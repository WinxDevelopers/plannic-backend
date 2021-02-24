package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notasTutor")
public class NotasTutor {

    @Id
    @GeneratedValue
    private Long idNotaUsuario;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "idMateira")
    private Long idMateira;

    @Column(name = "notaTutor")
    private Integer notaTutor;

    @Column(name = "descricaoNota")
    private String descricaoNota;

    @Column(name = "idUsuarioTutor")
    private Long idUsuarioTutor;
}
