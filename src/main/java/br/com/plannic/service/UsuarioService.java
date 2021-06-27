package br.com.plannic.service;

import br.com.plannic.model.Funcao;
import br.com.plannic.model.Usuario;
import br.com.plannic.model.UsuarioFuncao;
import br.com.plannic.repository.UsuarioFuncaoRepository;
import br.com.plannic.repository.UsuarioRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.bytebuddy.utility.RandomString;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class UsuarioService {

//    @Autowired
//    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository repository;
    private final UsuarioFuncaoRepository usuarioFuncaoRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration config;

    private static Logger logger = Logger.getLogger(UsuarioService.class);


    public UsuarioService(UsuarioRepository repository, UsuarioFuncaoRepository usuarioFuncaoRepository) {
        this.repository = repository;
        this.usuarioFuncaoRepository = usuarioFuncaoRepository;
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

    public void save(Usuario usuario,String url) throws IOException, MessagingException, TemplateException {
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

    public void sendVerificationEmail(Usuario usuario, String url)
            throws MessagingException, IOException, TemplateException {
        String toAddress = usuario.getEmail();
        String fromAddress = "plannic@plannic.com.br";
        String senderName = "Plannic";
        String subject = "Plannic - Verificação de email";

        Template template = config.getTemplate("emailVerificacao.ftl");
        String verifyURL = url + "/usuario/verify?code=" + usuario.getCodVerifica();

        Map<String,Object> model =new HashMap<>();
        model.put("nome",usuario.getNome());
        model.put("url",verifyURL);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(html, true);

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
            throws IOException, MessagingException, TemplateException {
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

    public List<UsuarioFuncao> getFuncao(int id) {
        ModelMapper mapper = new ModelMapper();
        Optional<UsuarioFuncao> usuarioFuncao = Optional.ofNullable(this.usuarioFuncaoRepository.findByUsuario(repository.findByIdUsuario(id)));

        if (!usuarioFuncao.isEmpty()) {
            logger.info("Função recuperada");
            return usuarioFuncao
                    .stream()
                    .map(funcao -> mapper.map(funcao, UsuarioFuncao.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
