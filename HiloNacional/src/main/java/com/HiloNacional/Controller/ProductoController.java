package com.HiloNacional.Controller;

import java.util.List;

import com.HiloNacional.model.Producto;
import com.HiloNacional.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/HiloNacional/productos/")
@CrossOrigin(origins="http://127.0.0.1:5500/PI-HiloNacional-CH64/productos.html", methods=RequestMethod.GET)
public class ProductoController {
	
private final ProductoService productoService;
	
	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	// GET - LISTAR TODOS LOS PRODUCTOS
	@GetMapping
	public List<Producto> getProductos() {
		return productoService.getProductos();
	}
	
	// GET - UN SOLO PRODUCTO POR ID
	@GetMapping(path="{productoId}")
	public Producto getProducto(@PathVariable("productoId") Long id) {
		return productoService.getProducto(id);	
	}

	// DELETE - ELIMINAR PRODUCTO
	@DeleteMapping(path="{productoId}")
	public Producto deleteProducto(@PathVariable("productoId") Long id) {
		return productoService.deleteProducto(id);	
	}

	// POST - CREAR PRODUCTO
	@PostMapping
	public Producto addProducto(@RequestBody Producto producto) {
		return productoService.addProducto(producto);
	}

	// PUT 
	@PutMapping(path="{productoId}")
	public Producto updateProduct(@PathVariable("productoId") Long id,
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String imagen,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) Double precio,
			@RequestParam(required = false) String categoria,
			@RequestParam(required = false) Integer stock,
			@RequestParam(required = false) String talla,
			@RequestParam(required = false) String color,
			@RequestParam(required = false) String material) {
		
		return productoService.updateProducto(id, nombre, descripcion, precio, imagen, categoria, stock, talla, color, material);
	}
}


