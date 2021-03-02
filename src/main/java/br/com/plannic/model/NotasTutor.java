//package br.com.plannic.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "notasTutor")
//public class NotasTutor {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "idnotausuario")
//    private int idNotaUsuario;
//
//    @Column(name = "idusuario")
//    private int idUsuario;
//
//    @Column(name = "idmateria")
//    private int idMateria;
//
//    @Column(name = "notatutor")
//    private Integer notaTutor;
//
//    @Column(name = "descricaonota")
//    private String descricaoNota;
//
//    @Column(name = "idusuariotutor")
//    private int idUsuarioTutor;
//
//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "idusuario", insertable=false, updatable=false)
//    private Usuario usuario;
//
//}
