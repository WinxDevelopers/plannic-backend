package br.com.plannic.controller;

import br.com.plannic.model.TelegramUsuario;
import br.com.plannic.service.TelegramUsuarioService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuariotelegram")
public class TelegramUsuarioController {

    private TelegramUsuarioService telegramUsuarioService;

    public TelegramUsuarioController(TelegramUsuarioService telegramUsuarioService) {
        this.telegramUsuarioService = telegramUsuarioService;
    }


    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de usuarios do telegram")
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

    @GetMapping
    @ApiOperation(value = "Realiza a busca de todos os usuarios do telegram cadastrados")
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET usuarios telegram");
            List<TelegramUsuario> telegramUsuarios = telegramUsuarioService.findAll();

            return new ResponseEntity<>(telegramUsuarios, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Realiza a busca de acordo com o id informado")
    public ResponseEntity getUser(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET usuarios do telegram");
            return new ResponseEntity<>(telegramUsuarioService.findByIdUsuario(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
