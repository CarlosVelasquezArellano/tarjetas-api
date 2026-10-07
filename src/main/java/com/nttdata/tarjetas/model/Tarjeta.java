package com.nttdata.tarjetas.model;

/**
 * Representa una tarjeta del banco.
 * Clase simple con atributos privados, constructores, getters y setters.
 */
public class Tarjeta {

    private String numero;      // 16 dígitos, por ejemplo "4111000000000001"
    private String titular;     // nombre del cliente
    private TipoTarjeta tipo;    // tipo de tarjeta: CREDITO o DEBITO
    private double limite;      // límite de la tarjeta
    private EstadoTarjeta estado; // estado actual: ACTIVA o BLOQUEADA

    // Constructor vacío: Spring lo necesita para convertir el JSON en un objeto
    public Tarjeta() {
    }

    public Tarjeta(String numero, String titular, TipoTarjeta tipo, double limite, EstadoTarjeta estado) {
        this.numero = numero;
        this.titular = titular;
        this.tipo = tipo;
        this.limite = limite;
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public EstadoTarjeta getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarjeta estado) {
        this.estado = estado;
    }
}
