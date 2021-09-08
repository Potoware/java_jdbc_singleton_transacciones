package com.potoware.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.potoware.java.jdbc.models.Categoria;
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
				ResultSet resultSet = stmt.executeQuery("SELECT p.*,c.nombre as categoria FROM PRODUCTOS as p inner join categorias as c ON (p.categoria_id = c.id);");){
			
			while(resultSet.next()) {
			
				Producto p = crearProducto(resultSet);
				productos.add(p);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return productos;
	}
	
	
	@Override
	public Producto porId(Long id) {
		Producto producto = null;
		
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT p.*,c.nombre as categoria FROM PRODUCTOS as p inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?");)
		{
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				producto = crearProducto(rs);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return producto;
	}
	@Override
	public void guardar(Producto t) {
		
		String sql;
		if (t.getId() != null && t.getId()>0) {
			sql = "UPDATE productos SET nombre=?,precio=?,categoria_id=? where id=?";
		}
		else {
			sql = "INSERT INTO productos (nombre,precio,categoria_id, fecha_registro) VALUES(?,?,?,?)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
			
			stmt.setString(1,t.getNombre());
			stmt.setLong(2,t.getPrecio());
			stmt.setLong(3, t.getCategoria().getId());
			if (t.getId() != null && t.getId()>0) {
				stmt.setLong(4, t.getId());
			}
		else {
			stmt.setDate(4, new Date(t.getFechaRegistro().getTime()));
		}
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void eliminar(Long id) {
		
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")){
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Producto crearProducto(ResultSet resultSet) throws SQLException {
		Producto p = new Producto();
		Categoria c = new Categoria();
		p.setId(resultSet.getLong("id"));
		p.setNombre(resultSet.getString("nombre"));
		
		p.setPrecio(resultSet.getInt("precio"));
		p.setFechaRegistro(resultSet.getDate("fecha_registro"));
		c.setId(resultSet.getLong("categoria_id"));
		c.setNombre(resultSet.getString("categoria"));
		p.setCategoria(c);
		return p;
	}




}
