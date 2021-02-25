package br.com.plannic.service;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.repository.AgendamentoRepository;
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
public class AgendamentoService {

    private AgendamentoRepository repository;

    private static Logger logger = Logger.getLogger(AgendamentoService.class);

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }


    public List<Agendamento> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Agendamento> agendamentos = repository.findAll();

        if (!agendamentos.isEmpty()) {
            logger.info("agendamentos recuperados");
            return  agendamentos
                    .stream()
                    .map(agendamento -> mapper.map(agendamento, Agendamento.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Agendamento agendamento) {
        ModelMapper mapper = new ModelMapper();
        var agendamentoSalvo = repository.save(mapper.map(agendamento, Agendamento.class));
        MDC.put("user_id", agendamentoSalvo.getIdAgendamento());
        logger.info("Agendamento salvo");

    }


    public boolean update(Agendamento agendamento) {
        Optional<Agendamento> agendamentos = Optional.ofNullable(this.repository.findById(agendamento.getIdAgendamento()));

        if (agendamentos.isPresent()) {
            logger.info("agendamento atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(agendamento, Agendamento.class));
            return true;
        }

        return false;
    }


    public boolean delete(Agendamento agendamento) {
        Optional<Agendamento> agendamentos = Optional.ofNullable(this.repository.findById(agendamento.getIdAgendamento()));

        if (agendamentos.isPresent()) {
            logger.info("Agendamento deletado");
            this.repository.deleteById(agendamento.getIdAgendamento());
            return true;
        }

        return false;
    }
}
