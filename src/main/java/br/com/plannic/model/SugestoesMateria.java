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
@Table(name = "sugestoesmateria")
public class SugestoesMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsugestoesmateria")
    private int idSugestoesMateria;

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "materia")
    private String nomeMateria;

    @Column(name = "votos")
    private int votos;

    @Column(name = "faltavotar")
    private String faltaVotar;

    @Column(name = "totalvotos")
    private int totalVotos;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference
    private Usuario usuario;

    public SugestoesMateria(String nomeMateria) {
        this.nomeMateria=nomeMateria;
    }
}