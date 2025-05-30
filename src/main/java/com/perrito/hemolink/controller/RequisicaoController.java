package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.entity.Requisicao;
import com.perrito.hemolink.service.RequisicaoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/requisicoes")
public class RequisicaoController {
	@Autowired
    private RequisicaoService requisicaoService;

    @PostMapping("/usuarios/{usuarioId}")
    public Requisicao criarRequisicao(
            @PathVariable int usuarioId,
            @RequestBody Requisicao requisicao) throws Exception {
        return requisicaoService.criarRequisicao(usuarioId, requisicao);
    }

    @GetMapping("/feed")
    public List<Requisicao> listarRequisicoes() {
        return requisicaoService.listarRequisicoes();
    }
}
