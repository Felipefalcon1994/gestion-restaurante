package com.example.cocina.controller;

import com.example.cocina.dto.OrdenCocinaDTO;
import com.example.cocina.model.OrdenCocina;
import com.example.cocina.service.OrdenCocinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocina")
public class OrdenCocinaController {
    private final OrdenCocinaService ordenService;

    public OrdenCocinaController(OrdenCocinaService ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping
    public ResponseEntity<OrdenCocina> crearOrden(@Valid @RequestBody OrdenCocinaDTO dto) {
        return new ResponseEntity<>(ordenService.crearOrden(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdenCocina>> listarTodas() {
        return new ResponseEntity<>(ordenService.listarTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCocina> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(ordenService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenCocina>> listarPorEstado(@PathVariable String estado) {
        return new ResponseEntity<>(ordenService.listarPorEstado(OrdenCocina.EstadoCocina.valueOf(estado)), HttpStatus.OK);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenCocina> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return new ResponseEntity<>(ordenService.actualizarEstado(id, estado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
