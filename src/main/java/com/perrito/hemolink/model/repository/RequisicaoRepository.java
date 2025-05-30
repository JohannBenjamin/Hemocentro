package com.perrito.hemolink.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Requisicao;

public interface RequisicaoRepository  extends JpaRepository<Requisicao, Integer> {

}
