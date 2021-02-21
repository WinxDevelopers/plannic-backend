package br.com.plannic.service;

import br.com.plannic.model.Materia;
import br.com.plannic.repository.MateriaRepository;
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
public class MateriaService {

    private final MateriaRepository repository;

    private static Logger logger = Logger.getLogger(MateriaService.class);

    public MateriaService(MateriaRepository repository) {
        this.repository = repository;
    }

    public List<Materia> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Materia> materias = repository.findAll();

        if (!materias.isEmpty()) {
            logger.info("Materias recuperadas");
            return materias
                    .stream()
                    .map(materia -> mapper.map(materia, Materia.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Materia materia) {
        ModelMapper mapper = new ModelMapper();
        var materiaSalva = repository.save(mapper.map(materia, Materia.class));
        MDC.put("materia_id", materiaSalva.getId());
        logger.info("Materia salva");
    }

    public boolean update(Materia materia) {
        Optional<Materia> materias = Optional.ofNullable(this.repository.findById(materia.getId()));

        if (materias.isPresent()) {
            logger.info("Materia atualizada");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(materia, Materia.class));
            return true;
        }
        return false;
    }


    public boolean delete(Materia materia) {
        Optional<Materia> materias = Optional.ofNullable(this.repository.findById(materia.getId()));

        if (materias.isPresent()) {
            logger.info("Materia deletada");
            this.repository.deleteById(materia.getId());
            return true;
        }
        return false;
    }
}
