package br.com.plannic.service;

import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.UsuarioRepository;
import com.sun.xml.bind.v2.TODO;
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

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    public CustomUserDetailsServiceTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void loadUserByUsername() {
        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        Mockito.doNothing().when(usuarioService).save(usuario);

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }
}