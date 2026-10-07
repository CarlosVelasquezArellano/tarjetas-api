package com.nttdata.tarjetas.service;

import com.nttdata.tarjetas.exception.ConflictoException;
import com.nttdata.tarjetas.exception.NoEncontradoException;
import com.nttdata.tarjetas.exception.ValidacionException;
import com.nttdata.tarjetas.model.EstadoTarjeta;
import com.nttdata.tarjetas.model.Tarjeta;
import com.nttdata.tarjetas.model.TipoTarjeta;
import com.nttdata.tarjetas.repository.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    public List<Tarjeta> listarTodas() {
        // Devuelve todas las tarjetas que ya están en memoria.
        return tarjetaRepository.listarTodas();
    }

    @Override
    public Tarjeta buscarPorNumero(String numero) {
        // Busca la tarjeta en el repositorio.
        Tarjeta tarjeta = tarjetaRepository.buscarPorNumero(numero);

        // Si no existe, se lanza una excepción controlada.
        if (tarjeta == null) {
            throw new NoEncontradoException("Tarjeta no encontrada");
        }

        return tarjeta;
    }

    @Override
    public Tarjeta guardar(Tarjeta tarjeta) {
        // R1: el número debe tener 16 caracteres y el titular es obligatorio.
        if (tarjeta == null || tarjeta.getNumero() == null || tarjeta.getNumero().length() != 16 || tarjeta.getTitular() == null || tarjeta.getTitular().trim().isEmpty()) {
            throw new ValidacionException("El número debe tener 16 caracteres y el titular es obligatorio");
        }

        // R2: el tipo solo puede ser CREDITO o DEBITO.
        if (tarjeta.getTipo() != TipoTarjeta.CREDITO && tarjeta.getTipo() != TipoTarjeta.DEBITO) {
            throw new ValidacionException("El tipo solo puede ser CREDITO o DEBITO");
        }

        // R3: una tarjeta CREDITO debe tener límite mayor a 0.
        if (tarjeta.getTipo() == TipoTarjeta.CREDITO && tarjeta.getLimite() <= 0) {
            throw new ValidacionException("Una tarjeta CREDITO debe tener un límite mayor a 0");
        }

        // R4: si el número ya existe, se devuelve conflicto.
        Tarjeta existe = tarjetaRepository.buscarPorNumero(tarjeta.getNumero());
        if (existe != null) {
            throw new ConflictoException("La tarjeta ya existe");
        }

        // R5: la tarjeta se guarda con estado ACTIVA y se responde con 201.
        tarjeta.setEstado(EstadoTarjeta.ACTIVA);
        tarjetaRepository.guardar(tarjeta);
        return tarjeta;
    }

    @Override
    public Tarjeta bloquearTarjeta(String numero) {
        // Busca la tarjeta por su número usando el método ya existente.
        Tarjeta tarjeta = buscarPorNumero(numero);

        // Como el PUT es idempotente, si ya estaba bloqueada, se mantiene bloqueada.
        tarjeta.setEstado(EstadoTarjeta.BLOQUEADA);
        return tarjeta;
    }

    @Override
    public List<Tarjeta> buscarPorTipo(TipoTarjeta tipo) {
        // Validamos que el tipo enviado sea realmente uno de los permitidos.
        if (tipo == null) {
            throw new ValidacionException("El tipo no es válido");
        }

        // Recorremos la lista para filtrar solo las tarjetas con ese tipo.
        List<Tarjeta> resultado = new ArrayList<>();
        List<Tarjeta> tarjetas = tarjetaRepository.listarTodas();
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            if (tipo.equals(tarjeta.getTipo())) {
                resultado.add(tarjeta);
            }
        }

        return resultado;
    }
}
