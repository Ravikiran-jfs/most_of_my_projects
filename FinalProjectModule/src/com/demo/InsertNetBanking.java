package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class InsertNetBanking
 */
@WebServlet("/InsertNetBanking")
public class InsertNetBanking extends HttpServlet {
	Connection connection = null;
	@Override
	public void init() throws ServletException {
		try {
			System.out.println("INIT INVOKED");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}		
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int netBankingId = Integer.parseInt(request.getParameter("netBankingId"));
		String netBankingUsername = request.getParameter("netBankingUsername");
		String netBankingPassword = request.getParameter("netBankingPassword");
		
		insertDetails(/*netBankingId,*/ netBankingUsername, netBankingPassword);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("dbbookselect");
		requestDispatcher.forward(request, response);
	}
	
	public void insertDetails(/*int netBankingId, */String netBankingUserame, String netBankingPassword) {
		// Get ojdbc14.jar
		// Load the driver
		try {
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			int noOfRowsInserted = statement.executeUpdate("insert into tnetbanking values(" + /*netBankingId + */", '" + netBankingUserame + ", '" + netBankingPassword + "')" );
			if(noOfRowsInserted ==1) {
				System.out.println("NO OF ROWS INSERTED : " + noOfRowsInserted);
			}
			else {
				System.out.println("No rows inserted!");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
