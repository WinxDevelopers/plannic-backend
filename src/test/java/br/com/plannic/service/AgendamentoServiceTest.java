package br.com.plannic.service;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.AgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class AgendamentoServiceTest {

    @InjectMocks
    AgendamentoService agendamentoService;

    @Mock
    AgendamentoRepository repository;

    public AgendamentoServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    List<Agendamento> listAgendamento = new ArrayList<>();

    Agendamento agendamento = new Agendamento(1, 1, 1, new Date(2021 - 02 - 23), new Date(2021 - 02 - 24), "segunda", "prova", "" ,LocalTime.now(), LocalTime.now(),
            new Usuario(
                    1,
                    "teste@teste.com",
                    "teste123",
                    "teste",
                    LocalDateTime.now(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "codteste",
                    true
            ));

    Agendamento agendamento1 = new Agendamento(1, 1, 1, new Date(2021 - 02 - 25), new Date(2021 - 02 - 24), "segunda", "prova", "", LocalTime.now(), LocalTime.now(),
            new Usuario(
                    1,
                    "teste@teste.com",
                    "teste123",
                    "teste",
                    LocalDateTime.now(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "codteste",
                    true
            ));

    @Test
    public void getAll_exception() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.findAll()).thenReturn(listAgendamento);

        List<Agendamento> agendamentos = agendamentoService.getAll();


    }

    @Test
    public void getAll_success() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        listAgendamento.add(agendamento);

        Mockito.when(repository.findAll()).thenReturn(listAgendamento);

        List<Agendamento> agendamentos = agendamentoService.getAll();

    }

    @Test
    public void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.save(agendamento)).thenReturn(agendamento);

        agendamentoService.save(agendamento);

    }

    @Test
    public void update() {


        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(agendamento.getIdAgendamento())).thenReturn(agendamento);

        agendamentoService.update(agendamento1);

    }

    @Test
    public void update_false() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.findById(agendamento.getIdAgendamento())).thenReturn(null);

        agendamentoService.update(agendamento1);


    }

    @Test
    public void delete() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.findById(agendamento.getIdAgendamento())).thenReturn(agendamento);

        Mockito.doNothing().when(repository).deleteById(agendamento.getIdAgendamento());


        agendamentoService.delete(agendamento.getIdAgendamento());

    }

    @Test
    public void delete_false() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(agendamento.getIdAgendamento())).thenReturn(null);

        Mockito.doNothing().when(repository).deleteById(agendamento.getIdAgendamento());

        agendamentoService.delete(agendamento.getIdAgendamento());

    }
}
