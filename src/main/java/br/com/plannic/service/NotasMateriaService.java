package br.com.plannic.service;

import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.MateriaRepository;
import br.com.plannic.repository.NotasMateriaRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotasMateriaService {

    private NotasMateriaRepository repository;

    private static Logger logger = Logger.getLogger(NotasMateriaService.class);

    public NotasMateriaService(NotasMateriaRepository repository) {
        this.repository = repository;
    }

    public List<NotasMateria> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<NotasMateria> notasMaterias = repository.findAll();

        if (!notasMaterias.isEmpty()) {
            logger.info("Notas das materias recuperadas");
            return  notasMaterias
                    .stream()
                    .map(notasMateria -> mapper.map(notasMateria, NotasMateria.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(NotasMateria notasMateria) {
        ModelMapper mapper = new ModelMapper();
        var notaMateriaSalva = repository.save(mapper.map(notasMateria, NotasMateria.class));
        MDC.put("user_id", notaMateriaSalva.getIdUsuario());
        logger.info("Nota da materia salva");

    }


    public boolean update(NotasMateria notasMateria) {
        Optional<NotasMateria> notasMaterias = Optional.ofNullable(this.repository.findById(notasMateria.getIdMateria()));

        if (notasMaterias.isPresent()) {
            logger.info("Nota da materia atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(notasMateria, NotasMateria.class));
            return true;
        }

        return false;
    }


    public boolean delete(int id) {
        Optional<NotasMateria> notasMaterias = Optional.ofNullable(this.repository.findById(id));

        if (notasMaterias.isPresent()) {
            logger.info("Notas da materia deletada");
            this.repository.deleteById(id);
            return true;
        }

        return false;
    }

}
