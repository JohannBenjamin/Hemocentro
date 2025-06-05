package com.perrito.hemolink.model.dto;

import com.perrito.hemolink.model.entity.Requisicao;

public class RequisicaoDTO {
    private String tipo;
    private String descricao;

      public RequisicaoDTO() {
    }
    public RequisicaoDTO(Requisicao requisicao) {
    this.tipo = requisicao.getTipo();
    this.descricao = requisicao.getDescricao();
}

    // Getters e setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
