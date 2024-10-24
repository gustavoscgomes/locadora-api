package com.locarapp.locadora.infra.security;

import com.locarapp.locadora.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
//    @Autowired
//    private UsuarioRepository repository;
//    @Autowired
//    private TokenService tokenService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var token = this.recoverToken(request);
//        if (token != null) {
//            var username = tokenService.validateToken(token);
//            UserDetails userDetails = repository.findByUsername(username);
//
//            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
//                    userDetails.getAuthorities());
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String recoverToken(HttpServletRequest request) {
//        var authHeader = request.getHeader("Authorization");
//        if (authHeader == null) {
//            return null;
//        }
//        return authHeader.replace("Bearer ", "").trim();
//    }
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var username = tokenService.validateToken(token);
            if (username != null) { // Verifica se o username não é nulo
                UserDetails userDetails = repository.findByUsername(username);

                if (userDetails != null) { // Verifica se o usuário foi encontrado
                    var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "").trim();
    }
}
