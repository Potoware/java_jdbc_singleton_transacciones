package com.potoware.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.repositorio.ProductoRepositorio;
import com.potoware.java.jdbc.repositorio.Repositorio;
import com.potoware.java.jdbc.util.ConexionBaseDatos;

public class EjemploJdbcTrx {
public static void main(String[] args) throws SQLException {
		
	try (Connection conn = ConexionBaseDatos.getInstance();){
		
		if (conn.getAutoCommit()) {
			conn.setAutoCommit(false);
		}
		try {
			Repositorio<Producto> repo = new ProductoRepositorio();
			System.out.println("==============Listar==================");
			repo.listar().forEach(System.out::println);
			System.out.println("==============Obtener Por ID==================");
			System.out.println(repo.porId(2L));
			System.out.println("==============Insertar Producto==================");
			Producto producto = new Producto();
			producto.setNombre("Base cama dividida");
			producto.setPrecio(500000);
			producto.setFechaRegistro(new Date());
			producto.setSku("zxcv78949");
			Categoria categoria = new Categoria();
			categoria.setId(3L);
			producto.setCategoria(categoria);
			repo.guardar(producto);
			System.out.println("Se guardo el producto");
			System.out.println("==============Editar Producto==================");
			Producto productoE = new Producto();
			productoE.setId(1L);
			productoE.setNombre("Mouse Sencillo");
			productoE.setPrecio(15000);
			Categoria categoriaE = new Categoria();
			categoriaE.setId(2L);
			productoE.setSku("lkoi897654");
			productoE.setCategoria(categoriaE);
			repo.guardar(productoE);
			System.out.println("Se actualizo el producto");
			repo.listar().forEach(System.out::println);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
		
				
	} 
}
}
