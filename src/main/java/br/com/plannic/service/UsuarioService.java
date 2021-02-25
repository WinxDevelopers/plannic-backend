package br.com.plannic.service;

import br.com.plannic.controller.AuthenticateController;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UsuarioService {

//    @Autowired
//    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository repository;


    private static Logger logger = Logger.getLogger(UsuarioService.class);


    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Usuario> usuarios = repository.findAll();

        if (!usuarios.isEmpty()) {
            logger.info("Usuários recuperados");
            return  usuarios
                    .stream()
                    .map(usuario -> mapper.map(usuario, Usuario.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Usuario usuario) {
        var senha = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(senha));
        ModelMapper mapper = new ModelMapper();
        var usuarioSalvo = repository.save(mapper.map(usuario, Usuario.class));
        MDC.put("user_id", usuarioSalvo.getIdUsuario());
        logger.info("Usuário salvo");


    }


    public boolean update(Usuario usuario) {
        Optional<Usuario> usuarios = this.repository.findById(usuario.getIdUsuario());

        if (usuarios.isPresent()) {
            logger.info("Usuário atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(usuario, Usuario.class));
            return true;
        }

        return false;
    }


    public boolean delete(Usuario usuario) {
        Optional<Usuario> usuarios = this.repository.findById(usuario.getIdUsuario());

        if (usuarios.isPresent()) {
            logger.info("Usuário deletado");
            this.repository.deleteById(usuario.getIdUsuario());
            return true;
        }

        return false;
    }
}
