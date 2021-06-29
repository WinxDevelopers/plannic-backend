package br.com.plannic.service;

import br.com.plannic.model.Tutor;
import br.com.plannic.repository.TutorRepository;
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
public class TutorService {

    private TutorRepository repository;


    private static Logger logger = Logger.getLogger(TutorService.class);

    public TutorService(TutorRepository repository) {
        this.repository = repository;
    }


    public List<Tutor> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Tutor> tutores = repository.findAll();

        if (!tutores.isEmpty()) {
            logger.info("Alunos recuperados");
            return  tutores
                    .stream()
                    .map(tutor -> mapper.map(tutor, Tutor.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Tutor tutor) {
        ModelMapper mapper = new ModelMapper();
        var tutorSalvo = repository.save(mapper.map(tutor, Tutor.class));
        MDC.put("tutor_id", tutor.getIdTutor());
        logger.info("Tutor salvo");
    }


    public boolean update(Tutor tutor) {
        Optional<Tutor> tutores = Optional.ofNullable(this.repository.findById(tutor.getIdTutor()));

        if (tutores.isPresent()) {
            logger.info("Tutor atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(tutor, Tutor.class));
            return true;
        }

        return false;
    }


    public boolean delete(int id) {
        Optional<Tutor> tutores = Optional.ofNullable(this.repository.findById(id));

        if (tutores.isPresent()) {
            logger.info("Tutor deletado");
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Tutor> getByMateria(int id, int idMateria) {
        ModelMapper mapper = new ModelMapper();
        Optional<Tutor> tutores = Optional.ofNullable(this.repository.findByIdMateriaBase(id, idMateria));

        if (!tutores.isEmpty()) {
            logger.info("Tutores recuperados");
            return  tutores
                    .stream()
                    .map(tutor -> mapper.map(tutor, Tutor.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public boolean deleteAfterTutoria(int id, int idMateria) {
        Tutor tutor = this.repository.findByTutoria(id, idMateria);

        if (tutor.getIdTutor() != 0) {
            logger.info("Tutor deletado");
            this.repository.deleteById(tutor.getIdTutor());
            return true;
        }
        return false;
    }
}
