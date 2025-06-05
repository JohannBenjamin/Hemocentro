package com.perrito.hemolink.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Agendamento;
import com.perrito.hemolink.model.entity.Usuario;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	List<Agendamento> findByUsuario(Usuario usuario);
	List<Agendamento> findByRequisicaoId(int requisicaoId);
	boolean existsByUsuario(Usuario usuario);



}
