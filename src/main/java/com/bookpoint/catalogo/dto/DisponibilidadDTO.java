package com.bookpoint.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DisponibilidadDTO {
    private Long sucursalId;
    private Integer stock;
}