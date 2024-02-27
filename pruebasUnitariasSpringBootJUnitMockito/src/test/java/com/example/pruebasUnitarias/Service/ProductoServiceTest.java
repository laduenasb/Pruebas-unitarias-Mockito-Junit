package com.example.pruebasUnitarias.Service;

import com.example.pruebasUnitarias.Entity.Producto;
import com.example.pruebasUnitarias.Repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductoServiceTest {

  // Insertamos la anotación para inyectar automáticamente los mock (simulaciones)
  @InjectMocks
  private ProductoService productoService;

  // Esta anotación nos permite inicializar la simulación de las funcInalidades
  @Mock
  private ProductoRepository productoRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }
  @Test
  void guardarProducto() {
    // Crearemos un nuevo objeto para las pruebas
    Producto producto = new Producto();
    // Configurar el comportamiento del mock del repositorio para que nos devuelva un dato al guardar el nombre del producto
    Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenReturn(producto);
    // Llamaremos al método del servicio para realizarle la prueba
    Producto resultado = productoService.guardarProducto(producto);
    // Afirmar que el dato que recibe la variable "resultado" sea el que se esperaba con producto
    assertEquals(producto, resultado);
  }

  @Test
  void buscarPorNombre() {
    // Definir un nombre para buscar
    String nombre = "Producto1";
    // Crear el objeto para realizar las pruebas
    Producto producto = new Producto();
    // Configurar el comportamiento del mock del repositorio para que nos devuelva un dato al buscar el nombre del producto
    Mockito.when(productoRepository.findByNombre(nombre)).thenReturn(Optional.of(producto));
    // Llamaremos al metodo del servicio para realizarle la prueba
    Optional<Producto> resultado = productoService.buscarPorNombre(nombre);
    // Afirmar que el dato que recibe la variable "resultado" sea el que se esperaba con producto
    assertTrue(resultado.isPresent());
    assertEquals(producto, resultado.get());
  }

  @Test
  void listarProductos() {
    // Crear una lista de productos
    List<Producto> productos = new ArrayList<>();
    // Configuración para que me permita traer todos los datos de la lista
    Mockito.when(productoRepository.findAll()).thenReturn(productos);
    // Llamaremos al método que me permita traer los productos para realizar el test
    List<Producto> resultado = productoService.listarProductos();
    //Afirmaremos que los datos obtenidos coinciden con la lista de prueba.
    assertEquals(productos,resultado);
  }

  @Test
  void eliminarProducto() {
    // Definir un ID para eliminar un producto
    Long id = 1L;
    // LLamamos al método del servicio de eliminar el producto
    productoService.eliminarProducto(id);
    // Verificar que el método haya eliminado el id correspondiente
    Mockito.verify(productoRepository, Mockito.times(1)).deleteById(id);
  }
}