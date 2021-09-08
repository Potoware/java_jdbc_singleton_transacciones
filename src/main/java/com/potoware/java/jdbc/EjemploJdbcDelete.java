package com.potoware.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.repositorio.ProductoRepositorio;
import com.potoware.java.jdbc.repositorio.Repositorio;
import com.potoware.java.jdbc.util.ConexionBaseDatos;

public class EjemploJdbcDelete {
public static void main(String[] args) {
		
	try (Connection conn = ConexionBaseDatos.getInstance();){
		
		Repositorio<Producto> repo = new ProductoRepositorio();
		System.out.println("==============Listar==================");

		repo.listar().forEach(System.out::println);
		System.out.println("==============Obtener Por ID==================");
		System.out.println(repo.porId(2L));
		System.out.println("==============Editar Producto==================");
		Producto producto = new Producto();
		producto.setId(3L);
		producto.setNombre("Teclado Mecanico Retroiluminado");
		producto.setPrecio(350000);
	
		
		repo.guardar(producto);
		System.out.println("Se actualizo el producto");
		repo.listar().forEach(System.out::println);
		
		
				
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
	
	}
}
}
