package br.com.plannic.dto;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


@RunWith(MockitoJUnitRunner.class)
class DataVsNotaDTOTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    void testDTO() throws Exception {
        assertNotNull(new DataVsNotaDTO(1, 3.0, Date.from(Instant.now()), "teste"));

        assertThat(DataVsNotaDTO.class);
    }

}
