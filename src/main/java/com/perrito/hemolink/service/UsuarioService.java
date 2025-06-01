package com.perrito.hemolink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario createUsuario(Usuario usuario) {
		//Bcrypt
		String senha = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senha));
        
        return usuarioRepository.save(usuario);
	}
	
	public boolean login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null && passwordEncoder.matches(senha, usuario.getSenha());
    }
	
	public Usuario getUsuarioByCodigo(int codigo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(codigo);
        return optionalUsuario.orElse(null);
    }
    public boolean deleteUsuario(String email) {
    	Usuario usuario = usuarioRepository.findByEmail(email);
	if (usuario != null) {
		usuarioRepository.delete(usuario);
		return true;
	}
    	return false;
}

	
	public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
