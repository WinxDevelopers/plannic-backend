package br.com.plannic.service;

import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.Usuario;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class UsuarioServiceTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    private MockHttpServletRequest request = new MockHttpServletRequest();


    private AuthenticationManager authenticationManager;

    public UsuarioServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void adicionarUsuario() throws IOException, MessagingException, TemplateException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String url = "google.com";

        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste",LocalDateTime.now(),"",LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"",true);

        Mockito.doNothing().when(usuarioService).save(usuario,url);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario,request);


        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void encontrarUsuarios() throws IOException, MessagingException, TemplateException {


        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), "",LocalDateTime.now(),Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"",true);
        Usuario usuario1 = new Usuario(1, "teste@gmail.com", "senha345", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"",true);
        Usuario usuario2 = new Usuario(1, "usuario@gmail.com", "senha567", "Usuario Teste", LocalDateTime.now(), "",LocalDateTime.now(),Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"",true);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario,request);
        ResponseEntity<Usuario> responseEntity1 = usuarioController.save(usuario1,request);
        ResponseEntity<Usuario> responseEntity2 = usuarioController.save(usuario2,request);

        Mockito.when(usuarioService.getAll()).thenReturn(Collections.emptyList());


        usuarioController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity2.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void deletarUsuarios() throws IOException, MessagingException, TemplateException {


        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), "",LocalDateTime.now(),Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"fdsdf",true);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario,request);

        Mockito.when(usuarioService.delete(1)).thenReturn(true);

        ResponseEntity responseEntityDelete = usuarioController.delete(1);

        usuarioController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void atualizarUsuario() throws IOException, MessagingException, TemplateException {


        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true);
        Usuario usuario1 = new Usuario(1, "teste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario,request);

        ResponseEntity responseEntityAtualiza = usuarioController.update(usuario1);

        usuarioController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(406);
    }
}
