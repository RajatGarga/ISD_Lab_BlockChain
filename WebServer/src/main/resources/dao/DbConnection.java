package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private Connection conn = null;
	private Statement stmt = null;
	
	public DbConnection(){
		
		try {
			Class.forName(DbStatic.JDBC_DRIVER);
			conn = DriverManager.getConnection(DbStatic.DB_URL, DbStatic.USER, DbStatic.PASSWORD);
			stmt =  conn.createStatement();
			String sql;
			sql = "create database IF NOT EXISTS restdb;";
			stmt.executeUpdate(sql);
			sql= "use restdb;";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public Connection getConn() {
		return conn;
	}
}