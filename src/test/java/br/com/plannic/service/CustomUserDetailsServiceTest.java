package br.com.plannic.service;

import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.UsuarioRepository;
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class CustomUserDetailsServiceTest {

    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuarioController usuarioController;

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    //TODO arrumar esse teste
    @Test
    void loadUserByUsername() {
        Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now());

        ResponseEntity<Usuario> responseEntity = usuarioController.save(usuario);

        Usuario usuario1 = repository.findByEmail(usuario.getEmail());

        assertThat(usuario1.getEmail()).isEqualTo(usuario.getEmail());

    }
}