package com.bookpoint.catalogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bookpoint.catalogo.service.CatalogoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogoService catalogoService;

    @Test
    void getLibros() throws Exception {
        when(catalogoService.obtenerCatalogoLibros())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/catalogo/libros"))
                .andExpect(status().isOk());
    }

    @Test
    void getArticulos() throws Exception {
        when(catalogoService.obtenerCatalogoArticulos())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/catalogo/articulos"))
                .andExpect(status().isOk());
    }

    @Test
    void getAutor() throws Exception {
        when(catalogoService.filtrarPorAutor("Rowling"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/catalogo/libros/autor")
                        .param("autor", "Rowling"))
                .andExpect(status().isOk());
    }

    @Test
    void getEditorial() throws Exception {
        when(catalogoService.filtrarPorEditorial("Planeta"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/catalogo/libros/editorial")
                        .param("editorial", "Planeta"))
                .andExpect(status().isOk());
    }

    @Test
    void getGenero() throws Exception {
        when(catalogoService.filtrarPorGenero("Drama"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/catalogo/libros/genero")
                        .param("genero", "Drama"))
                .andExpect(status().isOk());
    }

    @Test
    void getPrecio() throws Exception {
        when(catalogoService.filtrarPorPrecio(10, 50))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/catalogo/libros/precio")
                        .param("min", "10")
                        .param("max", "50"))
                .andExpect(status().isOk());
    }
}