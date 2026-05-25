package com.bookpoint.catalogo.dto;

import lombok.Data;

@Data

public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private String genero;
    private Integer precio;
    private Boolean mostrarCatalogo;
}