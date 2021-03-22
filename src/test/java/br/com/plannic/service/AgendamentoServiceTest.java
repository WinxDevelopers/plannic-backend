package br.com.plannic.service;

import br.com.plannic.controller.AgendamentoController;
import br.com.plannic.model.Agendamento;
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

@RunWith(MockitoJUnitRunner.class)
class AgendamentoServiceTest {

    @InjectMocks
    AgendamentoController agendamentoController;

    @Mock
    AgendamentoService agendamentoService;

    public AgendamentoServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Agendamento agendamento = new Agendamento(1, 1, 1, new Date(2021 - 02 - 23), new Date(2021 - 02 - 24), "segunda", "prova", LocalTime.now(), LocalTime.now(),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",

                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "codteste",
                        true
                ));

        Mockito.doNothing().when(agendamentoService).save(agendamento);

        Mockito.when(agendamentoService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Agendamento> responseEntity = agendamentoController.save(agendamento);

        agendamentoController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }

    @Test
    public void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Agendamento agendamento = new Agendamento(1, 1, 1, new Date(2021 - 02 - 23), new Date(2021 - 02 - 24), "segunda", "prova", LocalTime.now(), LocalTime.now(),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "codteste",
                        true
                ));

        Mockito.doNothing().when(agendamentoService).save(agendamento);

        ResponseEntity<Agendamento> responseEntity = agendamentoController.save(agendamento);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }

    @Test
    public void update() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Agendamento agendamento = new Agendamento(1, 1, 1, new Date(2021 - 02 - 23), new Date(2021 - 02 - 24), "segunda", "prova", LocalTime.now(), LocalTime.now(),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "codteste",
                        true
                ));

        Agendamento agendamento1 = new Agendamento(1, 2, 1, new Date(2021 - 02 - 23), new Date(2021 - 02 - 24), "segunda", "prova", LocalTime.now(), LocalTime.now(),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "codteste",
                        true
                ));

        Mockito.doNothing().when(agendamentoService).save(agendamento);
        Mockito.when(agendamentoService.update(agendamento1)).thenReturn(true);
        Mockito.when(agendamentoService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Agendamento> responseEntity = agendamentoController.save(agendamento);
        ResponseEntity responseEntityAtualiza = agendamentoController.update(agendamento1);

        agendamentoController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void delete() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Agendamento agendamento = new Agendamento(1, 1, 1, new Date(2021 - 02 - 23), new Date(2021 - 02 - 24), "segunda", "prova", LocalTime.now(), LocalTime.now(),
                new Usuario(
                        1,
                        "teste@teste.com",
                        "teste123",
                        "teste",
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "codteste",
                        true
                ));

        Mockito.doNothing().when(agendamentoService).save(agendamento);
        Mockito.when(agendamentoService.delete(1)).thenReturn(true);
        Mockito.when(agendamentoService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Agendamento> responseEntity = agendamentoController.save(agendamento);

        ResponseEntity responseEntityDelete = agendamentoController.delete(1);

        agendamentoController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(200);
    }
}