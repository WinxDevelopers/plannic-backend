package br.com.plannic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class NotasMateriaDTO {

    private int idNotaMateria;
    private int idMateria;
    private int idUsuario;
    private Double notaMateria;
    private String nomeMateria;
}
