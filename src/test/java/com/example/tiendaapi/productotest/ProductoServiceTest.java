package com.example.tiendaapi.productotest;

import com.example.tiendaapi.producto.ProductoRepository;
import com.example.tiendaapi.producto.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepositoryTest;
    private ProductoService productoServiceTest;


    @BeforeEach
    void setUp(){ productoServiceTest = new ProductoService(productoRepositoryTest);}

    @Test
    void canGetProductos() {
        // given
        // productoService y productoRepository

        //when
        productoServiceTest.getProductos();

        //then
        verify(productoRepositoryTest).findAll();
    }
}
