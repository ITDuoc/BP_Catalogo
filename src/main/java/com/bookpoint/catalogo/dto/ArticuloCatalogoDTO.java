package com.bookpoint.catalogo.dto;

import java.util.List;

import lombok.Data;

@Data

public class ArticuloCatalogoDTO {
    private Long id;
    private String nombre;
    private String marca;
    private Integer precio;

    private List<DisponibilidadDTO> disponibilidad;
}