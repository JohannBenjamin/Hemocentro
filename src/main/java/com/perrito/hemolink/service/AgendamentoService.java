package com.perrito.hemolink.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrito.hemolink.model.entity.Agendamento;
import com.perrito.hemolink.model.entity.Requisicao;
import com.perrito.hemolink.model.entity.Usuario;
import com.perrito.hemolink.model.repository.AgendamentoRepository;
import com.perrito.hemolink.model.repository.RequisicaoRepository;
import com.perrito.hemolink.model.repository.UsuarioRepository;

@Service
public class AgendamentoService {
	@Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RequisicaoRepository requisicaoRepository;

    // Criar Agendamento
    public Agendamento criarAgendamento(Agendamento agendamentoCriado) {
        Usuario usuario = usuarioRepository.findById(agendamentoCriado.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Requisicao requisicao = requisicaoRepository.findById(agendamentoCriado.getRequisicao().getId())
                .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));

        // Verifique se o usuário não está agendando para si mesmo (opcional)
        if (usuario.equals(requisicao.getUsuario())) {
            throw new RuntimeException("Não é permitido agendar para a própria requisição");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setUsuario(usuario);
        agendamento.setRequisicao(requisicao);
        agendamento.setData(agendamento.getData());

        agendamento = agendamentoRepository.save(agendamento);

        return agendamento;
    }

    // Listar Agendamentos por Requisição
    public List<Agendamento> listarAgendamentosPorRequisicao(int codigo) {
        Requisicao requisicao = requisicaoRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));

        List<Agendamento> agendamentos = agendamentoRepository.findByRequisicaoId(codigo);

        return agendamentos;
    }
     public List<Agendamento> listarAgendamentosPorUsuario(Usuario usuario) {
        return agendamentoRepository.findByUsuario(usuario);
    }

    public Agendamento buscarPorId(int id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        return agendamento.orElse(null);
    }

    public void deletarAgendamento(int id) {
        agendamentoRepository.deleteById(id);
    }

    public boolean existeAgendamentoPorUsuario(Usuario usuario) {
    return agendamentoRepository.existsByUsuario(usuario);
}


}
