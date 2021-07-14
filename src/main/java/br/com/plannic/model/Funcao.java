package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcao")
public class Funcao {

    @Id
    @Column(name = "idfuncao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFuncao;

    @Column(name = "funcao")
    private String funcao;

    public Funcao(String funcao) {

        this.funcao=funcao;
    }
}
