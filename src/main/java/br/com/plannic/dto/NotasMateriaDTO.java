package br.com.plannic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class NotasMateriaDTO {

    private Double notaMateria;
    private String nomeMateria;
}
