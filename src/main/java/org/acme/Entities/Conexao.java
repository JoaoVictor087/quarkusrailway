package org.acme.Entities;




public class Conexao {
    private Estacao origem;
    private Estacao destino;
    private double distancia;

    public Conexao() {
    }

    public Conexao(Estacao origem, double distancia, Estacao destino) {
        this.origem = origem;
        this.distancia = distancia;
        this.destino = destino;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

}
