package br.com.plannic.service;

import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    private AuthenticationManager authenticationManager;


    @Test
    public void adicionarUsuario() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        Mockito.doNothing().when(usuarioService).save(usuario);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void encontrarUsuarios() {

        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        Usuario usuario1 = new Usuario(1, "teste@gmail.com", "senha345", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        Usuario usuario2 = new Usuario(1, "usuario@gmail.com", "senha567", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario);
        ResponseEntity<Usuario> responseEntity1 = usuarioController.save(usuario1);
        ResponseEntity<Usuario> responseEntity2 = usuarioController.save(usuario2);
        Mockito.when(usuarioService.getAll()).thenReturn(Collections.emptyList());


        usuarioController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity2.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void deletarUsuarios() {

        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario);
        Mockito.when(usuarioService.delete(1)).thenReturn(true);

        ResponseEntity responseEntityDelete = usuarioController.delete(1);

        usuarioController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void atualizarUsuario() {

        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        Usuario usuario1 = new Usuario(1, "teste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario);
        ResponseEntity responseEntityAtualiza = usuarioController.update(usuario1);

        usuarioController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(204);
    }
}
