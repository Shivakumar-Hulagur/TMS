//Write a script to insert the date to 'Users' Table read the data from the users and when user provide the exsited name it should not store the data in database else it should store the data.
package com.assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.cj.jdbc.*;
import com.mysql.jdbc.Driver;

public class Verify_JDBC_Test {
	public static void main(String[] args) throws SQLException {

		String id="0";
		//To read the data from the user
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the Application please enter your name to store the data");
		String name=sc.nextLine().replace(" ", "");
		

		//To register the database
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);

		//To get the connection to the database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TMS", "root", "root");

		//To create statement
		Statement stat = conn.createStatement();

		//Select Query
		String query="select * from Users;";



		//To execute/perform the neccessary action related to the script
		ResultSet res = stat.executeQuery(query);
		boolean flag=false;
		while(res.next())
		{
			if(res.getString(2).equalsIgnoreCase(name))
			{
				flag=true;
			}
			else
				id=res.getString(1);
		}
		int newId=Integer.parseInt(id)+1;

		//Update Query
		String upQuery="insert into Users values("+newId+",'"+name+"');";

		if(!flag)
		{
			int upRes=stat.executeUpdate(upQuery);
			if(upRes>0)
				System.out.println("The data is stored sucessfully");
			else
				System.err.println("Unable to store the data right now , please try again after some times");
		}
		else
			System.err.println("The user name is already exist, please try again by providing the new user name\n");

		System.out.println("******Thank you for using the application *********");

	}
}
