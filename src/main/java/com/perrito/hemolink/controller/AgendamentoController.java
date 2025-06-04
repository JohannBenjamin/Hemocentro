package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.entity.Agendamento;
import com.perrito.hemolink.service.AgendamentoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
    private AgendamentoService agendamentoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoResponse = agendamentoService.criarAgendamento(agendamento);
        return new ResponseEntity<>(agendamentoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/requisicao/{requisicaoId}")
    public ResponseEntity<List<Agendamento>> listarAgendamentosPorRequisicao(@PathVariable int requisicaoId) {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorRequisicao(requisicaoId);
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodosAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }
}
