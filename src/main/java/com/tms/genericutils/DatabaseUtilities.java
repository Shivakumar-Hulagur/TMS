package com.tms.genericutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;
import com.mysql.jdbc.Driver;

public class DatabaseUtilities {

	 Connection conn;
	public void getDBConnect() throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		conn= DriverManager.getConnection(IPathConstants1.dbURL,IPathConstants1.dbUsername,IPathConstants1.dbPassword);
		System.out.println(conn);
	}
	
	public void closeDBConnection() throws SQLException
	{
		conn.close();
	}
	public String executeSelectQuery(String query,String expRes,int columnNum) throws SQLException
	{
		getDBConnect();
		Statement stat = conn.createStatement();
		ResultSet res = stat.executeQuery(query);
		boolean flag=false;
		String  actRes=null;
		while(res.next())
		{
			actRes = res.getString(columnNum);
			if(actRes.contains(expRes))
			{
				flag=true;
				break;
			}
		}
		conn.close();
		if(flag)
			return actRes;
		else
			return "No Data Found";
		
	}
}