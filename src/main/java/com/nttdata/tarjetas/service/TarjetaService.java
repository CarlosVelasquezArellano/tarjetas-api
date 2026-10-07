package com.nttdata.tarjetas.service;

import com.nttdata.tarjetas.model.Tarjeta;
import com.nttdata.tarjetas.model.TipoTarjeta;

import java.util.List;

public interface TarjetaService {

    List<Tarjeta> listarTodas();

    Tarjeta buscarPorNumero(String numero);

    Tarjeta guardar(Tarjeta tarjeta);

    Tarjeta bloquearTarjeta(String numero);

    List<Tarjeta> buscarPorTipo(TipoTarjeta tipo);
}
