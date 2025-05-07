package org.acme.Entities;

import jakarta.enterprise.context.ApplicationScoped;


public class Usuario {
    private Estacao origem;
    private Estacao destino;

    public Usuario() {
    }

    public Usuario(Estacao destino, Estacao origem) {
        this.destino = destino;
        this.origem = origem;
    }

    public Estacao getOrigem() {
        return origem;
    }

    public void setOrigem(Estacao origem) {
        this.origem = origem;
    }

    public Estacao getDestino() {
        return destino;
    }

    public void setDestino(Estacao destino) {
        this.destino = destino;
    }

}

