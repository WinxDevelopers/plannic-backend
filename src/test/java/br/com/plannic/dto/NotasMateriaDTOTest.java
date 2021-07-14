package br.com.plannic.dto;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class NotasMateriaDTOTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    void testDTO() throws Exception {
        assertNotNull(new NotasMateriaDTO(2.3, "teste"));

        assertThat(NotasMateriaDTO.class);
    }

}
