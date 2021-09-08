package com.potoware.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.util.ConexionBaseDatos;

public class ProductoRepositorio implements Repositorio<Producto>{

	private Connection getConnection() throws SQLException {
		return ConexionBaseDatos.getInstance();
	}
	@Override
	public List<Producto> listar()  {
		List<Producto> productos= new ArrayList<>();
		
		try (Statement stmt = getConnection().createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM PRODUCTOS;");){
			
			while(resultSet.next()) {
			
				Producto p = new Producto();
				p.setId(resultSet.getLong("id"));
				p.setNombre(resultSet.getString("nombre"));
				
				p.setPrecio(resultSet.getInt("precio"));
				p.setFechaRegistro(resultSet.getDate("fecha_registro"));
				productos.add(p);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return productos;
	}
	@Override
	public Producto porId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void guardar(Producto t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}




}
