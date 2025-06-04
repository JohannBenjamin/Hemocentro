package com.perrito.hemolink.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	List<Agendamento> findByRequisicaoId(int codigo);

}
