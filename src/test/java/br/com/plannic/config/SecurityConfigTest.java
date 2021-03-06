package br.com.plannic.config;

import br.com.plannic.filter.JwtFilter;
import br.com.plannic.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@RunWith(MockitoJUnitRunner.class)
class SecurityConfigTest {

    @InjectMocks
    SecurityConfig securityConfig;

    @Mock
    CustomUserDetailsService userDetailsService;

    @Mock
    JwtFilter jwtFilter;

    @Mock
    AuthenticationManagerBuilder auth;

    public SecurityConfigTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void configure() throws Exception {
        // Mockito.when(auth.userDetailsService(Mockito.any())).getMock();
    }


}
