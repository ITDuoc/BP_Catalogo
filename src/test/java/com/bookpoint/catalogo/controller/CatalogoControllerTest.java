package com.bookpoint.catalogo.controller;

import com.bookpoint.catalogo.service.CatalogoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class CatalogoControllerTest {

    @Mock
    private CatalogoService catalogoService;

    @InjectMocks
    private CatalogoController controller;

    private MockMvc mockMvc;

    @Test
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetLibros() throws Exception {
        when(catalogoService.obtenerCatalogoLibros()).thenReturn(java.util.List.of());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/catalogo/libros"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetArticulos() throws Exception {
        when(catalogoService.obtenerCatalogoArticulos()).thenReturn(java.util.List.of());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/catalogo/articulos"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAutor() throws Exception {
        when(catalogoService.filtrarPorAutor("a")).thenReturn(java.util.List.of());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/catalogo/libros/autor")
                        .param("autor", "a"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEditorial() throws Exception {
        when(catalogoService.filtrarPorEditorial("x")).thenReturn(java.util.List.of());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/catalogo/libros/editorial")
                        .param("editorial", "x"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetGenero() throws Exception {
        when(catalogoService.filtrarPorGenero("g")).thenReturn(java.util.List.of());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/catalogo/libros/genero")
                        .param("genero", "g"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPrecio() throws Exception {
        when(catalogoService.filtrarPorPrecio(1, 10)).thenReturn(java.util.List.of());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/v1/catalogo/libros/precio")
                        .param("min", "1")
                        .param("max", "10"))
                .andExpect(status().isOk());
    }
}