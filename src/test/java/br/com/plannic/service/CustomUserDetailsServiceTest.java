package br.com.plannic.service;

import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.UsuarioRepository;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository repository;


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
                Collections.emptyList(),
                "codteste",
                true);
        String url = "google.com.br";

        Mockito.doNothing().when(usuarioService).save(usuario, url);
        when(repository.findByEmail(usuario.getEmail())).thenReturn(usuario);

        User user = (User) customUserDetailsService.loadUserByUsername(usuario.getEmail());

        assertThat(user.getUsername()).isEqualTo("usuarioteste@gmail.com");
    }
}
