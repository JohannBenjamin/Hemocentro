package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.entity.Requisicao;
import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.service.RequisicaoService;
import com.perrito.hemolink.service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/requisicoes")
public class RequisicaoController {
    @Autowired
    private RequisicaoService requisicaoService;
    @Autowired
	private UsuarioService usuarioService;

    @PostMapping("/usuarios/eu")
    public ResponseEntity<?> criarRequisicao(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Requisicao requisicao) {
        try {
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
            }

            Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
            }

            Requisicao requisicaoSalva = requisicaoService.criarRequisicao(usuario, requisicao);
            return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoSalva);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/feed")
    public List<Requisicao> listarRequisicoes() {
        return requisicaoService.listarRequisicoes();
    }
}
