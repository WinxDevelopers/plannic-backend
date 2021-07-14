package br.com.plannic.controller;

import br.com.plannic.model.NotasTutor;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.NotasTutorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class NotasTutorControllerTest {

    @InjectMocks
    NotasTutorController notasTutorController;

    @Mock
    NotasTutorService notasTutorService;


    public NotasTutorControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    NotasTutor notasTutor = new NotasTutor(
            1, 1, 1, 5, "Matematica", 1,
            new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "codteste",
                    true)
    );

    @Test
    public void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.doNothing().when(notasTutorService).save(notasTutor);
        ResponseEntity<NotasTutor> responseEntity = notasTutorController.save(notasTutor);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    public void update() {

        NotasTutor notasTutor1 = new NotasTutor(
                1, 1, 1, 5, "Matematica", 1,
                new Usuario(1, "usuarioteste@gmail.com", "senha123", "Usuario Teste", LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "codteste",
                        true)
        );

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.doNothing().when(notasTutorService).save(notasTutor);
        Mockito.when(notasTutorService.update(notasTutor1)).thenReturn(true);
        Mockito.when(notasTutorService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<NotasTutor> responseEntity = notasTutorController.save(notasTutor);
        ResponseEntity responseEntityAtualiza = notasTutorController.update(notasTutor1);

        notasTutorController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void delete() {

        Mockito.doNothing().when(notasTutorService).save(notasTutor);
        Mockito.when(notasTutorService.delete(notasTutor)).thenReturn(true);
        Mockito.when(notasTutorService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<NotasTutor> responseEntity = notasTutorController.save(notasTutor);

        ResponseEntity responseEntityDelete = notasTutorController.delete(notasTutor);

        notasTutorController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getAll() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.doNothing().when(notasTutorService).save(notasTutor);

        Mockito.when(notasTutorService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<NotasTutor> responseEntity = notasTutorController.save(notasTutor);

        notasTutorController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

}
