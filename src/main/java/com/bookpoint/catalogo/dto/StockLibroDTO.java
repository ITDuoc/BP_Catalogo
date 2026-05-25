package com.bookpoint.catalogo.dto;

import lombok.Data;

@Data

public class StockLibroDTO {
    private Long id;
    private Long libroId;
    private Long sucursalId;
    private Integer stock;
    private Integer stockMinimo;
}