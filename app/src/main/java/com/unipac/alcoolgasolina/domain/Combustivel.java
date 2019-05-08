package com.unipac.alcoolgasolina.domain;

import java.io.Serializable;

public class Combustivel implements Serializable {
    private Double precoGasolina;

    public Double getPrecoGasolina() {
        return precoGasolina;
    }

    public void setPrecoGasolina(Double precoGasolina) {
        this.precoGasolina = precoGasolina;
    }

    public Double getPrecoAlcool() {
        return precoAlcool;
    }

    public void setPrecoAlcool(Double precoAlcool) {
        this.precoAlcool = precoAlcool;
    }

    private Double precoAlcool;


}
