package web.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }


       // System.out.println("================" + roles.toString());
        httpServletRequest.getSession().setAttribute("userName",authentication.getName());

        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin/usersPage");
        } else if (roles.contains("USER")) {
            httpServletResponse.sendRedirect("/user");
          //  System.out.println(  "name ====== " + authentication.getName());
        } else {
            throw new RuntimeException("The user has not roles");
        }
    }

}