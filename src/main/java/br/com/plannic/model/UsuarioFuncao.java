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
@Table(name = "usuariofuncao")
public class UsuarioFuncao {

    @Id
    @Column(name = "idusuariofuncao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioFuncao;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference
    private Usuario usuario;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idfuncao", insertable=false, updatable=false)
    @JsonBackReference
    private Funcao funcao;

}
