package com.bookpoint.catalogo.service;

import com.bookpoint.catalogo.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogoServiceTest {

    @InjectMocks
    private CatalogoService service;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception {
        java.lang.reflect.Field field = CatalogoService.class.getDeclaredField("restTemplate");
        field.setAccessible(true);
        field.set(service, restTemplate);
    }

    @Test
    void testObtenerLibrosBase() {
        LibroDTO[] libros = new LibroDTO[]{new LibroDTO()};
        when(restTemplate.getForEntity(contains("/libros"), eq(LibroDTO[].class)))
                .thenReturn(ResponseEntity.ok(libros));

        List<LibroDTO> result = service.obtenerLibrosBase();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerArticulosBase() {
        ArticuloDTO[] data = new ArticuloDTO[]{new ArticuloDTO()};
        when(restTemplate.getForEntity(contains("/articulos"), eq(ArticuloDTO[].class)))
                .thenReturn(ResponseEntity.ok(data));

        List<ArticuloDTO> result = service.obtenerArticulosBase();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerStockLibros() {
        StockLibroDTO[] data = new StockLibroDTO[]{new StockLibroDTO()};
        when(restTemplate.getForEntity(contains("stock-libros"), eq(StockLibroDTO[].class)))
                .thenReturn(ResponseEntity.ok(data));

        List<StockLibroDTO> result = service.obtenerStockLibros();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerStockArticulos() {
        StockArticuloDTO[] data = new StockArticuloDTO[]{new StockArticuloDTO()};
        when(restTemplate.getForEntity(contains("stock-articulos"), eq(StockArticuloDTO[].class)))
                .thenReturn(ResponseEntity.ok(data));

        List<StockArticuloDTO> result = service.obtenerStockArticulos();

        assertEquals(1, result.size());
    }

    @Test
    void testCatalogoLibros() {
        LibroDTO libro = new LibroDTO();
        libro.setId(1L);
        libro.setMostrarCatalogo(true);

        StockLibroDTO stock = new StockLibroDTO();
        stock.setLibroId(1L);
        stock.setSucursalId(1L);
        stock.setStock(5);

        when(restTemplate.getForEntity(contains("/libros"), eq(LibroDTO[].class)))
                .thenReturn(ResponseEntity.ok(new LibroDTO[]{libro}));

        when(restTemplate.getForEntity(contains("stock-libros"), eq(StockLibroDTO[].class)))
                .thenReturn(ResponseEntity.ok(new StockLibroDTO[]{stock}));

        List<LibroCatalogoDTO> result = service.obtenerCatalogoLibros();

        assertEquals(1, result.size());
    }

    @Test
    void testCatalogoLibrosFiltradoMostrarFalse() {
        LibroDTO libro = new LibroDTO();
        libro.setId(1L);
        libro.setMostrarCatalogo(false);

        when(restTemplate.getForEntity(contains("/libros"), eq(LibroDTO[].class)))
                .thenReturn(ResponseEntity.ok(new LibroDTO[]{libro}));

        when(restTemplate.getForEntity(contains("stock-libros"), eq(StockLibroDTO[].class)))
                .thenReturn(ResponseEntity.ok(new StockLibroDTO[]{}));

        List<LibroCatalogoDTO> result = service.obtenerCatalogoLibros();

        assertEquals(0, result.size());
    }

    @Test
    void testCatalogoArticulos() {
        ArticuloDTO articulo = new ArticuloDTO();
        articulo.setId(1L);
        articulo.setMostrarCatalogo(true);

        StockArticuloDTO stock = new StockArticuloDTO();
        stock.setArticuloId(1L);
        stock.setSucursalId(1L);
        stock.setStock(3);

        when(restTemplate.getForEntity(contains("/articulos"), eq(ArticuloDTO[].class)))
                .thenReturn(ResponseEntity.ok(new ArticuloDTO[]{articulo}));

        when(restTemplate.getForEntity(contains("stock-articulos"), eq(StockArticuloDTO[].class)))
                .thenReturn(ResponseEntity.ok(new StockArticuloDTO[]{stock}));

        List<ArticuloCatalogoDTO> result = service.obtenerCatalogoArticulos();

        assertEquals(1, result.size());
    }

    @Test
    void testFiltrosAutor() {
        CatalogoService spy = spy(service);

        LibroCatalogoDTO libro = new LibroCatalogoDTO();
        libro.setAutor("Juan");

        doReturn(List.of(libro)).when(spy).obtenerCatalogoLibros();

        List<LibroCatalogoDTO> result = spy.filtrarPorAutor("Juan");

        assertEquals(1, result.size());
    }

    @Test
    void testFiltrosGenero() {
        CatalogoService spy = spy(service);

        LibroCatalogoDTO libro = new LibroCatalogoDTO();
        libro.setGenero("Drama");

        doReturn(List.of(libro)).when(spy).obtenerCatalogoLibros();

        List<LibroCatalogoDTO> result = spy.filtrarPorGenero("Drama");

        assertEquals(1, result.size());
    }

    @Test
    void testFiltrosEditorial() {
        CatalogoService spy = spy(service);

        LibroCatalogoDTO libro = new LibroCatalogoDTO();
        libro.setEditorial("Planeta");

        doReturn(List.of(libro)).when(spy).obtenerCatalogoLibros();

        List<LibroCatalogoDTO> result = spy.filtrarPorEditorial("Planeta");

        assertEquals(1, result.size());
    }

    @Test
    void testFiltrosPrecio() {
        CatalogoService spy = spy(service);

        LibroCatalogoDTO libro = new LibroCatalogoDTO();
        libro.setPrecio(100);

        doReturn(List.of(libro)).when(spy).obtenerCatalogoLibros();

        List<LibroCatalogoDTO> result = spy.filtrarPorPrecio(50, 150);

        assertEquals(1, result.size());
    }
}