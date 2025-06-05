package com.perrito.hemolink.model.dto;

import com.perrito.hemolink.model.entity.Requisicao;

public class RequisicaoDTO {
    private Integer id;
    private String nome;
    private String tipo;
    private String descricao;

      public RequisicaoDTO() {
    }
    public RequisicaoDTO(Requisicao requisicao) {
    this.id = requisicao.getId();
    this.nome = requisicao.getUsuario() != null ? requisicao.getUsuario().getNome() : null;
    this.tipo = requisicao.getTipo();
    this.descricao = requisicao.getDescricao();
}

    // Getters e setters


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
