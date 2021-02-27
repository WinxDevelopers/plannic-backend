package br.com.plannic.controller;

import br.com.plannic.model.Usuario;
import br.com.plannic.service.UsuarioService;
import br.com.plannic.service.EmailService;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;
    private EmailService emailService;

    @Autowired
    //TODO voltar a parte do email
    public UsuarioController(UsuarioService usuarioService,
                             EmailService emailService) {
        this.usuarioService = usuarioService;
        this.emailService = emailService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario){
        try {
            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "POST save");
//            emailService.welcome(usuario);
            usuarioService.save(usuario);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody Usuario usuario) {
        try {
            MDC.put("user_id", usuario.getIdUsuario());
            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "PUT update");
            if(usuarioService.update(usuario)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/redefinicao")
    public ResponseEntity updatePassword(@Valid @RequestBody Usuario usuario) {
        try {
//            MDC.put("user_id", usuario.getIdUsuario());
//            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "PUT update");
            if(usuarioService.updatePassword(usuario.getIdUsuario(), usuario.getPassword())) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
//            MDC.put("user_id", usuario.getIdUsuario());
//            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "DELETE delete");
            if (usuarioService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET usuarios");
            return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET usuarios");
            return new ResponseEntity<>(usuarioService.getUser(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
