package br.com.plannic.dto;

import br.com.plannic.model.MateriaBase;
import br.com.plannic.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlunoDTO {
    private int idAluno;
    private Usuario idUsuarioAluno;
    private MateriaBase idMateriaBase;
    private Double nota;
}
