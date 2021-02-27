package br.com.plannic.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public  class TipoNotaVsNotaDTO {
    private String tipoNota;
    private double nota;


    public TipoNotaVsNotaDTO(Map.Entry<String, Double> entry) {
        this(entry.getKey(), entry.getValue()); // call all-args constructor provided by Lombok
    }
}
