package com.example.inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventario.dto.RecetaDetalleDTO;
import com.example.inventario.model.RecetaDetalle;
import com.example.inventario.service.RecetaDetalleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recetas")
public class RecetaDetalleController {

    private final RecetaDetalleService recetaService;

    public RecetaDetalleController(RecetaDetalleService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping
    public ResponseEntity<RecetaDetalle> crearReceta(@Valid @RequestBody RecetaDetalleDTO dto) {
        return new ResponseEntity<>(recetaService.crearReceta(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecetaDetalle>> obtenerRecetas() {
        return new ResponseEntity<>(recetaService.listarTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDetalle> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(recetaService.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaDetalle> actualizarReceta(@PathVariable Long id, @Valid @RequestBody RecetaDetalleDTO dto) {
        return new ResponseEntity<>(recetaService.actualizarReceta(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
