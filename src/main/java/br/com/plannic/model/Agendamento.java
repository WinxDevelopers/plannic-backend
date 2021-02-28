package br.com.plannic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idagendamento")
    private int idAgendamento;

    @Column(name = "idmateria")
    private int idMateria;

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "timestampinicio")
    private Date timestampInicio;

    @Column(name = "timestampfim")
    private Date timestampFim;

    @Column(name = "recorrencia")
    private String recorrencia;

    @Column(name = "tipoestudo")
    private String tipoEstudo;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference
    private Usuario usuario;

}
