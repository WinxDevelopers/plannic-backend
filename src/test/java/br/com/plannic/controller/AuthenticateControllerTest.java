package br.com.plannic.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import br.com.plannic.model.AuthRequest;
import org.junit.jupiter.api.Test;

public class AuthenticateControllerTest {
    @Test
    public void testGenerateToken() throws Exception {
        AuthenticateController authenticateController = new AuthenticateController();
        assertThrows(Exception.class,
                () -> authenticateController.generateToken(new AuthRequest("jane.doe@example.org", "iloveyou")));
    }

    @Test
    public void testGenerateToken2() throws Exception {
        assertThrows(Exception.class, () -> (new AuthenticateController()).generateToken(null));
    }

    @Test
    public void testGenerateToken3() throws Exception {
        assertThrows(Exception.class, () -> (new AuthenticateController()).generateToken(mock(AuthRequest.class)));
    }
}//package br.com.plannic.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//@RunWith(MockitoJUnitRunner.class)
//class AuthenticateControllerTest {
//
//    @Mock
//    AuthenticationManager authenticationManager;
//
//    @Test
//    void generateToken() {
//        authenticationManager.authenticate(new Authentication() {
//            @Override
//            public String getName() {
//                return null;
//            }
//
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return null;
//            }
//
//            @Override
//            public Object getCredentials() {
//                return null;
//            }
//
//            @Override
//            public Object getDetails() {
//                return null;
//            }
//
//            @Override
//            public Object getPrincipal() {
//                return null;
//            }
//
//            @Override
//            public boolean isAuthenticated() {
//                return false;
//            }
//
//            @Override
//            public void setAuthenticated(boolean b) throws IllegalArgumentException {
//
//            }
//        });
//    }
//}