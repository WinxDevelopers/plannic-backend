package br.com.plannic.controller;

import br.com.plannic.model.AuthRequest;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.UsuarioService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.utility.RandomString;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;


@RestController
@RequestMapping("/forgot")
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioService service;

//    @PostMapping("/{email}")
//    @ApiOperation(value = "Busca as horas gasta e a nota da materia, para criação do grafico")
//    public ResponseEntity<Usuario> enviaEmailResetSenha(@PathVariable final String email, HttpServletRequest request) {
//        try{
//            MDC.put("fluxo", "GET notas vs tipo de estudo");
//            String token = RandomString.make(30);
//            String resetPasswordLink = getSiteURL(request) + "/reset_password?token=" + token;
//            sendEmail(email, resetPasswordLink);
//            Usuario usuario = service.updateResetPassword(token,email);
//
//            return new ResponseEntity<>(usuario, HttpStatus.OK);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } finally {
//            MDC.clear();
//        }
//        return null;
//    }
//    private String getSiteURL(HttpServletRequest request) {
//        String siteURL = request.getRequestURL().toString();
//        return siteURL.replace(request.getServletPath(), "");
//    }
//
//    public void sendEmail(String recipientEmail, String link)
//            throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("contact@shopme.com", "Shopme Support");
//        helper.setTo(recipientEmail);
//
//        String subject = "Here's the link to reset your password";
//
//        String content = "<p>Hello,</p>"
//                + "<p>You have requested to reset your password.</p>"
//                + "<p>Click the link below to change your password:</p>"
//                + "<p><a href=\"" + link + "\">Change my password</a></p>"
//                + "<br>"
//                + "<p>Ignore this email if you do remember your password, "
//                + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
//
//
//    @GetMapping("{email}/reset_password/{token}")
//    @ApiOperation(value = "Realiza a verificação do email do usuario assim que o mesmo se cadastra")
//    public ResponseEntity<?> verifyUser(@Param("email") String email,@RequestBody AuthRequest authRequest) {
//        Usuario usuario = service.findByEmail(email);
//
//        if (service.updatePassword(usuario,authRequest.getPassword())) {
//            return ResponseEntity.status(HttpStatus.FOUND)
//                    .location(URI.create("https://plannic.herokuapp.com/login"))
//                    .build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .location(URI.create("https://plannic.herokuapp.com/email"))
//                    .build();
//        }
//    }

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
