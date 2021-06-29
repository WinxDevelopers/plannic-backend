package br.com.plannic.service;

import br.com.plannic.model.Aluno;
import br.com.plannic.repository.AlunoRepository;
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
public class AlunoService {

    private AlunoRepository repository;


    private static Logger logger = Logger.getLogger(AlunoService.class);

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }


    public List<Aluno> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Aluno> alunos = repository.findAll();

        if (!alunos.isEmpty()) {
            logger.info("Alunos recuperados");
            return  alunos
                    .stream()
                    .map(aluno -> mapper.map(aluno, Aluno.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Aluno aluno) {
        ModelMapper mapper = new ModelMapper();
        var alunoSalvo = repository.save(mapper.map(aluno, Aluno.class));
        MDC.put("aluno_id", aluno.getIdAluno());
        logger.info("Aluno salvo");
    }


    public boolean update(Aluno aluno) {
        Optional<Aluno> alunos = Optional.ofNullable(this.repository.findById(aluno.getIdAluno()));

        if (alunos.isPresent()) {
            logger.info("Aluno atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(aluno, Aluno.class));
            return true;
        }

        return false;
    }


    public boolean delete(int id) {
        Optional<Aluno> alunos = Optional.ofNullable(this.repository.findById(id));

        if (alunos.isPresent()) {
            logger.info("Aluno deletado");
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Aluno> getByMateria(int id, int idMateria) {
        ModelMapper mapper = new ModelMapper();
        Optional<Aluno> alunos = Optional.ofNullable(this.repository.findByIdMateriaBase(id, idMateria));

        if (!alunos.isEmpty()) {
            logger.info("Alunos recuperados");
            return  alunos
                    .stream()
                    .map(aluno -> mapper.map(aluno, Aluno.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public boolean deleteAfterTutoria(int id, int idMateria) {
        Aluno aluno = this.repository.findByTutoria(id, idMateria);

        if (aluno.getIdAluno() != 0) {
            logger.info("Aluno deletado");
            this.repository.deleteById(aluno.getIdAluno());
            return true;
        }
        return false;
    }
}
