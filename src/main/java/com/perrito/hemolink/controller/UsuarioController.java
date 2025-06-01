package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Usuario usuario) {
        usuarioService.createUsuario(usuario);
        return "Usuário criado com sucesso!";
    }



    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, HttpSession session) {
        boolean success = usuarioService.login(email, senha);
        if (success) {
            session.setAttribute("email", email); // salva na sessão
            return "Login bem-sucedido!";
        } else {
            return "Credenciais inválidas.";
        }
    }
        @GetMapping("/{codigo}")
	public ResponseEntity<Usuario> getUserByCodigo(@PathVariable int codigo) {
		Usuario usuario = usuarioService.getUsuarioByCodigo(codigo);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    @GetMapping("/eu") //pega a sessao do usuario
    public ResponseEntity<Usuario> getLoggedUser(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null){
            Usuario usuario = usuarioService.findByEmail(email);
            if(usuario != null){
                usuario.setSenha(null);
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout realizado com sucesso.";
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> deleteUsuario(@PathVariable int codigo) {
        boolean deleted = usuarioService.deleteUsuario(codigo);
        if (deleted) {
            return new ResponseEntity<>("Usuário deletado com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

@PutMapping("/{codigo}")
public ResponseEntity<String> atualizarUsuario(@PathVariable int codigo, @RequestBody Usuario usuario) {
    boolean atualizado = usuarioService.atualizarUsuario(codigo, usuario);
    if (atualizado) {
        return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
    }
}

    @GetMapping("/regioes")
    public ResponseEntity<String[]> listarRegioes() {
        String[] regioes = usuarioService.getRegioes();
        return new ResponseEntity<>(regioes, HttpStatus.OK);
    }

    
    @GetMapping
	public List<Usuario> getAllUsuarios() {
		return  usuarioService.getAllUsuarios();
	}
}
