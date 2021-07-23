package com.example.tiendaapi.producto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /** Listar todos los productos **/
    public List<ProductoEntity> getProductos(){
        return productoRepository.findAll();
    }

    /** Listar todos los productos **/
    public ProductoEntity buscarPorID(Integer id){
        ProductoEntity productoEntity = productoRepository.findById(id).orElse(null);
        return productoEntity;
    }

    /** Listar por nombre **/
    public List<ProductoEntity> buscarPorNombre(String nombre){
        return  productoRepository.findAllByNombreContaining(nombre);
    }

    /** Guardar producto **/
    public void guardarProducto(ProductoEntity productoEntity){
        if (productoEntity.getProducto_id() != null) {
            ProductoEntity productoExistente = productoRepository.findById(productoEntity.getProducto_id()).orElse(null);
            if (productoExistente != null) {
                if (productoEntity.getNombre() != null) productoExistente.setNombre(productoEntity.getNombre());
                if (productoEntity.getPrecio() != null) productoExistente.setPrecio(productoEntity.getPrecio());
                if (productoEntity.getStock() != null) productoExistente.setStock(productoEntity.getStock());
                    productoRepository.save(productoExistente);
            } else productoEntity.setProducto_id(null);
        }
        productoRepository.save(productoEntity);
    }

    /** Eliminar producto por ID **/
    public void eliminarProductoPorID(Integer id){
        productoRepository.deleteById(id);
    }
}
