package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue
    private Long idAgendamento;

    @Column(name = "idMateira")
    private Long idMateira;

    @Column(name = "timestampInicio")
    private Integer timestampInicio;

    @Column(name = "timestampFim")
    private Integer timestampFim;

    @Column(name = "recorrencia")
    private String recorrencia;

    @Column(name = "tipoEstudo")
    private String tipoEstudo;

}
