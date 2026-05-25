package com.bookpoint.catalogo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookpoint.catalogo.dto.*;

@Service

public class CatalogoService {

    private final RestTemplate restTemplate =
            new RestTemplate();

    private final String INVENTARIO_URL =
            "http://localhost:8083/api/v1";

    public List<LibroDTO> obtenerLibrosBase() {

        ResponseEntity<LibroDTO[]> response =
                restTemplate.getForEntity(
                        INVENTARIO_URL + "/libros",
                        LibroDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public List<ArticuloDTO> obtenerArticulosBase() {

        ResponseEntity<ArticuloDTO[]> response =
                restTemplate.getForEntity(
                        INVENTARIO_URL + "/articulos",
                        ArticuloDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public List<StockLibroDTO> obtenerStockLibros() {

        ResponseEntity<StockLibroDTO[]> response =
                restTemplate.getForEntity(
                        INVENTARIO_URL + "/stock-libros",
                        StockLibroDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public List<StockArticuloDTO> obtenerStockArticulos() {

        ResponseEntity<StockArticuloDTO[]> response =
                restTemplate.getForEntity(
                        INVENTARIO_URL + "/stock-articulos",
                        StockArticuloDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public List<LibroCatalogoDTO> obtenerCatalogoLibros() {

        List<LibroDTO> libros = obtenerLibrosBase();

        List<StockLibroDTO> stocks =
                obtenerStockLibros();

        List<LibroCatalogoDTO> catalogo =
                new ArrayList<>();

        for (LibroDTO libro : libros) {

            if (!libro.getMostrarCatalogo()) {
                continue;
            }

            LibroCatalogoDTO dto =
                    new LibroCatalogoDTO();

            dto.setId(libro.getId());
            dto.setTitulo(libro.getTitulo());
            dto.setAutor(libro.getAutor());
            dto.setEditorial(libro.getEditorial());
            dto.setGenero(libro.getGenero());
            dto.setPrecio(libro.getPrecio());

            List<DisponibilidadDTO> disponibilidad =
                    stocks.stream()
                    .filter(stock ->
                            stock.getLibroId()
                            .equals(libro.getId()))
                    .map(stock ->
                            new DisponibilidadDTO(
                                    stock.getSucursalId(),
                                    stock.getStock()))
                    .collect(Collectors.toList());

            dto.setDisponibilidad(disponibilidad);

            catalogo.add(dto);
        }

        return catalogo;
    }

    public List<ArticuloCatalogoDTO>
    obtenerCatalogoArticulos() {

        List<ArticuloDTO> articulos =
                obtenerArticulosBase();

        List<StockArticuloDTO> stocks =
                obtenerStockArticulos();

        List<ArticuloCatalogoDTO> catalogo =
                new ArrayList<>();

        for (ArticuloDTO articulo : articulos) {

            if (!articulo.getMostrarCatalogo()) {
                continue;
            }

            ArticuloCatalogoDTO dto =
                    new ArticuloCatalogoDTO();

            dto.setId(articulo.getId());
            dto.setNombre(articulo.getNombre());
            dto.setMarca(articulo.getMarca());
            dto.setPrecio(articulo.getPrecio());

            List<DisponibilidadDTO> disponibilidad =
                    stocks.stream()
                    .filter(stock ->
                            stock.getArticuloId()
                            .equals(articulo.getId()))
                    .map(stock ->
                            new DisponibilidadDTO(
                                    stock.getSucursalId(),
                                    stock.getStock()))
                    .collect(Collectors.toList());

            dto.setDisponibilidad(disponibilidad);

            catalogo.add(dto);
        }

        return catalogo;
    }

    public List<LibroCatalogoDTO>
    filtrarPorAutor(String autor) {

        return obtenerCatalogoLibros()
                .stream()
                .filter(libro ->
                        libro.getAutor()
                        .equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<LibroCatalogoDTO>
    filtrarPorGenero(String genero) {

        return obtenerCatalogoLibros()
                .stream()
                .filter(libro ->
                        libro.getGenero()
                        .equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    public List<LibroCatalogoDTO>
    filtrarPorEditorial(String editorial) {

        return obtenerCatalogoLibros()
                .stream()
                .filter(libro ->
                        libro.getEditorial()
                        .equalsIgnoreCase(editorial))
                .collect(Collectors.toList());
    }

    public List<LibroCatalogoDTO>
    filtrarPorPrecio(
            Integer min,
            Integer max) {

        return obtenerCatalogoLibros()
                .stream()
                .filter(libro ->
                        libro.getPrecio() >= min
                        &&
                        libro.getPrecio() <= max)
                .collect(Collectors.toList());
    }
}