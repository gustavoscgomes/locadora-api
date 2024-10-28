package com.locarapp.locadora.service;

import com.locarapp.locadora.domain.CartaoDeCredito;
import com.locarapp.locadora.domain.Usuario;
import com.locarapp.locadora.repository.CartaoDeCreditoRepository;
import com.locarapp.locadora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CartaoDeCreditoRepository cartaoDeCreditoRepository;

    public Usuario adicionarCartao(CartaoDeCredito cartaoDeCredito) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Recupera o usuario pelo nome de usuario
        Usuario usuario = usuarioRepository.findUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        cartaoDeCredito.setUsuario(usuario);

        CartaoDeCredito cartaoDeCreditoNovo = cartaoDeCreditoRepository.save(cartaoDeCredito);

        usuario.setCartaoDeCredito(cartaoDeCreditoNovo);

        return usuarioRepository.save(usuario);
    }
}
