package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeyResult {
    private int idUsuario;
    private int idMateria;
    private String tipoNota;
}