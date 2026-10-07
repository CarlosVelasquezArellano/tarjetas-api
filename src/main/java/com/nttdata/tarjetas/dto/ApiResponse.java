package com.nttdata.tarjetas.dto;

public class ApiResponse<T> {

    private boolean estado;
    private String mensaje;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean estado, String mensaje, T data) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.data = data;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

