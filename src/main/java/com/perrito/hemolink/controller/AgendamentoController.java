package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.perrito.hemolink.model.entity.Agendamento;
import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.service.AgendamentoService;
import com.perrito.hemolink.service.UsuarioService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarAgendamento(
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestBody Agendamento agendamento) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        agendamento.setUsuario(usuario); 

        Agendamento agendamentoSalvo = agendamentoService.criarAgendamento(agendamento);
        return new ResponseEntity<>(agendamentoSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listarMeusAgendamentos(@AuthenticationPrincipal UserDetails userDetails) {
    if (userDetails == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
    }

    Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
    }

    List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorUsuario(usuario);
    return ResponseEntity.ok(agendamentos);
}

}
