package br.com.plannic.service;

import br.com.plannic.model.NotasUsuario;
import br.com.plannic.model.TelegramUsuario;
import br.com.plannic.repository.NotasUsuarioRepository;
import br.com.plannic.repository.TelegramUsuarioRepository;
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
public class TelegramUsuarioService {

    private final TelegramUsuarioRepository repository;

    private static Logger logger = Logger.getLogger(TelegramUsuarioService.class);

    public TelegramUsuarioService(TelegramUsuarioRepository repository) {
        this.repository = repository;
    }


    public void save(TelegramUsuario telegramUsuario) {
        ModelMapper mapper = new ModelMapper();
        var telegramUsuarioSalva = repository.save(mapper.map(telegramUsuario, TelegramUsuario.class));
        MDC.put("idTelegramUsuario", telegramUsuarioSalva.getIdTelegramUsuario());
        logger.info("id do telegram do usuário salvo");
    }



//    public Double getNotaUsuario(int idAvaliado) {
//        List<NotasUsuario> notasUsuarios = this.repository.findNotaUsuario(idAvaliado);
//
//        if (!notasUsuarios.isEmpty()) {
//            logger.info("Notas do usuário recuperadas");
//            return notasUsuarios
//                    .stream()
//                    .collect(Collectors.averagingDouble(NotasUsuario::getNota));
//        }
//        return Double.valueOf(0);
//    }
}
