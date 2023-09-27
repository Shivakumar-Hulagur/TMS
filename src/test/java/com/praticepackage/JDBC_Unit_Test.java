package com.praticepackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;

public class JDBC_Unit_Test {
	public static void main(String [] args) throws SQLException
	{
		String proj="Manual";
		Connection conn=null;
		try {
			//Step 1: register driver 
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);

			//step 2: connect to database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TMS", "root", "root");
			System.out.println("===============Database connection established");

			//Step 3: create statement
			Statement stat=conn.createStatement();
			String query="Select * from project";
			String query1="insert into project values('SQL','Bharat Sir',2);";
			//Step 4: execute query
			boolean flag=false;
			int res1 = stat.executeUpdate(query1);
			System.out.println(res1);
			ResultSet res = stat.executeQuery(query);
			while(res.next())
			{
				String actRes=res.getString(1);
				System.out.println(actRes);
				if(proj.equalsIgnoreCase(actRes))
					flag=true;
			}
			Assert.assertTrue(flag);

		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			//Step 5: close the database connection
			conn.close();
			System.out.println("=============Database connection is closed================");
		}

	}
}
