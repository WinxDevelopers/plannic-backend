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
@Table(name = "telegramusuario")
public class TelegramUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtelegramusuario")
    private int idTelegramUsuario;

    @Column(name = "idusuario",unique = true)
    private int idUsuario;

    @Column(name = "idtelegram",unique = true)
    private String idTelegram;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference(value="idusuario")
    private Usuario usuario;

}
