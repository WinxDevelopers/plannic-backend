package br.com.plannic.service;

import br.com.plannic.controller.AgendamentoController;
import br.com.plannic.controller.NotasMateriaController;
import br.com.plannic.model.Agendamento;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class NotasMateriaServiceTest {

    @InjectMocks
    NotasMateriaController notasMateriaController;

    @Mock
    NotasMateriaService notasMateriaService;

    public NotasMateriaServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NotasMateria notasMateria = new NotasMateria(1, 1, 1, 7.0, "p1", new Date(2021 - 02 - 23),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));

        Mockito.doNothing().when(notasMateriaService).save(notasMateria);

        Mockito.when(notasMateriaService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<NotasMateria> responseEntity = notasMateriaController.save(notasMateria);

        notasMateriaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NotasMateria notasMateria = new NotasMateria(1, 1, 1, 7.0, "p1", new Date(2021 - 02 - 23),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));

        Mockito.doNothing().when(notasMateriaService).save(notasMateria);

        ResponseEntity<NotasMateria> responseEntity = notasMateriaController.save(notasMateria);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    void update() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NotasMateria notasMateria = new NotasMateria(1, 1, 1, 7.0, "p1", new Date(2021 - 02 - 23),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));

        NotasMateria notasMateria1 = new NotasMateria(1, 1, 1, 8.0, "p1", new Date(2021 - 02 - 23),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));

        Mockito.doNothing().when(notasMateriaService).save(notasMateria);
        Mockito.when(notasMateriaService.update(notasMateria1)).thenReturn(true);
        Mockito.when(notasMateriaService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<NotasMateria> responseEntity = notasMateriaController.save(notasMateria);
        ResponseEntity responseEntityAtualiza = notasMateriaController.update(notasMateria1);

        notasMateriaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void delete() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NotasMateria notasMateria = new NotasMateria(1, 1, 1, 7.0, "p1", new Date(2021 - 02 - 23),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));


        Mockito.doNothing().when(notasMateriaService).save(notasMateria);
        Mockito.when(notasMateriaService.delete(1)).thenReturn(true);
        Mockito.when(notasMateriaService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<NotasMateria> responseEntity = notasMateriaController.save(notasMateria);

        ResponseEntity responseEntityDelete = notasMateriaController.delete(1);

        notasMateriaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void buscaNotavsData() {
    }

    @Test
    void buscaMaior8() {
    }

    @Test
    void buscaMenor4() {
    }

    @Test
    void buscaNotavsTipoList() {
    }

    @Test
    void notasVsTipoEstudo() {
    }

    @Test
    void horasVsEstudo() {
    }

    @Test
    void notasVsMateria() {
    }
}