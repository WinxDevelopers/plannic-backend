package br.com.plannic.filter;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import br.com.plannic.service.CustomUserDetailsService;
import br.com.plannic.util.JwtUtil;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomUserDetailsService.class, JwtUtil.class, JwtFilter.class})
@ExtendWith(SpringExtension.class)
public class JwtFilterTest {
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void testDoFilterInternal() throws IOException, ServletException {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        Response httpServletResponse = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
        this.jwtFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
        verify(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    }
}

