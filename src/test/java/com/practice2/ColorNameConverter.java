package com.practice2;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ColorNameConverter {
    public static void main(String[] args) {
        // Set the path to your Chrome WebDriver executable
      //  System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        
        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // The hexadecimal color code you want to convert
        String hexColorCode = "#FF0000";
        
        // Load a webpage (you can use any webpage)
        driver.get("https://example.com");
        
        // Execute JavaScript to convert color code to name using the "Name that Color" library
        String colorName = (String) ((JavascriptExecutor) driver).executeScript(
                "var colorNames = new colornames.ColorNames(); " +
                "var color = '" + hexColorCode + "'; " +
                "var name = colorNames.getName(color); " +
                "return name;");
        
        // Print the color name
        System.out.println("Hex Color Code: " + hexColorCode);
        System.out.println("Color Name: " + colorName);
        
        // Close the WebDriver
        driver.quit();
    }
}
