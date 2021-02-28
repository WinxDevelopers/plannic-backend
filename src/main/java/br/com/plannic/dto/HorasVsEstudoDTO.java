package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class HorasVsEstudoDTO {

    private int idMateria;
    private long minEstudo;
    private String materia;
    private Double notaMateria;

}
