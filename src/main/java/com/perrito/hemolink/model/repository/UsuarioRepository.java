package com.perrito.hemolink.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);
}
