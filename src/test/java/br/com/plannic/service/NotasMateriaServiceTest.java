package br.com.plannic.service;

import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.NotasMateriaRepository;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class NotasMateriaServiceTest {

    @InjectMocks
    NotasMateriaService notasMateriaService;

    @Mock
    NotasMateriaRepository repository;

    public NotasMateriaServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    List<NotasMateria> listNotas = new ArrayList<>();

    NotasMateria notasMateria = new NotasMateria(1, 1, 1, 7.0, "p1", new Date(2021 - 02 - 23),
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
                    "teste",
                    true
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


        List<NotasMateria> notasMaterias = notasMateriaService.getAll();

    }

    @Test
    public void getAll_success() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        listNotas.add(notasMateria);

        Mockito.when(repository.findAll()).thenReturn(listNotas);

        List<NotasMateria> notasMaterias = notasMateriaService.getAll();

    }

    @Test
    public void save() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.save(notasMateria)).thenReturn(notasMateria);

        notasMateriaService.save(notasMateria);


    }

    @Test
    public void update() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.findById(notasMateria.getIdMateria())).thenReturn(notasMateria);

        notasMateriaService.update(notasMateria1);


    }

    @Test
    public void update_false() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(repository.findById(notasMateria.getIdMateria())).thenReturn(null);


        notasMateriaService.update(notasMateria1);

    }

    @Test
    public void delete() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(notasMateria.getIdMateria())).thenReturn(notasMateria);

        Mockito.doNothing().when(repository).deleteById(notasMateria.getIdMateria());

        notasMateriaService.delete(notasMateria.getIdMateria());

    }

    @Test
    public void delete_false() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(repository.findById(notasMateria.getIdMateria())).thenReturn(null);

        Mockito.doNothing().when(repository).deleteById(notasMateria.getIdMateria());

        notasMateriaService.delete(notasMateria.getIdMateria());

    }
}
