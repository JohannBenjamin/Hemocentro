package com.perrito.hemolink.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hospital {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String hemocentro;
	
	public Hospital() {
		
	}
	
	public Hospital(int id, String nome, String hemocentro) {
		this.id = id;
		this.nome = nome;
		this.hemocentro = hemocentro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHemocentro() {
		return hemocentro;
	}

	public void setHemocentro(String hemocentro) {
		this.hemocentro = hemocentro;
	}
	
	
}
