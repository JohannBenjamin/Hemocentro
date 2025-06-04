package com.perrito.hemolink.model.dto;

public class RequisicaoDTO {
    private String tipo;
    private String local;
    private String descricao;

    // Getters e setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
