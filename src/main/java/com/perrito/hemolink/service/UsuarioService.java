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

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
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
        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        usuarioExistente.setTipo(usuarioAtualizado.getTipo());
        usuarioExistente.setCelular(usuarioAtualizado.getCelular());
        usuarioExistente.setRegiao(usuarioAtualizado.getRegiao());

        if (!usuarioAtualizado.getSenha().equals(usuarioExistente.getSenha())) {
            String senhaCriptografada = passwordEncoder.encode(usuarioAtualizado.getSenha());
            usuarioExistente.setSenha(senhaCriptografada);
        }

        if (usuarioAtualizado.getRequisicao() != null) {
            usuarioExistente.setRequisicao(usuarioAtualizado.getRequisicao());
        }

        usuarioRepository.save(usuarioExistente);
        return true;
    }
    return false;
}

 

	public String[] getRegioes() {
		return new String[] {
			"Posto Clínicas - Av. Dr. Enéas de Carvalho Aguiar, 155 - Cerqueira César, São Paulo - SP, 05403-000",
			"Posto Dante Pazzanese - Av. Dr. Dante Pazzanese, 500 - Vila Mariana, São Paulo - SP, 04012-030",
			"Posto Mandaqui - Rua Voluntários da Pátria, 4.227 - Mandaqui, São Paulo - SP, 02310-000",
			"Posto Regional De Osasco - Rua Ari Barroso, 355, Presidente Altino, Osasco - SP, 06093-020",
			"Posto Barueri - Rua Guilhermina Carril Loureiro, 144 - Centro, Barueri - SP, 06401-020"
	};
}

	
	public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
