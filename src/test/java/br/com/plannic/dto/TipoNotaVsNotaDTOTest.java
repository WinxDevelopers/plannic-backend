package br.com.plannic.dto;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class TipoNotaVsNotaDTOTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    void testDTO() throws Exception {
        assertNotNull(new TipoNotaVsNotaDTO("teste", 3.4));

        assertThat(TipoNotaVsNotaDTO.class);
    }

}
