package br.com.plannic.dto;

import br.com.plannic.model.MateriaBase;
import br.com.plannic.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TutorDTO {
    private int idTutor;
    private Usuario idUsuarioTutor;
    private MateriaBase idMateriaBase;
    private Double nota;
}
