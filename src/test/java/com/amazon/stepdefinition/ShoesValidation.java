package com.amazon.stepdefinition;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShoesValidation {
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
	@Test(priority=1, groups="sanity")
	public void search() {
		WebElement srch = driver.findElement(By.xpath("//input[@type='text']"));
		srch.sendKeys("Shoes for women");
	}
	@Test(priority=2, groups="sanity")
	public void select() throws Exception {
		Robot r =  new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	@Test(priority=3)
	public void options() {
		WebElement s1 = driver.findElement(By.xpath("//span[contains(text(),'Agile')]"));
		s1.click();
		WebElement s2 = driver.findElement(By.xpath("//span[contains(text(),'Hd0062 ')]"));
		s2.click();
		WebElement s3 = driver.findElement(By.xpath("//span[contains(text(),'Alexa')]"));
		s3.click();	
	}
	@Test(priority=4)
	public void windows() {
		Set<String> cw = driver.getWindowHandles();
		List<String> x = new ArrayList<String>(cw);
		String text = x.get(2);
		driver.switchTo().window(text);
	}
	@Test(priority=5)
	public void scroll() {
		WebElement scrl = driver.findElement(By.xpath("//label[text()='Size:']"));
		j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(true)", scrl);
		
	}
	@Test(priority=6)
	public void size() {
		WebElement selectsize = driver.findElement(By.xpath("//select[contains(@name,'size')]"));
		Select s = new Select(selectsize);
		s.selectByIndex(5);
	}
	@Test(priority=7)
	public void scroll1(){
	WebElement srclup = driver.findElement(By.xpath("//label[text()='Quantity:']"));
	j.executeScript("arguments[0].scrollIntoView(false)", srclup);
	}
	@Test(priority=8,enabled=false)
	public void add() {
		WebElement bn = driver.findElement(By.xpath("//input[contains(@value,'Cart')]"));
		bn.click();

	}
	

	
	

}
