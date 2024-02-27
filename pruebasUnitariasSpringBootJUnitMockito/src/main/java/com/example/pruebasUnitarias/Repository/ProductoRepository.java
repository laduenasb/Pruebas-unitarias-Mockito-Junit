package com.example.pruebasUnitarias.Repository;

import com.example.pruebasUnitarias.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    /**
     * Busca un producto por su nombre.
     *
     * @param nombre El nombre del producto que se desea buscar.
     * @return Un objeto Optional que puede contener el producto encontrado, o ser vacío si no se encuentra.
     */
    Optional<Producto> findByNombre(String nombre);
}
