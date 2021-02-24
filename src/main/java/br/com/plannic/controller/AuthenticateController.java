package br.com.plannic.controller;


import br.com.plannic.model.AuthRequest;
import br.com.plannic.util.JwtUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class AuthenticateController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "WINXS IFSP";
    }

    private static Logger logger = Logger.getLogger(AuthenticateController.class);

    @PostMapping("authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            logger.info("Autenticando o usuário.");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            logger.error("Erro na autenticação do usuário");
            throw new Exception("Erro na autenticação do usuário.");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
}
