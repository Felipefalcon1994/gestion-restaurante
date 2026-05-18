package com.example.inventario.controller;

import jakarta.validation.Valid;

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

import com.example.inventario.dto.InsumoDTO;
import com.example.inventario.model.Insumo;
import com.example.inventario.service.InsumoService;


@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @PostMapping
    public ResponseEntity<Insumo> crearInsumo(@Valid @RequestBody InsumoDTO dto) {
        return new ResponseEntity<>(insumoService.crearInsumo(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Insumo>> obtenerInsumos() {
        return new ResponseEntity<>(insumoService.listarTodos(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Insumo> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(insumoService.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insumo> actualizarInsumo(@PathVariable Long id, @Valid @RequestBody InsumoDTO dto) {
        return new ResponseEntity<>(insumoService.actualizarInsumo(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInsumo(@PathVariable Long id) {
        insumoService.eliminarInsumo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
