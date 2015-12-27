package com.url.drive;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.url.process.ProcessUrl;
import com.urlstatus.display.DisplayUsingMail;

public class DriverUrl {
	
	public static void main(String args[]) throws IOException{
		
		//Ensuring proper pre conditions for IE Browser
		System.setProperty("webdriver.ie.driver", "../URLHealthCheckAutomation/lib/IEDriverForSelenium/IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, "true");
		WebDriver driver = new InternetExplorerDriver(capabilities);
		
		ProcessUrl p = new ProcessUrl();
		p.iterateUrlList(driver);
		
		driver.close();
		
		DisplayUsingMail dsplyMail = new DisplayUsingMail();
		dsplyMail.sendEmailHtml();
		
		System.out.println("Test Completed");
	}

}
