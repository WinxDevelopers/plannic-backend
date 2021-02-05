package br.com.plannic.controller;

import br.com.plannic.model.Usuario;
import br.com.plannic.service.UsuarioService;
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

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario){
        try {
            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "POST save");
            usuarioService.save(usuario);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody Usuario usuario) {
        try {
            MDC.put("user_id", usuario.getId());
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

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Usuario usuario) {
        try {
            MDC.put("user_id", usuario.getId());
            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "DELETE delete");
            if (usuarioService.delete(usuario)) {
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
}
