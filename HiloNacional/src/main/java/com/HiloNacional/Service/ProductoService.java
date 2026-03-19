package com.HiloNacional.Service;

import java.util.List;
import java.util.Optional;

import com.HiloNacional.model.Producto;
import com.HiloNacional.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	final private ProductosRepository productosRepository;

	@Autowired
	public ProductoService(ProductosRepository productosRepository) {
		this.productosRepository = productosRepository;	
	}
	
	public List<Producto> getProductos() {
		return productosRepository.findAll();
	}

	public Producto getProducto(Long id) {
		return productosRepository
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El producto con el id ["+id+"] no existe"));
	}

	public Producto deleteProducto(Long id) {
		Producto tmpProducto = null;
		if(productosRepository.existsById(id)) {
			tmpProducto = productosRepository.findById(id).get();
			productosRepository.deleteById(id);
		}
		return tmpProducto;
	}

	public Producto addProducto(Producto producto) {
		Optional<Producto> prod = productosRepository.findByNombre(producto.getNombre());
		if(prod.isEmpty()) {
			return productosRepository.save(producto);
		} else {
			producto=null;
		}
		return producto;
	}

	
	public Producto updateProducto(Long id, String nombre, String descripcion, Double precio, 
			String imagen, String categoria, Integer stock, String talla, String color, String material) {
		
		Producto tmpProducto = null;
		
		if(productosRepository.existsById(id)) {
			Producto prod = productosRepository.findById(id).get();
			
			// Validaciones campo por campo
			if(nombre != null) prod.setNombre(nombre);
			if(descripcion != null) prod.setDescripcion(descripcion);
			if(precio != null) prod.setPrecio(precio);
			if(imagen != null) prod.setImagen(imagen);
			if(categoria != null) prod.setCategoria(categoria);
			if(stock != null) prod.setStock(stock);
			if(talla != null) prod.setTalla(talla);
			if(color != null) prod.setColor(color);
			if(material != null) prod.setMaterial(material);
			
			tmpProducto = productosRepository.save(prod);
		}
		return tmpProducto;
	}
}