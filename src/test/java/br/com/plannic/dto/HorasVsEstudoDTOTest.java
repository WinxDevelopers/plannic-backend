package br.com.plannic.dto;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class HorasVsEstudoDTOTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    void testDTO() throws Exception {
        assertNotNull(new HorasVsEstudoDTO(1, 1, "teste", 3.4));

        assertThat(HorasVsEstudoDTO.class);
    }

}
