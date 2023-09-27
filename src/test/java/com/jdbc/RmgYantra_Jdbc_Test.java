package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.mysql.cj.jdbc.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v113.css.CSS.GetInlineStylesForNodeResponse;
import org.openqa.selenium.support.ui.Select;



public class RmgYantra_Jdbc_Test {
	public static void main(String[] args) throws SQLException, InterruptedException {
		Connection conn=null;
		String expectdData="TY_Proj_TMS_SDET_04";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://rmgtestingserver:8084/");
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(expectdData);
		driver.findElement(By.name("createdBy")).sendKeys("Shivakumar H");
		WebElement statusDropDown = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select s=new Select(statusDropDown);
		s.selectByValue("Created");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();


		Driver driver1=new Driver();
		try {
			DriverManager.registerDriver(driver1);
			conn=DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
			Statement state = conn.createStatement();
			String query="select * from project";
			ResultSet result = state.executeQuery(query);
			boolean flag=false;
			while(result.next())
			{
				String actualData=result.getString(4);
				if(actualData.equalsIgnoreCase(expectdData))
				{
					flag=true;
					System.out.println("Project name:"+result.getString(4));
					System.out.println("Status"+result.getString(5));
					System.out.println("Team_Size"+result.getString(6));
					break;

				}
			}
			if(flag)
				System.out.println("Project is created succesfully");
			else
				System.out.println("Project is not created");
		}
		catch (Exception e) {
			// TODO: handle exception
		}

		conn.close();	
	}
}
