package com.nttdata.tarjetas.controller;

import com.nttdata.tarjetas.dto.ApiResponse;
import com.nttdata.tarjetas.model.Tarjeta;
import com.nttdata.tarjetas.model.TipoTarjeta;
import com.nttdata.tarjetas.service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API REST de tarjetas. Cada método atiende un endpoint.
 */
@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    /** GET /api/v1/tarjetas -> lista todas las tarjetas (200). */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Tarjeta>>> listarTarjetas() {

        var tarjeta = tarjetaService.listarTodas();
        var respuesta = new ApiResponse<>(true, "Tarjetas encontradas", tarjeta);
        return ResponseEntity.ok(respuesta);
    }

    /** GET /api/v1/tarjetas/{numero} -> devuelve la tarjeta si existe. */
    @GetMapping("/{numero}")
    public ResponseEntity<ApiResponse<Tarjeta>> buscarPorNumero(@PathVariable String numero) {
        Tarjeta tarjeta = tarjetaService.buscarPorNumero(numero);
        ApiResponse<Tarjeta> respuesta = new ApiResponse<>(true, "Tarjeta encontrada", tarjeta);
        return ResponseEntity.ok(respuesta);
    }

    /** GET /api/v1/tarjetas/tipo/{tipo} -> devuelve las tarjetas del tipo indicado. */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ApiResponse<List<Tarjeta>>> buscarPorTipo(@PathVariable String tipo) {
        TipoTarjeta tipoTarjeta = TipoTarjeta.fromString(tipo);
        List<Tarjeta> tarjetas = tarjetaService.buscarPorTipo(tipoTarjeta);
        ApiResponse<List<Tarjeta>> respuesta = new ApiResponse<>(true, "Tarjetas encontradas por tipo", tarjetas);
        return ResponseEntity.ok(respuesta);
    }

    /** POST /api/v1/tarjetas -> registra una nueva tarjeta (201). */
    @PostMapping
    public ResponseEntity<ApiResponse<Tarjeta>> guardarTarjeta(@RequestBody Tarjeta tarjeta) {
        Tarjeta tarjetaGuardada = tarjetaService.guardar(tarjeta);
        ApiResponse<Tarjeta> respuesta = new ApiResponse<>(true, "Tarjeta registrada", tarjetaGuardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /** PUT /api/v1/tarjetas/{numero}/bloquear -> bloquea la tarjeta si existe (200). */
    @PutMapping("/{numero}/bloquear")
    public ResponseEntity<ApiResponse<Tarjeta>> bloquearTarjeta(@PathVariable String numero) {
        Tarjeta tarjeta = tarjetaService.bloquearTarjeta(numero);
        ApiResponse<Tarjeta> respuesta = new ApiResponse<>(true, "Tarjeta bloqueada", tarjeta);
        return ResponseEntity.ok(respuesta);
    }
}
