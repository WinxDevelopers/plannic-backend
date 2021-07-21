package br.com.plannic.controller;

import br.com.plannic.model.Usuario;
import br.com.plannic.service.AgendamentoService;
import br.com.plannic.service.EmailService;
import br.com.plannic.service.NotificacaoService;
import br.com.plannic.service.UsuarioService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/mock")
public class MockController {

    private NotificacaoService notificaoService;

    @Autowired
    public MockController(NotificacaoService notificaoService) {
        this.notificaoService = notificaoService;
    }
    @GetMapping("/verify")
    public ResponseEntity verifyUser() {
        notificaoService.sendMessage();
        return ResponseEntity.ok().build();
    }
}
