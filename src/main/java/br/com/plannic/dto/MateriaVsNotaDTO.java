package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public  class MateriaVsNotaDTO {
    private Integer materia;
    private double nota;


    public MateriaVsNotaDTO(Map.Entry<Integer, Double> entry) {
        this(entry.getKey(), entry.getValue()); // call all-args constructor provided by Lombok
    }
}
