package com.example.tiendaapi.productotest;

import com.example.tiendaapi.producto.ProductoEntity;
import com.example.tiendaapi.producto.ProductoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @AfterEach
    void tearDown() {

    }

    @Test
    public void encontrarProductoSegunNombre(String nombre) {
        //given
        nombre = "silla";
        ProductoEntity productoEntity = new ProductoEntity(1, "silla", 20.20, 15 );
        ProductoEntity productoEntityDos = new ProductoEntity(2, "sillon", 236.20, 15 );
        productoRepository.save(productoEntity);
        productoRepository.save(productoEntityDos);

        //when

        List<ProductoEntity> productosEncontrados = productoRepository.findAllByNombreContaining(nombre);

        //then
        assertThat(productosEncontrados).isNotEmpty();
    }
}
