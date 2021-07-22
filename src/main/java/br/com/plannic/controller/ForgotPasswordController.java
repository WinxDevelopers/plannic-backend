package br.com.plannic.controller;


import br.com.plannic.service.UsuarioService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.io.IOException;



@RestController
@RequestMapping("/forgot")
public class ForgotPasswordController {


    @Autowired
    private UsuarioService service;


    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) throws MessagingException, TemplateException, IOException {

        String response = service.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,@RequestParam String password) {
        return service.resetPassword(token, password);
    }




}
