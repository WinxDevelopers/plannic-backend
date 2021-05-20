package br.com.plannic.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import br.com.plannic.model.Usuario;
import br.com.plannic.service.EmailService;
import br.com.plannic.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UsuarioController.class})
@ExtendWith(SpringExtension.class)
public class UsuarioControllerTest {
    @MockBean
    private EmailService emailService;

    @Autowired
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void testUpdatePassword() throws Exception {
        when(this.usuarioService.updatePassword(anyInt(), anyString())).thenReturn(true);

        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setCodVerifica("Cod Verifica");
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setData(null);
        usuario.setAtivo(true);
        usuario.setAgendamentos(null);
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/usuario/redefinicao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdatePassword2() throws Exception {
        when(this.usuarioService.updatePassword(anyInt(), anyString())).thenReturn(false);

        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setCodVerifica("Cod Verifica");
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setData(null);
        usuario.setAtivo(true);
        usuario.setAgendamentos(null);
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/usuario/redefinicao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetUser() throws Exception {
        when(this.usuarioService.getUser(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usuario/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateCodeVerifica() throws Exception {
        when(this.usuarioService.updateCodigoVerifica((Usuario) any(), anyString())).thenReturn(true);

        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setCodVerifica("Cod Verifica");
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setData(null);
        usuario.setAtivo(true);
        usuario.setAgendamentos(null);
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/usuario/atualizaverificaemail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateCodeVerifica2() throws Exception {
        when(this.usuarioService.updateCodigoVerifica((Usuario) any(), anyString())).thenReturn(false);

        Usuario usuario = new Usuario();
        usuario.setNotasMateria(null);
        usuario.setEmail("jane.doe@example.org");
        usuario.setPassword("iloveyou");
        usuario.setMaterias(null);
        usuario.setCodVerifica("Cod Verifica");
        usuario.setNome("Nome");
        usuario.setIdUsuario(1);
        usuario.setData(null);
        usuario.setAtivo(true);
        usuario.setAgendamentos(null);
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/usuario/atualizaverificaemail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testVerifyUser() throws Exception {
        when(this.usuarioService.verify(anyString())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usuario/verify").param("code", "foo");
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("https://plannic.herokuapp.com/login"));
    }

    @Test
    public void testVerifyUser2() throws Exception {
        when(this.usuarioService.verify(anyString())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usuario/verify").param("code", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.redirectedUrl("https://plannic.herokuapp.com/email"));
    }
}

