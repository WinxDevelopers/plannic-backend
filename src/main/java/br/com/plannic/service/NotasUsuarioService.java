package br.com.plannic.service;

import br.com.plannic.dto.AlunoDTO;
import br.com.plannic.dto.NotasMateriaDTO;
import br.com.plannic.dto.NotasUsuarioDTO;
import br.com.plannic.model.NotasUsuario;
import br.com.plannic.model.Tutoria;
import br.com.plannic.repository.NotasUsuarioRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotasUsuarioService {

    private final NotasUsuarioRepository repository;

    private static Logger logger = Logger.getLogger(NotasUsuarioService.class);

    public NotasUsuarioService(NotasUsuarioRepository repository) {
        this.repository = repository;
    }


    public void save(NotasUsuario notasUsuario) {
        ModelMapper mapper = new ModelMapper();
        var notasUsuarioSalva = repository.save(mapper.map(notasUsuario, NotasUsuario.class));
        MDC.put("idNotasUsuario", notasUsuarioSalva.getIdNotaUsuario());
        logger.info("Nota de usuário salva");
    }

    public boolean update(NotasUsuario notasUsuario) {
        Optional<NotasUsuario> notasUsuarios = Optional.ofNullable(this.repository.findById(notasUsuario.getIdNotaUsuario()));

        if (notasUsuarios.isPresent()) {
            logger.info("Notas de usuário atualizada");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(notasUsuario, NotasUsuario.class));
            return true;
        }

        return false;
    }

    public boolean delete(NotasUsuario notasUsuario) {
        Optional<NotasUsuario> notasUsuarios = Optional.ofNullable(this.repository.findById(notasUsuario.getIdNotaUsuario()));

        if (notasUsuarios.isPresent()) {
            logger.info("Nota de usuário deletada");
            this.repository.deleteById(notasUsuario.getIdNotaUsuario());
            return true;
        }

        return false;
    }

    public boolean ativa(int idUsuario, int idTutoria) {
        NotasUsuario notasUsuariosTutor = this.repository.findByIdTutoriaTutor(idTutoria, idUsuario);
        NotasUsuario notasUsuariosAluno = this.repository.findByIdTutoriaAluno(idTutoria, idUsuario);

        if (notasUsuariosTutor.getIdNotaUsuario() != 0 && notasUsuariosTutor.getIdNotaUsuario() != 0) {
            notasUsuariosTutor.setAtivo(true);
            notasUsuariosAluno.setAtivo(true);
            update(notasUsuariosTutor);
            update(notasUsuariosAluno);
            logger.info("Notas de usuário ativadas");
            return true;
        }

        return false;
    }

    public List<NotasUsuarioDTO> getAvalia(int idAvalia) {
        List<NotasUsuario> notasUsuarios = this.repository.findAvaliar(idAvalia);
        List<NotasUsuarioDTO> list =  new ArrayList<>();

        notasUsuarios.forEach(
                nu ->
                        list.add(
                                new NotasUsuarioDTO(
                                        nu.getIdNotaUsuario(),
                                        nu.getIdAvalia(),
                                        nu.getUsuarioAvalia().getNome(),
                                        nu.getIdAvaliado(),
                                        nu.getUsuarioAvaliado().getNome(),
                                        nu.getIdTutoria(),
                                        nu.getMateriaBase().getMateriaBase(),
                                        nu.getNota()
                                        )
                        ));

        List<NotasUsuarioDTO> filtered = list.stream().collect(Collectors.toList());
        return filtered;
    }

    public Double getNotaUsuario(int idAvaliado) {
        List<NotasUsuario> notasUsuarios = this.repository.findNotaUsuario(idAvaliado);

        if (!notasUsuarios.isEmpty()) {
            logger.info("Notas do usuário recuperadas");
            return notasUsuarios
                    .stream()
                    .collect(Collectors.averagingDouble(NotasUsuario::getNota));
        }
        return Double.valueOf(0);
    }
}
