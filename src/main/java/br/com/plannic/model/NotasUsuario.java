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
@Entity
@Table(name = "notasusuario")
public class NotasUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotausuario")
    private int idNotaUsuario;

    @Column(name = "idavalia")
    private int idAvalia;

    @Column(name = "idavaliado")
    private int idAvaliado;

    @Column(name = "idtutoria")
    private int idTutoria;

    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "ativo")
    private boolean ativo;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idavalia", insertable=false, updatable=false)
    private Usuario usuarioAvalia;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idavaliado", insertable=false, updatable=false)
    private Usuario usuarioAvaliado;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idmateriabase", insertable=false, updatable=false)
    private MateriaBase materiaBase;

    public NotasUsuario() {

    }
}
