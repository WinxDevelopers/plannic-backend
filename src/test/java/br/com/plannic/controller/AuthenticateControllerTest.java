package br.com.plannic.controller;


import br.com.plannic.model.AuthRequest;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.UsuarioService;
import br.com.plannic.util.JwtUtil;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthenticateController.class})
@ExtendWith(SpringExtension.class)
class AuthenticateControllerTest {

    @Autowired
    AuthenticateController authenticateController;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    UsuarioService usuarioService;

    @MockBean
    JwtUtil jwtUtil;

    String email = "usuarioteste@gmail.com";
    String password = "senha123";
    UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
            email, password);
    String token = "123";
    AuthRequest authRequest = new AuthRequest(
            email,
            password
    );

    Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            "codteste",
            true);

    Usuario usuarioInativo = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            "codteste",
            false);


    @Test
    void gerar_token_sucesso() throws Exception {

        when(this.authenticationManager.authenticate(userAuth)).thenReturn(userAuth);
        when(this.usuarioService.findByEmail(email)).thenReturn(usuario);
        when(this.jwtUtil.generateToken(email)).thenReturn(token);

        ResponseEntity<?> responseEntity = authenticateController.generateToken(authRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

    }

    @Test
    void erro_gerar_token() throws Exception {

        when(this.authenticationManager.authenticate(userAuth)).thenReturn(userAuth);
        when(this.usuarioService.findByEmail(email)).thenReturn(usuarioInativo);

        ResponseEntity<?> responseEntity = authenticateController.generateToken(authRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    void exception_gerar_token() throws Exception {

        when(this.authenticationManager.authenticate(userAuth)).thenReturn(userAuth);
        when(this.usuarioService.findByEmail(email)).thenReturn(usuario);
        when(this.jwtUtil.generateToken(email)).thenReturn(token);

        thrown.expectMessage("Erro na autenticação do usuário.");
        thrown.expect(Exception.class);

    }
}

