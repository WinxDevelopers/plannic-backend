package br.com.plannic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DataVsNotaDTO {
    private int idMateria;
    private Double notaMateria;
    private Date dataNota;
    private String nomeMateria;





}
