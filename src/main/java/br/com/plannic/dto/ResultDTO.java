package br.com.plannic.dto;

import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class ResultDTO  {
    private String nomeMateria;
    private int idNotaMateria;

    private int idMateria;

    private int idUsuario;

    private Double notaMateria;

    private String tipoNota;

    private Date dataNota;

    private Usuario usuario;

//    public ResultDTO(KeyResult key, ValueResult value, String notaMateria) {
//        super(
//                key.getIdUsuario(), key.getIdMateria(),
//                value.getAvgNotaMateria(), value.getMinIdNotaMateria(),
//                key.getTipoNota()
//        );
//        this.nomeMateria = notaMateria;
//    }
}
