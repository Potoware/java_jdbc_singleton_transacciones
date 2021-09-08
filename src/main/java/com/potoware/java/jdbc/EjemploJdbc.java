package com.potoware.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploJdbc {
public static void main(String[] args) {
	String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Bogota";
	String username = "root";
	String password = "sasa";

	
	try (Connection conn = DriverManager.getConnection(url, username,password);
			Statement  stmt = conn.createStatement();
			ResultSet resultado= stmt.executeQuery("SELECT * FROM productos");
			){
		
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
