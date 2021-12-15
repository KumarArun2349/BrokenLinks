package org.sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Broken {
	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver",
				"E:\\SELENIUM\\Practice(15.12.2021)\\BrokenLinks\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.instagram.com/?hl=en");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total Links: " + links.size());
		int count = 0;
		for (int i = 0; i < links.size(); i++) {
			String attribute = links.get(i).getAttribute("href");

			// to convert String into URL
			URL u = new URL(attribute);

			// to setup Connection
			URLConnection openConnection = u.openConnection();

			// to get response
			HttpURLConnection http = (HttpURLConnection) openConnection;

			int responseCode = http.getResponseCode();

			if (responseCode >= 400 && responseCode <= 499) {
				count++;
				System.out.println(attribute);
			}
		}
		System.out.println(count);

		driver.quit();
	}
}
