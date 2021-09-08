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

public class EjemploJdbcUpdate {
public static void main(String[] args) {
		
	try (Connection conn = ConexionBaseDatos.getInstance();){
		
		Repositorio<Producto> repo = new ProductoRepositorio();
		System.out.println("==============Listar==================");

		repo.listar().forEach(System.out::println);
		System.out.println("==============Obtener Por ID==================");
		System.out.println(repo.porId(2L));
		System.out.println("==============Eliminar Producto==================");
		Producto producto = new Producto();
		producto.setId(3L);
		
		
		repo.eliminar(producto.getId());
		System.out.println("Se actualizo el producto");
		repo.listar().forEach(System.out::println);
		
		
				
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
	
	}
}
}
