package br.com.plannic.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotasTutorService.class})
@ExtendWith(SpringExtension.class)
public class NotasTutorServiceTest {


    @Autowired
    private NotasTutorService notasTutorService;


}

