package br.com.plannic.controller;

import br.com.plannic.model.*;
import br.com.plannic.service.NotasUsuarioService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notasusuario")
public class NotasUsuarioController {

    private NotasUsuarioService notasUsuarioService;

    @Autowired
    public NotasUsuarioController(NotasUsuarioService notasUsuarioService) {
        this.notasUsuarioService = notasUsuarioService;
    }


    @GetMapping("/{idAvalia}")
    @ApiOperation(value = "Realiza a busca de todas as notas pendentes")
    public ResponseEntity getAvaliacoesPendentes(@PathVariable("idAvalia") int idAvalia) {
        try{
            MDC.put("fluxo", "GET Avaliações Pendentes");
            return new ResponseEntity<>(notasUsuarioService.getAvalia(idAvalia), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @PutMapping
    @ApiOperation(value = "Realiza a atualização de nota")
    public ResponseEntity update(@RequestBody NotasUsuario notasUsuario) {
        try {
            MDC.put("user_id", notasUsuario.getIdAvalia());
            MDC.put("fluxo", "PUT update");
            if(notasUsuarioService.update(notasUsuario)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nota/{idAvaliado}")
    @ApiOperation(value = "Realiza a busca da nota do usuário")
    public ResponseEntity getNotaUsuario(@PathVariable("idAvaliado") int idAvaliado) {
        try{
            MDC.put("fluxo", "GET Nota Usuário");
            return new ResponseEntity<>(notasUsuarioService.getNotaUsuario(idAvaliado), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}