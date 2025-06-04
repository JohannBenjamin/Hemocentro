package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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



    @GetMapping("/{codigo}")
	public ResponseEntity<Usuario> getUserByCodigo(@PathVariable int codigo) {
		Usuario usuario = usuarioService.getUsuarioByCodigo(codigo);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @GetMapping("/eu")
    public ResponseEntity<Usuario> getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
            if (usuario != null) {
                usuario.setSenha(null);
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public String logout() {
        
        return "Logout realizado com sucesso.";
    }

    @DeleteMapping("/eu")
   public ResponseEntity<String> deletarUsuario(@AuthenticationPrincipal UserDetails userDetails) {
    if (userDetails == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
    }

    Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }

    boolean deletado = usuarioService.deleteUsuario(usuario.getId());
    if (deletado) {
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao deletar o usuário.");
    }
}
   @PutMapping("/eu")
    public ResponseEntity<String> atualizarUsuario(
        @RequestBody Usuario usuario,
        @AuthenticationPrincipal UserDetails userDetails) {

    if (userDetails == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
    }

    Usuario usuarioLogado = usuarioService.findByEmail(userDetails.getUsername());
    if (usuarioLogado == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
    }

    boolean atualizado = usuarioService.atualizarUsuario(usuarioLogado.getId(), usuario);
    if (atualizado) {
        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
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
