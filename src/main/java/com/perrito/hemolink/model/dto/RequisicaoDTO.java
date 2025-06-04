package com.perrito.hemolink.model.dto;

import com.perrito.hemolink.model.entity.Requisicao;

public class RequisicaoDTO {
    private String tipo;
    private String local;
    private String descricao;

      public RequisicaoDTO() {
    }
    public RequisicaoDTO(Requisicao requisicao) {
    this.tipo = requisicao.getTipo();
    this.local = requisicao.getLocal();
    this.descricao = requisicao.getDescricao();
}

    // Getters e setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
