package br.com.plannic.controller;


import br.com.plannic.model.AuthRequest;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.UsuarioService;
import br.com.plannic.util.JwtUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping()
public class AuthenticateController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String welcome() {
        return "WINXS IFSP";
    }

    private static Logger logger = Logger.getLogger(AuthenticateController.class);

    @PostMapping("authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            logger.info("Autenticando o usuário.");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            Usuario usuario = usuarioService.findByEmail(authRequest.getEmail());
            String token = (jwtUtil.generateToken(authRequest.getEmail()));
            HashMap jsonMessage= new HashMap();
            jsonMessage.put("token", token);
            jsonMessage.put("idUsuario", usuario.getIdUsuario());
            jsonMessage.put("nome",usuario.getNome());

            return new ResponseEntity<>(jsonMessage, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            logger.error("Erro na autenticação do usuário");
            throw new Exception("Erro na autenticação do usuário.");

        }

    }
}
