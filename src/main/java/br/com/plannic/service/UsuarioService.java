package br.com.plannic.service;

import br.com.plannic.model.Usuario;
import br.com.plannic.repository.UsuarioRepository;
import net.bytebuddy.utility.RandomString;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    private JavaMailSender javaMailSender;


    private static Logger logger = Logger.getLogger(UsuarioService.class);


    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Usuario> usuarios = repository.findAll();

        if (!usuarios.isEmpty()) {
            logger.info("Usuários recuperados");
            return usuarios
                    .stream()
                    .map(usuario -> mapper.map(usuario, Usuario.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Usuario> getUser(int id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Usuario> usuarios = Optional.ofNullable(this.repository.findByIdUsuario(id));

        if (!usuarios.isEmpty()) {
            logger.info("Usuário recuperado");
            return usuarios
                    .stream()
                    .map(usuario -> mapper.map(usuario, Usuario.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(Usuario usuario,String url) throws UnsupportedEncodingException, MessagingException {
        var senha = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(senha));
        ModelMapper mapper = new ModelMapper();
        String randomCode = RandomString.make(64);
        usuario.setCodVerifica(randomCode);
        usuario.setAtivo(false);
        var usuarioSalvo = repository.save(mapper.map(usuario, Usuario.class));

        sendVerificationEmail(usuario, url);

        MDC.put("user_id", usuarioSalvo.getIdUsuario());
        logger.info("Usuário salvo");
    }

    private void sendVerificationEmail(Usuario user, String url)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "plannic@plannic.com.br";
        String senderName = "Plannic";
        String subject = "Plannic - Verificação de email";
        String content = "Ola [[name]],<br>"
                + "Por favor confirme seu email para utilizar o Plannic:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">Clique Aqui</a></h3>"
                + "Obrigado,<br>"
                + "Equipe Plannic.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getNome());
        String verifyURL = url + "/usuario/verify?code=" + user.getCodVerifica();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        javaMailSender.send(message);

    }

    public boolean verify(String verificationCode) {
        Usuario user = repository.findByVerificationCode(verificationCode);

        if (user == null || user.isAtivo()) {
            return false;
        } else {
            user.setCodVerifica(null);
            user.setAtivo(true);
            repository.save(user);

            return true;
        }

    }
    public boolean update(Usuario usuario) {
        Optional<Usuario> usuarios = this.repository.findById(usuario.getIdUsuario());

        if (usuarios.isPresent()) {
            logger.info("Usuário atualizado");
            ModelMapper mapper = new ModelMapper();
            Usuario user = new Usuario(usuario.getIdUsuario(), usuario.getEmail(), usuarios.get().getPassword(), usuario.getNome(), usuario.getData(), usuarios.get().getMaterias(), usuarios.get().getAgendamentos(), usuarios.get().getNotasMateria(),usuarios.get().getCodVerifica(),usuarios.get().isAtivo());
            repository.save(mapper.map(user, Usuario.class));
            return true;
        }

        return false;
    }

    public boolean updatePassword(int id, String password) {
        Usuario usuario = this.repository.findByIdUsuario(id);
        if (!password.isBlank()) {
            usuario.setPassword(passwordEncoder.encode(password));
            ModelMapper mapper = new ModelMapper();
            var usuarioSalvo = repository.save(mapper.map(usuario, Usuario.class));
            MDC.put("user_id", usuarioSalvo.getIdUsuario());
            logger.info("Senha atualizada");
            return true;
        }
        return false;
    }

    public boolean updateCodigoVerifica(Usuario usuario, String url)
            throws UnsupportedEncodingException, MessagingException {
        Usuario usuarios = this.repository.findByEmail(usuario.getEmail());

        if (usuarios.getCodVerifica() != null && usuarios.isAtivo() ==false) {
            ModelMapper mapper = new ModelMapper();
            String randomCode = RandomString.make(64);
            usuarios.setCodVerifica(randomCode);
            usuarios.setAtivo(false);
            var usuarioSalvo = repository.save(mapper.map(usuarios, Usuario.class));
            sendVerificationEmail(usuarios, url);
            MDC.put("user_id", usuarioSalvo.getIdUsuario());
            logger.info("Senha atualizada");
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Optional<Usuario> usuarios = this.repository.findById(id);

        if (usuarios.isPresent()) {
            logger.info("Usuário deletado");
            this.repository.deleteById(id);
            return true;
        }

        return false;
    }

    public Usuario findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
