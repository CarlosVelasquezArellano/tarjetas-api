package com.nttdata.tarjetas.repository;

import com.nttdata.tarjetas.model.EstadoTarjeta;
import com.nttdata.tarjetas.model.Tarjeta;
import com.nttdata.tarjetas.model.TipoTarjeta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda las tarjetas en una lista en memoria (no hay base de datos).
 * Al reiniciar la aplicación, los datos vuelven a su estado inicial.
 */
@Repository
public class TarjetaRepository {

    private final List<Tarjeta> tarjetas = new ArrayList<>();

    public TarjetaRepository() {
        tarjetas.add(new Tarjeta("4111000000000001", "Ana Torres", TipoTarjeta.CREDITO, 5000.00, EstadoTarjeta.ACTIVA));
        tarjetas.add(new Tarjeta("4111000000000002", "Luis Ramos", TipoTarjeta.DEBITO, 0.00, EstadoTarjeta.ACTIVA));
        tarjetas.add(new Tarjeta("4111000000000003", "María Quispe", TipoTarjeta.CREDITO, 12000.00, EstadoTarjeta.BLOQUEADA));
        tarjetas.add(new Tarjeta("4111000000000004", "Carlos Díaz", TipoTarjeta.CREDITO, 3000.00, EstadoTarjeta.ACTIVA));
        tarjetas.add(new Tarjeta("4111000000000005", "Rosa Huamán", TipoTarjeta.DEBITO, 0.00, EstadoTarjeta.ACTIVA));
    }

    /** Devuelve todas las tarjetas. */
    public List<Tarjeta> listarTodas() {
        return tarjetas;
    }

    /** Busca una tarjeta por su número. */
    public Tarjeta buscarPorNumero(String numero) {
        // Recorremos la lista para comparar cada número con el buscado.
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            if (tarjeta.getNumero().equals(numero)) {
                return tarjeta;
            }
        }
        // Si no se encuentra ninguna coincidencia, devolvemos null.
        return null;
    }

    /** Guarda una tarjeta nueva en la lista. */
    public void guardar(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }
}
