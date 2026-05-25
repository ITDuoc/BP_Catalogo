package com.bookpoint.catalogo.dto;

import lombok.Data;

@Data

public class StockArticuloDTO {
    private Long id;
    private Long articuloId;
    private Long sucursalId;
    private Integer stock;
    private Integer stockMinimo;
}