package br.com.plannic.service;

import br.com.plannic.model.Materia;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.MateriaRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class MateriaServiceTest {

    @InjectMocks
    MateriaService materiaService;

    @Mock
    MateriaRepository repository;

    public MateriaServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    List<Materia> listMateria = new ArrayList<>();


    Materia materia = new Materia(
            1,
            2,
            1,
            1,
            "Matematica",
            "Descrição de Matematica",
            new Usuario(
            1,
            "teste@teste.com.br",
            "123456", "Teste",
            LocalDateTime.now(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            "teste",
            true
    ));


    Materia materia1 = new Materia(
            2,
            2,
            1,
            1,
            "Matematica",
            "Descrição de Matematica",
            new Usuario(
                    1,
                    "teste@teste.com.br",
                    "123456", "Teste",
                    LocalDateTime.now(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "teste",
                    true
            ));

    @Test
    public void getAll_exception() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findAll()).thenReturn(listMateria);

        List<Materia> materias = materiaService.getAll();

    }

    @Test
    public void getAll_success() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        listMateria.add(materia);

        Mockito.when(repository.findAll()).thenReturn(listMateria);

        List<Materia> materia = materiaService.getAll();

    }

    @Test
    public void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.save(materia)).thenReturn(materia);

        materiaService.save(materia);

    }

    @Test
    public void update() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(materia.getIdMateria())).thenReturn(materia);

        materiaService.update(materia);

    }

    @Test
    public void update_false() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(materia.getIdMateria())).thenReturn(null);

        materiaService.update(materia);

    }

    @Test
    public void delete() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(materia.getIdMateria())).thenReturn(materia);

        Mockito.doNothing().when(repository).deleteById(materia.getIdMateria());

        materiaService.delete(materia.getIdMateria());

    }

    @Test
    public void delete_false() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(materia.getIdMateria())).thenReturn(null);

        Mockito.doNothing().when(repository).deleteById(materia.getIdMateria());

        materiaService.delete(materia.getIdMateria());

    }
}
