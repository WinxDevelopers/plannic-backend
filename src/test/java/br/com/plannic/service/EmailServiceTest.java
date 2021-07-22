package br.com.plannic.service;

import br.com.plannic.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
class EmailServiceTest {

    @InjectMocks
    EmailService emailService;

    @Mock
    JavaMailSender sender;

    public EmailServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    Usuario usuario = new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),"",LocalDateTime.now(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            "codteste",
            true);

    SimpleMailMessage email = new SimpleMailMessage();

    @Test
    public void welcome() {
        Mockito.doNothing().when(sender).send(email);
        emailService.welcome(usuario);
    }


}
