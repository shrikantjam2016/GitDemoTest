package selenium;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLink {
	static WebDriver driver=null;
	public static void main(String[] args) {
		
		brokenlink();
		
	}
	private static void brokenlink() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver_win32_1\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		//https://rahulshettyacademy.com/AutomationPractice/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		List<WebElement> links=driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		SoftAssert s=new SoftAssert();
		for(WebElement wb:links)
		{
			try
			{
			String st1=wb.getAttribute("href");
			HttpsURLConnection cn=(HttpsURLConnection) new URL(st1).openConnection();
			cn.setRequestMethod("HEAD");
			cn.connect();
			int responseCode=cn.getResponseCode();
			System.out.println(responseCode);
			s.assertTrue(responseCode>400, wb.getText()+"="+responseCode);
			
			}
			catch(Exception e)
			{
				System.out.println("kahia tari zala");
			}
			
		}
		s.assertAll();
	
		
	
	}


}
