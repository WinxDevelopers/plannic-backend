package br.com.plannic.service;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.NotasTutor;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.NotasTutorRepository;
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
class NotasTutorServiceTest {

    @InjectMocks
    NotasTutorService notasTutorService;

    @Mock
    NotasTutorRepository repository;

    public NotasTutorServiceTest() {
        MockitoAnnotations.initMocks(this);
    }


    List<NotasTutor> listNotas = new ArrayList<>();

    NotasTutor notasTutor = new NotasTutor(
            1, 1, 3, 2, "descricao", 1,
            new Usuario(
                    1,
                    "teste@teste.com",
                    "teste123",
                    "teste",
                    LocalDateTime.now(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "teste",
                    true
            ));

    NotasTutor notasTutor1 = new NotasTutor(
            1, 1, 3, 5, "descricao", 1,
            new Usuario(
                    1,
                    "teste@teste.com",
                    "teste123",
                    "teste",
                    LocalDateTime.now(),
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

        Mockito.when(repository.findAll()).thenReturn(listNotas);

        List<NotasTutor> notasTutor = notasTutorService.getAll();

    }

    @Test
    public void getAll_success() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        listNotas.add(notasTutor);

        Mockito.when(repository.findAll()).thenReturn(listNotas);

        List<NotasTutor> notasTutors = notasTutorService.getAll();

    }

    @Test
    public void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.save(notasTutor)).thenReturn(notasTutor);

        notasTutorService.save(notasTutor);

    }

    @Test
    public void update() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(notasTutor.getIdUsuarioTutor())).thenReturn(java.util.Optional.ofNullable(notasTutor));

        notasTutorService.update(notasTutor);

    }


    @Test
    public void delete() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(notasTutor.getIdUsuarioTutor())).thenReturn(java.util.Optional.ofNullable(notasTutor));

        Mockito.doNothing().when(repository).deleteById(notasTutor.getIdUsuarioTutor());

        notasTutorService.delete(notasTutor);

    }
}

