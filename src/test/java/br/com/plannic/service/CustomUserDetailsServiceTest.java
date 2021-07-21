package br.com.plannic.service;

import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.UsuarioRepository;
import com.sun.xml.bind.v2.TODO;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;


    @Mock
    private HttpSession httpSession;

    private MockHttpServletRequest request = new MockHttpServletRequest();


    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    public CustomUserDetailsServiceTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void loadUserByUsername() throws IOException, MessagingException, TemplateException {
        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                "codteste",
                true);
        String url = "google.com.br";

        Mockito.doNothing().when(usuarioService).save(usuario,url);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario, request);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }
}