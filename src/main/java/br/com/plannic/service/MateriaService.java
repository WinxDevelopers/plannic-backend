package br.com.plannic.service;

import br.com.plannic.model.Materia;
import br.com.plannic.model.MateriaBase;
import br.com.plannic.model.SugestoesMateria;
import br.com.plannic.repository.MateriaBaseRepository;
import br.com.plannic.repository.MateriaRepository;
import br.com.plannic.repository.SugestoesMateriaRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class MateriaService {

    private final MateriaRepository repository;
    private final MateriaBaseRepository materiaBaseRepository;
    private final SugestoesMateriaRepository sugestoesMateriaRepository;

    private static Logger logger = Logger.getLogger(MateriaService.class);

    public MateriaService(MateriaRepository repository, MateriaBaseRepository materiaBaseRepository, SugestoesMateriaRepository sugestoesMateriaRepository) {
        this.repository = repository;
        this.materiaBaseRepository = materiaBaseRepository;
        this.sugestoesMateriaRepository = sugestoesMateriaRepository;
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
        MDC.put("materia_id", materiaSalva.getIdMateria());
        logger.info("Materia salva");
    }

    public boolean update(Materia materia) {
        Optional<Materia> materias = Optional.ofNullable(this.repository.findById(materia.getIdMateria()));

        if (materias.isPresent()) {
            logger.info("Materia atualizada");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(materia, Materia.class));
            return true;
        }
        return false;
    }



    public boolean delete(int id) {
        Optional<Materia> materias = Optional.ofNullable(this.repository.findById(id));

        if (materias.isPresent()) {
            logger.info("Materia deletada");
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Materias Base
    public void saveMateriaBase(MateriaBase materiaBase) {
        ModelMapper mapper = new ModelMapper();
        var baseSalva = materiaBaseRepository.save(mapper.map(materiaBase, MateriaBase.class));
        MDC.put("baseMateria_id", baseSalva.getIdMateriaBase());
        logger.info("Materia base salva");

    }

    public List<MateriaBase> getAllBase() {
        ModelMapper mapper = new ModelMapper();
        List<MateriaBase> materias = materiaBaseRepository.findAll();

        if (!materias.isEmpty()) {
            logger.info("Materias base recuperadas");
            return materias
                    .stream()
                    .map(materiaBase -> mapper.map(materiaBase, MateriaBase.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    // Sugestão de Matéria
    public void saveSugestaoMateria(SugestoesMateria sugestoesmateria) {
        ModelMapper mapper = new ModelMapper();
        var sugestaoSalva = sugestoesMateriaRepository.save(mapper.map(sugestoesmateria, SugestoesMateria.class));
        MDC.put("sugestaoMateria_id", sugestaoSalva.getIdSugestoesMateria());
        logger.info("Sugestão salva");

        Materia materia = new Materia(sugestoesmateria.getNomeMateria());
            materia.setIdSugestao(sugestaoSalva.getIdSugestoesMateria());
            materia.setIdUsuario(sugestaoSalva.getIdUsuario());

            save(materia);
    }

    public boolean updateSugestaoMateria(SugestoesMateria sugestoesmateria) {
        Optional<SugestoesMateria> sugestoes = Optional.ofNullable(this.sugestoesMateriaRepository.findById(sugestoesmateria.getIdSugestoesMateria()));

        if (sugestoes.isPresent()) {
            logger.info("Sugestão atualizada");
            ModelMapper mapper = new ModelMapper();
            sugestoesMateriaRepository.save(mapper.map(sugestoesmateria, SugestoesMateria.class));
            return true;
        }
        return false;
    }


    public void atualizarMateriaAceita(SugestoesMateria sugestoesMateria, MateriaBase materiaBase) {
        ModelMapper mapper = new ModelMapper();
        Materia materia = this.repository.findByIdSugestao(sugestoesMateria.getIdSugestoesMateria());
        if (materia.getIdMateria() != 0) {
            logger.info("Materia atualizada");
            materia.setIdMateriaBase(materiaBase.getIdMateriaBase());
            materia.setIdSugestao(0);
            repository.save(mapper.map(materia, Materia.class));
        }
    }

    public void atualizarMateriaRecusada(SugestoesMateria sugestoesMateria) {
        Materia materia = this.repository.findByIdSugestao(sugestoesMateria.getIdSugestoesMateria());
        if (materia.getIdMateria() != 0) {
            logger.info("Materia Excluída");
            repository.deleteById(materia.getIdMateria());
        }
    }

    public List<SugestoesMateria> getAllSugestoes() {
        ModelMapper mapper = new ModelMapper();
        List<SugestoesMateria> materias = sugestoesMateriaRepository.findAll();

        if (!materias.isEmpty()) {
            logger.info("Sugestões recuperadas");
            return materias
                    .stream()
                    .map(sugestoesMateria -> mapper.map(sugestoesMateria, SugestoesMateria.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public boolean deleteSugestaoMateria(int id) {
        Optional<SugestoesMateria> materias = Optional.ofNullable(this.sugestoesMateriaRepository.findById(id));

        if (materias.isPresent()) {
            logger.info("Sugestão deletada");
            this.sugestoesMateriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
