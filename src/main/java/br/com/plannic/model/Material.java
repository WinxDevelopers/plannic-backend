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
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmaterial")
    private int idMaterial;

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "idmateria")
    private int idMateria;

    @Column(name = "idmateriabase")
    private int idMateriaBase;

    @Column(name = "nomematerial")
    private String nomeMaterial;

    @Column(name = "material")
    private String material;

    @Column(name = "tipomaterial")
    private String tipoMaterial;

    @Column(name="publico")
    private boolean publico;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idmateria", insertable=false, updatable=false)
    @JsonBackReference(value="idmateria")
    private Materia materia;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
    @JsonBackReference(value="idusuario")
    private Usuario usuario;
}
