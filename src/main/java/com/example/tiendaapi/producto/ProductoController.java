package com.example.tiendaapi.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /** Listar todos los productos **/
    @GetMapping
    public List<ProductoEntity> getProductos(){
        return productoService.getProductos();
    }

    /** Buscar producto por ID **/
    @GetMapping("/id/{id}")
    public ProductoEntity buscarPorID(@PathVariable Integer id){

        return productoService.buscarPorID(id);
    }

    /** Listar por nombre **/
    @GetMapping("/nombre/{nombre}")
    public List<ProductoEntity> buscarPorNombre(@PathVariable String nombre){
        return productoService.buscarPorNombre(nombre);
    }

    /** Guardar producto **/
    @PostMapping(value = "/guardar", consumes = "application/json")
    public void guardarProducto(@RequestBody ProductoEntity productoEntity){
        productoService.guardarProducto(productoEntity);
    }

    /** Eliminar producto **/
    @DeleteMapping("/borrar/{id}")
    public void borrarProducto(@PathVariable Integer id){
        productoService.eliminarProductoPorID(id);
    }

    /** Modificar producto **/
    @PutMapping("/modificar")
    @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
    public void modificarProducto(@RequestBody ProductoEntity productoEntity){
        productoService.guardarProducto(productoEntity);
    }

    @GetMapping("/mi-rol")
    public String getMiRol(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().toString();
    }
}
