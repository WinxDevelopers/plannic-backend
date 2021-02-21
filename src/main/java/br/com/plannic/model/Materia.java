package br.com.plannic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue
    private int id;
    private int idUsuario;
    private String descricao;
    private String materia;
}
