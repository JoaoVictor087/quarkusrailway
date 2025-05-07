package org.acme.Entities;

import com.google.common.base.Objects;


public class Estacao {
    private String nome;
    private double x;
    private double y;


    public Estacao() {
    }

    public Estacao(String nome, double x, double y) {
        this.nome = nome;
        this.x = x;
        this.y = y;

    }

    public Estacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estacao estacao = (Estacao) o;
        return Objects.equal(getNome(), estacao.getNome());
    }


}
