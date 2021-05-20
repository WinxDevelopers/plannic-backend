package br.com.plannic.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import br.com.plannic.service.NotasMateriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {NotasMateriaController.class})
@ExtendWith(SpringExtension.class)
public class NotasMateriaControllerTest {
    @Autowired
    private NotasMateriaController notasMateriaController;

    @MockBean
    private NotasMateriaService notasMateriaService;

    @Test
    public void testGetHorasVsNota() throws Exception {
        when(this.notasMateriaService.horasVsEstudo((Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notasMateria/horasvsnota/{idusuario}",
                1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNotaMaior8() throws Exception {
        when(this.notasMateriaService.buscaMaior8((Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notasMateria/notamaior/{idusuario}", 1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNotaMenor8() throws Exception {
        when(this.notasMateriaService.buscaMenor4((Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notasMateria/notamenor/{idusuario}", 1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNotaVsData() throws Exception {
        when(this.notasMateriaService.buscaNotavsData((Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/notasMateria/notasvsdata/{idusuario}/{idmateira}", 1, 1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNotaVsMatsferia() throws Exception {
        when(this.notasMateriaService.notasVsMateria((Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/notasMateria/notasvsMateria/{idusuario}", 1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNotaVsTipo() throws Exception {
        when(this.notasMateriaService.buscaNotavsTipoList((Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notasMateria/notastipo/{idusuario}", 1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNotasvsTipo() throws Exception {
        when(this.notasMateriaService.notasVsTipoEstudo((Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notasMateria/notasvstipo/{idusuario}",
                1);
        MockMvcBuilders.standaloneSetup(this.notasMateriaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

