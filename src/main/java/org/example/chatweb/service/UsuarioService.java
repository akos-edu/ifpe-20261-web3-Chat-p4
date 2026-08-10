package org.example.chatweb.service;

import org.example.chatweb.dto.RegistroForm;
import org.example.chatweb.model.Usuario;
import org.example.chatweb.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario cadastrar(RegistroForm form) {
        if (usuarioRepository.existsByEmail(form.getEmail())) {
            throw new IllegalArgumentException("Ja existe um aluno cadastrado com este e-mail");
        }

        Usuario usuario = new Usuario(
                form.getNome(),
                form.getEmail(),
                passwordEncoder.encode(form.getSenha())
        );

        return usuarioRepository.save(usuario);
    }
}
