package com.example.tiendaapi.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    // listar todos los productos por nombre similar
    List<ProductoEntity> findAllByNombreContaining(String nombre);


}
