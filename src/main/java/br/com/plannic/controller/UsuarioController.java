package br.com.plannic.controller;

import br.com.plannic.model.Usuario;
import br.com.plannic.service.UsuarioService;
import br.com.plannic.service.EmailService;
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
import java.io.UnsupportedEncodingException;
import java.net.URI;

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
    @ApiOperation(value = "Realiza o cadastro do usuario")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario,HttpServletRequest request)
    throws UnsupportedEncodingException, MessagingException
    {
        try {
            MDC.put("name", usuario.getNome());
            MDC.put("fluxo", "POST save");
//            emailService.welcome(usuario);
            usuarioService.save(usuario,getSiteURL(request));
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    @ApiOperation(value = "Realiza a verificação do email do usuario assim que o mesmo se cadastra")
    public ResponseEntity<?> verifyUser(@Param("code") String code) {
        if (usuarioService.verify(code)) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("https://plannic.herokuapp.com/login"))
                    .build();
        } else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .location(URI.create("https://plannic.herokuapp.com"))
                    .build();
        }
    }


    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }


    @PutMapping
    @ApiOperation(value = "Atualiza dados cadastrados do usuario")
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
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/redefinicao")
    @ApiOperation(value = "Realiza a redefinicao de senha do usuario")
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

    @PostMapping("/atualizaverificaemail")
    @ApiOperation(value = "Envia novamente o email para o usuario caso ele nao tenha recebido o email de verificacao")
    public ResponseEntity<Usuario> updateCodeVerifica(@RequestBody Usuario usuario,HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException
    {
        try {
//            MDC.put("name", usuario.getNome());
//            MDC.put("fluxo", "POST save");
//            emailService.welcome(usuario);
            if(usuarioService.updateCodigoVerifica(usuario,getSiteURL(request))) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a delecao do usuario pelo id")
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
    @ApiOperation(value = "Realiza a busca de todos os usuarios cadastrados")
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET usuarios");
            return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Realiza a busca de acordo com o id informado")
    public ResponseEntity getUser(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET usuarios");
            return new ResponseEntity<>(usuarioService.getUser(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
