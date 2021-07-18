package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotasUsuarioDTO {
    private int idNotaUsuario;
    private int idAvalia;
    private String nomeAvalia;
    private int idAvaliado;
    private String nomeAvaliado;
    private int idTutoria;
    private int idMateriaBase;
    private String nomeMateria;
    private Double nota;
}
