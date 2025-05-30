package com.perrito.hemolink.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.perrito.hemolink.model.entity.Requisicao;
import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.model.repository.RequisicaoRepository;
import com.perrito.hemolink.model.repository.UsuarioRepository;

public class RequisicaoService {
	@Autowired
    private RequisicaoRepository requisicaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Requisicao criarRequisicao(int usuarioId, Requisicao requisicao) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        if (usuario.getRequisicao() != null) {
            throw new Exception("Usuário já possui uma requisição");
        }

        requisicao.setUsuario(usuario);
        requisicao.setDataCriacao(LocalDateTime.now());
        usuario.setRequisicao(requisicao);

        usuarioRepository.save(usuario); // cascade salva a requisição

        return requisicao;
    }

    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepo.findAll();
    }
}
