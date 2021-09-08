package com.potoware.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.potoware.java.jdbc.util.ConexionBaseDatos;

public class EjemploJdbc {
public static void main(String[] args) {
		
	try (Connection conn = ConexionBaseDatos.getInstance();
			Statement stmt = conn.createStatement();
			ResultSet resultado = stmt.executeQuery("Select * from productos");){
		
		while (resultado.next()) {
			System.out.print(resultado.getString("nombre")+" ");
			System.out.print(resultado.getInt("id")+" ");
			System.out.println(resultado.getInt("precio")+" ");
			
			}
				
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
	
	}
}
}
