package br.com.plannic;

import br.com.plannic.controller.AuthenticateController;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class PlannicApplication {

	private static Logger logger = Logger.getLogger(AuthenticateController.class);

	public static void main(String[] args) {
		System.setProperty("DATABASE_PASSWORD", System.getenv("DATABASE_PASSWORD"));
		System.setProperty("DATABASE_USERNAME", System.getenv("DATABASE_USERNAME"));
		System.setProperty("DB_URL", System.getenv("DB_URL"));
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		SpringApplication.run(PlannicApplication.class, args);
	}
}
