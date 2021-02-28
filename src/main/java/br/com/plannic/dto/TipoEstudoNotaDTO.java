package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoEstudoNotaDTO {

    private double nota;
    private String tipoEstudo;

}
