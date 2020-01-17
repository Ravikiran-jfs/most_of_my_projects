package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
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
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		PrintWriter table = resp.getWriter();
		
		getBookID();
		
		//table.println("<table border = 1><thead><tr><th>Book Id</th><th> </th><th>Book Name</th></tr></thead></table>");
	}
	
	public void getBookID() {
		Statement statement;
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from book");
			
			while(resultSet.next()) {
				int bookID = resultSet.getInt("bookID");
				String bookName = resultSet.getString("bookName");
				
				System.out.println(bookID + "->" + bookName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
