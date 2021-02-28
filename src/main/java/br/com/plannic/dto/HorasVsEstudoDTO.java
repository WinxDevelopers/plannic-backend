package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HorasVsEstudoDTO {

    private String horas;
    private String materia;
    private Double notaMateria;

}
