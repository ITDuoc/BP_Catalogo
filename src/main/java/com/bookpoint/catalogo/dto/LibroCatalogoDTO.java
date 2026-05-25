package com.bookpoint.catalogo.dto;

import java.util.List;

import lombok.Data;

@Data

public class LibroCatalogoDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private String genero;
    private Integer precio;

    private List<DisponibilidadDTO> disponibilidad;
}