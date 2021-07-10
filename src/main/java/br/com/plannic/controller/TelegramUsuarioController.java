package br.com.plannic.controller;

import br.com.plannic.model.TelegramUsuario;
import br.com.plannic.service.TelegramUsuarioService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuariotelegram")
public class TelegramUsuarioController {

    private TelegramUsuarioService telegramUsuarioService;

    public TelegramUsuarioController(TelegramUsuarioService telegramUsuarioService) {
        this.telegramUsuarioService = telegramUsuarioService;
    }


    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de materias")
    public ResponseEntity<?> save(@Valid @RequestBody TelegramUsuario telegramUsuario){
        try {
            MDC.put("id telegram usuario", telegramUsuario.getIdTelegramUsuario());
            MDC.put("fluxo", "POST save");
            telegramUsuarioService.save(telegramUsuario);
        }finally{
            MDC.clear();
        }

        return new ResponseEntity<>("salvo", HttpStatus.CREATED);
    }
}
