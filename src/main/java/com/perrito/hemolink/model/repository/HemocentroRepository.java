package com.perrito.hemolink.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Hemocentro;

public interface HemocentroRepository extends JpaRepository<Hemocentro, Integer>{
	List<Hemocentro> findByData(Date data);
	Optional<Hemocentro> findFirstByTipoOrderByDataDesc(String tipo);
}
