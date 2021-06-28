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

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "idfuncao")
    private int idfuncao;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference(value="idusuariofuncao")
    private Usuario usuario;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idfuncao", insertable=false, updatable=false)
    @JsonBackReference(value="idfuncao")
    private Funcao funcao;

    public UsuarioFuncao(int idUsuario, int idfuncao) {
        this.idUsuario = idUsuario;
        this.idfuncao = idfuncao;
    }
}
