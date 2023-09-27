package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.mysql.cj.jdbc.Driver;

public class SelectQueryTest {
	public static void main(String[] args) throws SQLException {
		//to register driver
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);

		//to get the connection to the database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TMS", "root", "root");

		//to create statement
		Statement state = conn.createStatement();

		//to execute the query
		String query="select * from project;";
		ResultSet result = state.executeQuery(query);
		while(result.next())
		{
			System.out.println(result.getString(1)+": "+result.getString(2)+"-"+result.getString(3));
		}
		//to close the connection
		conn.close();
	}
}
