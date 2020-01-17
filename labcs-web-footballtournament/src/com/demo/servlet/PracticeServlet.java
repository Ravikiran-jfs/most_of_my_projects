package com.demo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PracticeServlet
 */
@WebServlet("/PracticeServlet")
public class PracticeServlet extends HttpServlet {
	
	public static void main(String[] args) {
		//createTable("cool_books");
		//dropTable("value");
		//createValues(104, "Ponniyin Selvan", "cool_books");
		//createValuesUsingPreparedStatement(105, "Kane and Abel", "cool_books");
	}
	
	public static void createTable(String value) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			int noOfRowsExecuted = statement.executeUpdate("create table " + value + "(valueID number, valueName varchar(30))");
			
			System.out.println("Table " + value + " is created with " + noOfRowsExecuted + " row(s)");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void dropTable(String value) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			int noOfRowsExecuted = statement.executeUpdate("drop table " + value);
			
			System.out.println("Table " + value + " is dropped.");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void createValues(int valueId, String valueName, String tableName) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			int noOfRowsExecuted = statement.executeUpdate("insert into " + tableName + " values(" + valueId + ", '" + valueName + "')");
			
			System.out.println("Value is inserted into cool_books.");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void createValuesUsingPreparedStatement(int valueId, String valueName, String tableName) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			PreparedStatement preparedStatement = connection.prepareStatement("insert into " + tableName + " values(?,?)");
			//Execute the query

			preparedStatement.setInt(1, valueId);
			preparedStatement.setString(2, valueName);
			int noOfRowsExecuted = preparedStatement.executeUpdate();
			
			System.out.println("Values are inserted into cool_books.");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
