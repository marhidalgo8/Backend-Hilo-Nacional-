package com.HiloNacional.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productoId", unique=true, nullable=false)
	private Long id;

	@Column(name="nombre", nullable=false)
	private String nombre;

	@Column(name="descripcion", nullable=false)
	private String descripcion;

	@Column(name="precio", nullable=false)
	private Double precio;

	@Column(name="imagen", nullable=false)
	private String imagen;

	@Column(name="categoria", nullable=false)
	private String categoria;

	@Column(name="stock", nullable=false)
	private Integer stock;

	@Column(name="talla")
	private String talla;

	@Column(name="color")
	private String color;

	@Column(name="material")
	private String material;

	@CreationTimestamp
	@Column(name="fecha_creacion", updatable=false)
	private LocalDateTime fechaCreacion;
	
	// Constructor 
	public Producto(String nombre, String descripcion, Double precio, String imagen, 
            String categoria, Integer stock, String talla, String color, String material) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
			this.imagen = imagen;
			this.categoria = categoria;
			this.stock = stock;
			this.talla = talla;
			this.color = color;
			this.material = material;
	}//constructor
	
	// constructor vacio
	public Producto() {
	}// constructor vacio

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	@Override
	public String toString() {
	    return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
	            + ", imagen=" + imagen + ", categoria=" + categoria + ", stock=" + stock + ", talla=" + talla 
	            + ", color=" + color + ", material=" + material + ", fechaCreacion=" + fechaCreacion + "]";
	}
}
