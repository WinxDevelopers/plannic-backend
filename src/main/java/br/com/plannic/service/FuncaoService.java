package br.com.plannic.service;

import br.com.plannic.model.UsuarioFuncao;
import br.com.plannic.repository.UsuarioFuncaoRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class FuncaoService {

    private final UsuarioFuncaoRepository repository;

    private static Logger logger = Logger.getLogger(FuncaoService.class);

    public FuncaoService(UsuarioFuncaoRepository repository) {
        this.repository = repository;
    }


    public void save(UsuarioFuncao usuarioFuncao) {
        ModelMapper mapper = new ModelMapper();
        var usuarioFuncaoSalva = repository.save(mapper.map(usuarioFuncao, UsuarioFuncao.class));
        MDC.put("idUsuarioFuncao", usuarioFuncaoSalva.getIdUsuarioFuncao());
        logger.info("Função de usuário salva");
    }
}
