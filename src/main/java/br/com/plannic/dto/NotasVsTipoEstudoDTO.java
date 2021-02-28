package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotasVsTipoEstudoDTO {

    private Integer materia;
    //    private String materiaNome;
    private double nota;
    private String tipoEstudo;

}
