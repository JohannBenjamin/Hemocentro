package com.perrito.hemolink.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Requisicao;

import jakarta.transaction.Transactional;

public interface RequisicaoRepository  extends JpaRepository<Requisicao, Integer> {

	@Transactional
    void deleteByTipoIsNullOrTipo(String tipo);
}
