package org.acme.Entities;

import com.google.common.base.Objects;

public class LinhaStatus {
    private int codigo;
    private String situacao;


    public LinhaStatus() {
    }

    public LinhaStatus(int codigo, String situacao) {
        this.codigo = codigo;
        this.situacao = situacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LinhaStatus that = (LinhaStatus) o;
        return getCodigo() == that.getCodigo() && Objects.equal(getSituacao(), that.getSituacao());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCodigo(), getSituacao());
    }
}
