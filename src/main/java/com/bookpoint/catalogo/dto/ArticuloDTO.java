package com.bookpoint.catalogo.dto;

import lombok.Data;

@Data

public class ArticuloDTO {

    private Long id;
    private String nombre;
    private String marca;
    private Integer precio;
    private Boolean mostrarCatalogo;
}