package com.unipac.alcoolgasolina.domain;

import java.io.Serializable;

public class Combustivel implements Serializable {
    private Float precoGasolina;

    public Float getPrecoGasolina() {
        return precoGasolina;
    }

    public void setPrecoGasolina(Float precoGasolina) {
        this.precoGasolina = precoGasolina;
    }

    public Float getPrecoAlcool() {
        return precoAlcool;
    }

    public void setPrecoAlcool(Float precoAlcool) {
        this.precoAlcool = precoAlcool;
    }

    private Float precoAlcool;


}
