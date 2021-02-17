package br.com.plannic.service;

import br.com.plannic.model.Usuario;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Transactional
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    public void welcome(Usuario usuario){

        var emailDestino = usuario.getEmail();
        var assunto = "Confirmação de Cadastro";
        var message = "Olá, " + usuario.getNome() + "!" +
                "\n\nObrigado por se cadastrar no Plannic!";
        var email = new SimpleMailMessage();
        email.setTo(emailDestino);
        email.setSubject(assunto);
        email.setFrom("no-reply@plannic.com");
        email.setText(message);
        sender.send(email);
    }
}
