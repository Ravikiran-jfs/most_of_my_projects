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
 * Servlet implementation class DBSelectServlet
 */
@WebServlet("/DBSelectServlet")
public class DBSelectServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<h1>Welcome to the world of Servlets!</h1>");
		
		PrintWriter genericTable = resp.getWriter();
		genericTable.println("<table border = 1><thead><tr><th>Employee Id</th><th> </th><th>Employee first name</th></tr></thead></table>");
		
		fetchEmployeeDetails();
		fetchEmployeeDetails(out);
		
		
	}
	
	public void fetchEmployeeDetails() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			ResultSet resultSet = statement.executeQuery("select * from employees");
			while(resultSet.next()) {
				int employeeId = resultSet.getInt("employee_id");
				String firstName = resultSet.getString("first_name");
				System.out.println(employeeId + ">" + firstName);
			}
			System.out.println(resultSet);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void fetchEmployeeDetails(PrintWriter printOnHTML) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			ResultSet resultSet = statement.executeQuery("select * from employees");
			printOnHTML.println("<table border = 1>");
			while(resultSet.next()) {
//				int employeeId = resultSet.getInt("employee_id");
//				String firstName = resultSet.getString("first_name");
				//System.out.println(employeeId + ">" + firstName);
				//printOnHTML.println("<tr>");
				printOnHTML.println("<tr><td>" + resultSet.getInt("employee_id") + "</td>");
				//printOnHTML.println("<td> </td>");
				printOnHTML.println("<td>" + resultSet.getString("first_name") + "</td>");
				printOnHTML.println("</tr>");
			}
			printOnHTML.println("</table>");
			System.out.println(resultSet);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
}
