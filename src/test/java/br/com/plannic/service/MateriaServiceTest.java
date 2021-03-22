package br.com.plannic.service;

import br.com.plannic.controller.MateriaController;
import br.com.plannic.model.Materia;
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

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class MateriaServiceTest {

    @InjectMocks
    MateriaController materiaController;

    @Mock
    MateriaService materiaService;

    public MateriaServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void adicionarMateria() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Materia materia = new Materia(1, 2, "Matematica", "Descrição de Matematica", new Usuario(
                1, "teste@teste.com.br", "123456", "Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true
        ));

        Mockito.doNothing().when(materiaService).save(materia);

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void encontrarMaterias() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Materia materia = new Materia(1, 2, "Matematica", "Descrição de Matematica", new Usuario(
                1, "teste@teste.com.br", "123456", "Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true
        ));

        Materia materia1 = new Materia(2, 3, "Portugues", "Descrição de Portugues", new Usuario(
                1, "teste@teste.com.br", "123456", "Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true
        ));

        Mockito.doNothing().when(materiaService).save(materia);

        Mockito.when(materiaService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);
        ResponseEntity<Materia> responseEntity1 = materiaController.save(materia1);

        materiaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void deletaMaterias() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Materia materia = new Materia(1, 2, "Matematica", "Descrição de Matematica", new Usuario(
                1, "teste@teste.com.br", "123456", "Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true
        ));

        Mockito.doNothing().when(materiaService).save(materia);
        Mockito.when(materiaService.delete(1)).thenReturn(true);
        Mockito.when(materiaService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);

        ResponseEntity responseEntityDelete = materiaController.delete(1);

        materiaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void atualizarMateria() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Materia materia = new Materia(1, 2, "Matematica", "Descrição de Matematica", new Usuario(
                1, "teste@teste.com.br", "123456", "Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true
        ));


        Materia materia1 = new Materia(1, 2, "Portugues", "Descrição de Portugues", new Usuario(
                1, "teste@teste.com.br", "123456", "Teste", LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),"teste",true
        ));

        Mockito.doNothing().when(materiaService).save(materia);
        Mockito.when(materiaService.update(materia1)).thenReturn(true);
        Mockito.when(materiaService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);
        ResponseEntity responseEntityAtualiza = materiaController.update(materia1);

        materiaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(200);
    }
}
