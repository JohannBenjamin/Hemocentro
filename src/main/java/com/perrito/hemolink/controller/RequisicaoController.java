package com.perrito.hemolink.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.dto.RequisicaoDTO;
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
        @RequestBody RequisicaoDTO dto) {
    try {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        Requisicao requisicao = new Requisicao();
        requisicao.setTipo(dto.getTipo());
        requisicao.setDescricao(dto.getDescricao());
        requisicao.setDataCriacao(LocalDateTime.now());
        requisicao.setUsuario(usuario);

        Requisicao requisicaoSalva = requisicaoService.criarRequisicao(usuario, requisicao);

        RequisicaoDTO responseDTO = new RequisicaoDTO();
        responseDTO.setTipo(requisicaoSalva.getTipo());
        responseDTO.setDescricao(requisicaoSalva.getDescricao());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @DeleteMapping("/usuarios/eu")
    public ResponseEntity<?> deletarRequisicaoDoUsuario(
        @AuthenticationPrincipal UserDetails userDetails) {
    try {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        requisicaoService.deletarRequisicaoDoUsuario(usuario);
        return ResponseEntity.ok("Requisição deletada com sucesso.");

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @GetMapping("/feed")
    public ResponseEntity<List<RequisicaoDTO>> listarRequisicoes() {
        List<Requisicao> requisicoes = requisicaoService.listarRequisicoes();

        List<RequisicaoDTO> dtos = requisicoes.stream().map(requisicao -> {
            RequisicaoDTO dto = new RequisicaoDTO();
            dto.setTipo(requisicao.getTipo());
            dto.setDescricao(requisicao.getDescricao());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

}
