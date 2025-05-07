package org.acme.Entities;

import jakarta.enterprise.context.ApplicationScoped;


public class NoBusca implements Comparable<NoBusca>{
    private Estacao estacao;
    private double custoG; //o q foi gasto para chegar até a estação atual
    private double custoH; //tempo estimado que falta para chegar na estaçãod estino
    private NoBusca pai;
    //f(n) = g(n) + h(n):
    //f(n) = custo total estimado para chegar até o destino


    public NoBusca() {
    }

    public NoBusca(Estacao estacao, double custoG, double custoH, NoBusca pai) {
        this.estacao = estacao;
        this.custoG = custoG;
        this.custoH = custoH;
        this.pai = pai;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    public double getCustoG() {
        return custoG;
    }

    public void setCustoG(double custoG) {
        this.custoG = custoG;
    }

    public double getCustoH() {
        return custoH;
    }

    public void setCustoH(double custoH) {
        this.custoH = custoH;
    }

    public NoBusca getPai() {
        return pai;
    }

    public void setPai(NoBusca pai) {
        this.pai = pai;
    }

    public double custoF() {
        return custoG + custoH;
    }

    @Override
    public int compareTo(NoBusca outro) {
        return Double.compare(this.custoF(), outro.custoF());
    }

}
