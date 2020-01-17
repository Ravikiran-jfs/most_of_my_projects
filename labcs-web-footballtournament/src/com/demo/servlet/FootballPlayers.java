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
 * Servlet implementation class FootballPlayers
 */
@WebServlet("/FootballPlayers")
public class FootballPlayers extends HttpServlet {
	//connection is instantiated here
	Connection connection;
	@Override
	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Set the connection
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	int playerID = Integer.parseInt(req.getParameter("playerID"));
	//	String last_name = req.getParameter("last_name");
		enterPlayerDetails(1, "Alisson");
		
	}
	
	public void enterPlayerDetails(int playerID, String last_name) {
		try {
			//statement is created here
			Statement statement = connection.createStatement();
			
			int noOfRowsInserted = statement.executeUpdate("insert into football_players values (" + playerID + ", '" + last_name + "')");
			if(noOfRowsInserted ==1) {
				System.out.println("NO OF ROWS INSERTED : " + noOfRowsInserted);
			}
			else {
				System.out.println("No rows inserted!");
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}
