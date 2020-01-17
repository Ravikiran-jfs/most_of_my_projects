package com.demo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServlet
 */


@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	Connection connection;
	@Override
	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookID = Integer.parseInt(req.getParameter("bookID"));
		String bookName = req.getParameter("bookName");
		EnterDetails(bookID, bookName);
	} 
	public void EnterDetails(int bookID, String bookName) {
		try {
			Statement statement = connection.createStatement();
			
			int noOfRowsInserted = statement.executeUpdate("insert into book values(" + bookID + ", '" + bookName + "')");
			if(noOfRowsInserted ==1) {
				System.out.println("NO OF ROWS INSERTED : " + noOfRowsInserted);
			}
			else {
				System.out.println("No rows inserted!");
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
	}
}
