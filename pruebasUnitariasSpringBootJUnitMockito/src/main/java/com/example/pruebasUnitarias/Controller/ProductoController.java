package com.example.pruebasUnitarias.Controller;

import com.example.pruebasUnitarias.Entity.Producto;
import com.example.pruebasUnitarias.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController
{
    @Autowired
    private ProductoService productoService;


    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }


    @GetMapping("/{nombre}")
    public ResponseEntity<Producto> obtenerProductoPorNombre(@PathVariable String nombre) {
        Optional<Producto> producto = productoService.buscarPorNombre(nombre);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto nuevoProducto
    ) {

        Optional<Producto> productoExistente = productoService.buscarPorId(id);

        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();


            producto.setNombre(nuevoProducto.getNombre());
            producto.setPrecio(nuevoProducto.getPrecio());


            Producto productoActualizado = productoService.guardarProducto(producto);

            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id El ID del producto a eliminar, proporcionado como parte de la URL.
     * @return ResponseEntity sin contenido si se eliminó con éxito, o una respuesta de error 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
