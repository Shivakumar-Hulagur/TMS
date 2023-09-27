package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class UpdateQueryTest {
	public static void main(String[] args) throws SQLException {
		Driver driver=new Driver();

		DriverManager.registerDriver(driver);

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TMS", "root", "root");

		Statement state = conn.createStatement();

		String query="insert into project values('Manual','Suhas sir','1');";

		int result = state.executeUpdate(query);
		if(result>0)
			System.out.println("Data is created");
		else
			System.out.println("Unable to update the query");
		conn.close();
	}
}
