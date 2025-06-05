package com.perrito.hemolink.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrito.hemolink.model.entity.Requisicao;
import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.model.repository.RequisicaoRepository;
import com.perrito.hemolink.model.repository.UsuarioRepository;

@Service
public class RequisicaoService {
	@Autowired
    private RequisicaoRepository requisicaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Requisicao criarRequisicao(Usuario usuario, Requisicao requisicao) throws Exception {
    if (usuario.getRequisicao() != null) {
        throw new Exception("Usuário já possui uma requisição");
    }

    requisicao.setUsuario(usuario);
    requisicao.setDataCriacao(LocalDateTime.now());
    usuario.setRequisicao(requisicao);

    usuarioRepository.save(usuario); 

    return requisicao;
}
    public void deletarRequisicaoDoUsuario(Usuario usuario) throws Exception {
    Requisicao requisicao = usuario.getRequisicao();
    if (requisicao == null) {
        throw new Exception("Usuário não possui requisição para deletar.");
    }

    usuario.setRequisicao(null); 
    requisicaoRepo.delete(requisicao);
    usuarioRepository.save(usuario); 
}




    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepo.findAll();
    }
    
    public void deletarRequisicoesInvalidadas() {
        requisicaoRepo.deleteAll();
    }
}
