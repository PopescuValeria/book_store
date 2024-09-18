package md.usm.book_store.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.usm.book_store.constants.Roles;
import md.usm.book_store.constants.UserAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static md.usm.book_store.security.jwt.JwtUtil.extractJwtFromCookies;
import static md.usm.book_store.security.jwt.JwtUtil.getUserAttributeFromToken;

@Component
public class ThymeleafContextInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && request.getCookies() != null){
            String jwtToken = extractJwtFromCookies(request);
            if (jwtToken != null){
                Integer role = (Integer) getUserAttributeFromToken(jwtToken, UserAttributes.ROLE);
                modelAndView.addObject("isAdmin", Objects.equals(role, Roles.ADMIN));
                modelAndView.addObject("request", request);
            }
        }
    }
}
