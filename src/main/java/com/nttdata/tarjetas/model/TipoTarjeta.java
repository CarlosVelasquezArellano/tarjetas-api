package com.nttdata.tarjetas.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoTarjeta {
    CREDITO,
    DEBITO;

    @JsonCreator
    public static TipoTarjeta fromString(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El tipo solo puede ser CREDITO o DEBITO");
        }

        String texto = valor.trim().toUpperCase();
        if ("CREDITO".equals(texto)) {
            return CREDITO;
        }
        if ("DEBITO".equals(texto)) {
            return DEBITO;
        }
        throw new IllegalArgumentException("El tipo solo puede ser CREDITO o DEBITO");
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
