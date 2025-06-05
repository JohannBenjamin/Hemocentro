package com.perrito.hemolink.model.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String verificacao;
		private Date data;
		
		@ManyToOne
		@JsonBackReference
	    	private Requisicao requisicao;
		
		@ManyToOne
	    	private Usuario usuario;
		
		public Agendamento() {
			
		}

		public Agendamento(int id, String verificacao, Date data, Requisicao requisicao, Usuario usuario) {
			this.id = id;
			this.verificacao = verificacao;
			this.data = data;
			this.requisicao = requisicao;
			this.usuario = usuario;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getVerificacao() {
			return verificacao;
		}

		public void setVerificacao(String verificacao) {
			this.verificacao = verificacao;
		}

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public Requisicao getRequisicao() {
			return requisicao;
		}

		public void setRequisicao(Requisicao requisicao) {
			this.requisicao = requisicao;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		
}
