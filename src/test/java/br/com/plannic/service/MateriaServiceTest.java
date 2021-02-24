package br.com.plannic.service;

import br.com.plannic.controller.MateriaController;
import br.com.plannic.controller.UsuarioController;
import br.com.plannic.model.AuthRequest;
import br.com.plannic.model.Materia;
import br.com.plannic.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

    @InjectMocks
    MateriaController materiaController;

    @Mock
    MateriaService materiaService;

    @Test
    public void adicionarMateria() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Materia materia = new Materia(1, 1, "Destinado a matematica", "Matematica");
        ResponseEntity<Materia> responseEntity = materiaController.save(materia);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void encontrarMaterias() {

        Materia materia = new Materia(1, 1, "Destinado a matematica", "Matematica");
        Materia materia1 = new Materia(2, 1, "Destinado a portugues", "Portugues");

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);
        ResponseEntity<Materia> responseEntity1 = materiaController.save(materia1);

        materiaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void deletaMaterias() {

        Materia materia = new Materia(1, 1, "Destinado a matematica", "Matematica");

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);

        ResponseEntity responseEntityDelete = materiaController.delete(materia);

        materiaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityDelete.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    public void atualizarMateria() {

        Materia materia = new Materia(1, 1, "Destinado a matematica", "Matematica");
        Materia materia1 = new Materia(2, 1, "Destinado a portugues", "Portugues");

        ResponseEntity<Materia> responseEntity = materiaController.save(materia);
        ResponseEntity<Materia> responseEntityAtualiza = materiaController.update(materia1);

        materiaController.getAll();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntityAtualiza.getStatusCodeValue()).isEqualTo(204);
    }
}
