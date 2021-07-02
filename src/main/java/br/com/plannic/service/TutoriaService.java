package br.com.plannic.service;

import br.com.plannic.model.Tutoria;
import br.com.plannic.repository.TutoriaRepository;
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
public class TutoriaService {

    private TutoriaRepository repository;


    private static Logger logger = Logger.getLogger(TutoriaService.class);

    public TutoriaService(TutoriaRepository repository) {
        this.repository = repository;
    }


    public List<Tutoria> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Tutoria> tutorias = repository.findAll();

        if (!tutorias.isEmpty()) {
            logger.info("Tutores recuperados");
            return  tutorias
                    .stream()
                    .map(tutoria -> mapper.map(tutoria, Tutoria.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Tutoria tutoria) {
        ModelMapper mapper = new ModelMapper();
        var tutoriaSalva = repository.save(mapper.map(tutoria, Tutoria.class));
        MDC.put("tutoria_id", tutoria.getIdTutoria());
        logger.info("Tutoria salva");
    }


    public boolean update(Tutoria tutoria) {
        Optional<Tutoria> tutorias = Optional.ofNullable(this.repository.findById(tutoria.getIdTutoria()));

        if (tutorias.isPresent()) {
            logger.info("Tutoria atualizada");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(tutoria, Tutoria.class));
            return true;
        }

        return false;
    }


    public boolean delete(int id) {
        Optional<Tutoria> tutorias = Optional.ofNullable(this.repository.findById(id));

        if (tutorias.isPresent()) {
            logger.info("Tutoria deletada");
            this.repository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<Tutoria> getTutor(int id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Tutoria> tutoria = Optional.ofNullable(this.repository.findByIdUsuarioTutor(id));

        if (!tutoria.isEmpty()) {
            logger.info("Tutor recuperado");
            return tutoria
                    .stream()
                    .map(tutor -> mapper.map(tutor, Tutoria.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Tutoria> getAluno(int id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Tutoria> tutoria = Optional.ofNullable(this.repository.findByIdUsuarioAluno(id));

        if (!tutoria.isEmpty()) {
            logger.info("Aluno recuperado");
            return tutoria
                    .stream()
                    .map(tutor -> mapper.map(tutor, Tutoria.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
