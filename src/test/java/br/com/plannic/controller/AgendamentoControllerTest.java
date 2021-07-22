package br.com.plannic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.AgendamentoRepository;
import br.com.plannic.service.AgendamentoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AgendamentoController.class})
@ExtendWith(SpringExtension.class)
public class AgendamentoControllerTest {
    @Autowired
    private AgendamentoController agendamentoController;

    @MockBean
    private AgendamentoService agendamentoService;

    @Test
    public void testConstructor() {
        AgendamentoService agendamentoService = new AgendamentoService(mock(AgendamentoRepository.class));
        ResponseEntity all = (new AgendamentoController(agendamentoService)).getAll();
        Object body = all.getBody();
        assertTrue(((Collection<Object>) body).isEmpty());
        assertEquals("<200 OK OK,[],[]>", all.toString());
        assertTrue(all.hasBody());
        assertEquals(200, all.getStatusCodeValue());
        assertEquals(HttpStatus.OK, all.getStatusCode());
        assertTrue(all.getHeaders().isEmpty());
        assertSame(body, agendamentoService.getAll());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setAtivo(true);
        usuario.setTokenCreationDate(null);
        usuario.setTokenReset("ABC123");
        usuario.setCodVerifica("Cod Verifica");
        usuario.setTelegramUsuario(null);
        usuario.setData(null);
        usuario.setAgendamentos(null);

        Agendamento agendamento = new Agendamento();
        agendamento.setHoraInicio(null);
        agendamento.setRecorrencia("Recorrencia");
        agendamento.setIdMateria(1);
        agendamento.setTipoEstudo("Tipo Estudo");
        agendamento.setIdUsuario(1);
        agendamento.setIdAgendamento(1);
        agendamento.setTempoNotificacao("Tempo Notificacao");
        agendamento.setUsuario(usuario);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento.setRecorrenciaFim(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento.setRecorrenciaInicio(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        agendamento.setHoraFim(null);
        AgendamentoRepository agendamentoRepository = mock(AgendamentoRepository.class);
        when(agendamentoRepository.save((Agendamento) any())).thenReturn(agendamento);
        AgendamentoController agendamentoController = new AgendamentoController(
                new AgendamentoService(agendamentoRepository));
        ResponseEntity<Agendamento> actualSaveResult = agendamentoController.save(new Agendamento("Tipo Estudo"));
        assertNull(actualSaveResult.getBody());
        assertEquals("<201 CREATED Created,[]>", actualSaveResult.toString());
        assertEquals(HttpStatus.CREATED, actualSaveResult.getStatusCode());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        verify(agendamentoRepository).save((Agendamento) any());
    }

    @Test
    public void testSave2() {
        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setAtivo(true);
        usuario.setTokenCreationDate(null);
        usuario.setTokenReset("ABC123");
        usuario.setCodVerifica("Cod Verifica");
        usuario.setTelegramUsuario(null);
        usuario.setData(null);
        usuario.setAgendamentos(null);

        Agendamento agendamento = new Agendamento();
        agendamento.setHoraInicio(null);
        agendamento.setRecorrencia("Recorrencia");
        agendamento.setIdMateria(1);
        agendamento.setTipoEstudo("Tipo Estudo");
        agendamento.setIdUsuario(1);
        agendamento.setIdAgendamento(1);
        agendamento.setTempoNotificacao("Tempo Notificacao");
        agendamento.setUsuario(usuario);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento.setRecorrenciaFim(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento.setRecorrenciaInicio(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        agendamento.setHoraFim(null);
        AgendamentoRepository agendamentoRepository = mock(AgendamentoRepository.class);
        when(agendamentoRepository.save((Agendamento) any())).thenReturn(agendamento);
        AgendamentoController agendamentoController = new AgendamentoController(
                new AgendamentoService(agendamentoRepository));
        Agendamento agendamento1 = mock(Agendamento.class);
        when(agendamento1.getIdAgendamento()).thenReturn(1);
        ResponseEntity<Agendamento> actualSaveResult = agendamentoController.save(agendamento1);
        assertNull(actualSaveResult.getBody());
        assertEquals("<201 CREATED Created,[]>", actualSaveResult.toString());
        assertEquals(HttpStatus.CREATED, actualSaveResult.getStatusCode());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        verify(agendamentoRepository).save((Agendamento) any());
        verify(agendamento1).getIdAgendamento();
    }

    @Test
    public void testUpdate() {
        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setAtivo(true);
        usuario.setTokenCreationDate(null);
        usuario.setTokenReset("ABC123");
        usuario.setCodVerifica("Cod Verifica");
        usuario.setTelegramUsuario(null);
        usuario.setData(null);
        usuario.setAgendamentos(null);

        Agendamento agendamento = new Agendamento();
        agendamento.setHoraInicio(null);
        agendamento.setRecorrencia("Recorrencia");
        agendamento.setIdMateria(1);
        agendamento.setTipoEstudo("Tipo Estudo");
        agendamento.setIdUsuario(1);
        agendamento.setIdAgendamento(1);
        agendamento.setTempoNotificacao("Tempo Notificacao");
        agendamento.setUsuario(usuario);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento.setRecorrenciaFim(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento.setRecorrenciaInicio(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        agendamento.setHoraFim(null);

        Usuario usuario1 = new Usuario();
        usuario1.setNotasMateria(null);
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setPassword("iloveyou");
        usuario1.setMaterias(null);
        usuario1.setNome("Nome");
        usuario1.setIdUsuario(1);
        usuario1.setAtivo(true);
        usuario1.setTokenCreationDate(null);
        usuario1.setTokenReset("ABC123");
        usuario1.setCodVerifica("Cod Verifica");
        usuario1.setTelegramUsuario(null);
        usuario1.setData(null);
        usuario1.setAgendamentos(null);

        Agendamento agendamento1 = new Agendamento();
        agendamento1.setHoraInicio(null);
        agendamento1.setRecorrencia("Recorrencia");
        agendamento1.setIdMateria(1);
        agendamento1.setTipoEstudo("Tipo Estudo");
        agendamento1.setIdUsuario(1);
        agendamento1.setIdAgendamento(1);
        agendamento1.setTempoNotificacao("Tempo Notificacao");
        agendamento1.setUsuario(usuario1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento1.setRecorrenciaFim(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendamento1.setRecorrenciaInicio(Date.from(atStartOfDayResult3.atZone(ZoneId.systemDefault()).toInstant()));
        agendamento1.setHoraFim(null);
        AgendamentoRepository agendamentoRepository = mock(AgendamentoRepository.class);
        when(agendamentoRepository.save((Agendamento) any())).thenReturn(agendamento1);
        when(agendamentoRepository.findById(anyInt())).thenReturn(agendamento);
        AgendamentoController agendamentoController = new AgendamentoController(
                new AgendamentoService(agendamentoRepository));
        ResponseEntity actualUpdateResult = agendamentoController.update(new Agendamento("Tipo Estudo"));
        assertNull(actualUpdateResult.getBody());
        assertEquals("<200 OK OK,[]>", actualUpdateResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(agendamentoRepository).findById(anyInt());
        verify(agendamentoRepository).save((Agendamento) any());
    }

    @Test
    public void testUpdate2() {
        AgendamentoService agendamentoService = mock(AgendamentoService.class);
        when(agendamentoService.update((Agendamento) any())).thenReturn(true);
        AgendamentoController agendamentoController = new AgendamentoController(agendamentoService);
        ResponseEntity actualUpdateResult = agendamentoController.update(new Agendamento("Tipo Estudo"));
        assertNull(actualUpdateResult.getBody());
        assertEquals("<200 OK OK,[]>", actualUpdateResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(agendamentoService).update((Agendamento) any());
    }

    @Test
    public void testUpdate3() {
        AgendamentoService agendamentoService = mock(AgendamentoService.class);
        when(agendamentoService.update((Agendamento) any())).thenReturn(false);
        AgendamentoController agendamentoController = new AgendamentoController(agendamentoService);
        ResponseEntity actualUpdateResult = agendamentoController.update(new Agendamento("Tipo Estudo"));
        assertNull(actualUpdateResult.getBody());
        assertEquals("<204 NO_CONTENT No Content,[]>", actualUpdateResult.toString());
        assertEquals(HttpStatus.NO_CONTENT, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(agendamentoService).update((Agendamento) any());
    }

    @Test
    public void testUpdate4() {
        AgendamentoService agendamentoService = mock(AgendamentoService.class);
        when(agendamentoService.update((Agendamento) any())).thenReturn(true);
        AgendamentoController agendamentoController = new AgendamentoController(agendamentoService);
        Agendamento agendamento = mock(Agendamento.class);
        when(agendamento.getTipoEstudo()).thenReturn("foo");
        when(agendamento.getIdAgendamento()).thenReturn(1);
        ResponseEntity actualUpdateResult = agendamentoController.update(agendamento);
        assertNull(actualUpdateResult.getBody());
        assertEquals("<200 OK OK,[]>", actualUpdateResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(agendamentoService).update((Agendamento) any());
        verify(agendamento).getIdAgendamento();
        verify(agendamento).getTipoEstudo();
    }

    @Test
    public void testDelete() throws Exception {
        when(this.agendamentoService.delete(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/agendamento/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.agendamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete2() throws Exception {
        when(this.agendamentoService.delete(anyInt())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/agendamento/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendamentoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetAll() throws Exception {
        when(this.agendamentoService.getAll()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendamento");
        MockMvcBuilders.standaloneSetup(this.agendamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

