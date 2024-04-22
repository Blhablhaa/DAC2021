package com.app.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * @author Shubham
 * 
 * Infra: Tomcat 8.5
 * Java: 1.8.060
 * MySql: 8.0.23
 */

public class SeleniumBasic {

	public static void main(String[] args) {
		//for chrome the system varible is webd
		System.setProperty("webdriver.gecko.driver", "F:/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		String url="http://www.google.com";
		driver.get(url);
		System.out.println(driver.getTitle());
		driver.findElement(By.id(url));
	//	driver.close(); //close the window created by this execution
		driver.quit(); //close all window created by this execution
	}

}
