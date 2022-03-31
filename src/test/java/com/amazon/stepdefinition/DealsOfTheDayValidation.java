package com.amazon.stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DealsOfTheDayValidation {
	static WebDriver driver;
	static long st;
	static JavascriptExecutor j;
	@BeforeClass(groups="default")
	public static void bc() {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@AfterClass(groups="default")
	public static void ac() {
		driver.quit();
	}
	@BeforeMethod
	public void startsAt() {
		st = System.currentTimeMillis();
	}
	@AfterMethod 
		public void endsAt(){
		long et = System.currentTimeMillis();
		System.out.println("Time Taken:" + (et-st));
	}
	@Test(priority=1,groups="sanity")
	public void DealOfDay() {
		WebElement deals = driver.findElement(By.xpath("//a[contains(text(),'Deals')]"));
		deals.click();
	}
	@Test(priority=2,groups="sanity")
	public void LightningDeal() {
		WebElement ld = driver.findElement(By.xpath("//span[contains(text(),'Lightning')]"));
		ld.click();	
	}
	@Test (priority=3)
	public void scroll() {
		WebElement prime = driver.findElement(By.xpath("//div[text() ='Prime']"));
		j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(true)", prime);
	}
	@Test (priority=4)
	public void radiobutton() {
		WebElement rb = driver.findElement(By.xpath("//input[@type='checkbox']"));
		rb.click();
	}
	@Test(priority=5)
	public void price() {
		WebElement price = driver.findElement(By.xpath("//div[text() ='Price']"));
		j.executeScript("arguments[0].scrollIntoView(false)", price);	
	}
	@Test(priority=6)
	public void electronics() {
	    WebElement e = driver.findElement(By.xpath("//span[contains(text(),'Electronics')]"));
	    e.click(); 
	}
	

}
