package com.bookpoint.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookpoint.catalogo.service.CatalogoService;

@RestController
@RequestMapping("api/v1/catalogo")

public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/libros")
    public ResponseEntity<?> getLibros() {

        return ResponseEntity.ok(
                catalogoService
                .obtenerCatalogoLibros());
    }

    @GetMapping("/articulos")
    public ResponseEntity<?> getArticulos() {

        return ResponseEntity.ok(
                catalogoService
                .obtenerCatalogoArticulos());
    }

    @GetMapping("/libros/autor")
    public ResponseEntity<?> getAutor(
            @RequestParam String autor) {

        return ResponseEntity.ok(
                catalogoService
                .filtrarPorAutor(autor));
    }

    @GetMapping("/libros/editorial")
    public ResponseEntity<?> getEditorial(
            @RequestParam String editorial) {

        return ResponseEntity.ok(
                catalogoService
                .filtrarPorEditorial(editorial));
    }

    @GetMapping("/libros/genero")
    public ResponseEntity<?> getGenero(
            @RequestParam String genero) {

        return ResponseEntity.ok(
                catalogoService
                .filtrarPorGenero(genero));
    }

    @GetMapping("/libros/precio")
    public ResponseEntity<?> getPrecio(
            @RequestParam Integer min,
            @RequestParam Integer max) {

        return ResponseEntity.ok(
                catalogoService
                .filtrarPorPrecio(min, max));
    }
}