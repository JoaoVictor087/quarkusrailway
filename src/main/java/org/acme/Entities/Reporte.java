package org.acme.Entities;

import java.util.UUID;

public class Reporte {
    private UUID id;
    private String nome;
    private String local;
    private String descricao;

    public Reporte() {
    }

    public Reporte(UUID id, String descricao, String local, String nome) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
