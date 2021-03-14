package br.com.plannic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "idusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private LocalDateTime data = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("materias")
    List<Materia> materias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("agendamentos")
    List<Agendamento> agendamentos;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("notasmateria")
    List<NotasMateria> notasMateria;

    @Column(name = "codverifica", length = 64)
    private String CodVerifica;

    @Column(name="ativo")
    private boolean ativo;
//
//    @OneToMany
//    List<NotasTutor> notasTutor;
}
