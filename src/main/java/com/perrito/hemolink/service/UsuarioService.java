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
    public boolean deleteUsuario(int codigo) {
    	Usuario usuario = usuarioRepository.findById(codigo).orElse(null);
	if (usuario != null) {
		usuarioRepository.delete(usuario);
		return true;
	}
    	return false;
}

    public boolean atualizarUsuario(int codigo, Usuario usuarioAtualizado) {
	Optional<Usuario> optionalUsuario = usuarioRepository.findById(codigo);
	if (optionalUsuario.isPresent()) {
		Usuario usuarioExistente = optionalUsuario.get();

		usuarioExistente.setNome(usuarioAtualizado.getNome());
		usuarioExistente.setEmail(usuarioAtualizado.getEmail());

		if (!usuarioAtualizado.getSenha().equals(usuarioExistente.getSenha())) {
			String senhaCriptografada = passwordEncoder.encode(usuarioAtualizado.getSenha());
			usuarioExistente.setSenha(senhaCriptografada);
		}

		usuarioRepository.save(usuarioExistente);
		return true;
	}
	return false;
}


	
	public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
