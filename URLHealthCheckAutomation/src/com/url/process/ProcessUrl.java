package com.url.process;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.annotations.Test;

import com.automate.url.ImportXLData;
import com.url.dao.UrlStatus;
import com.url.dao.UrlStruct;
//@Test
public class ProcessUrl {
	
	public static LinkedHashMap<Integer, UrlStruct> urlList;
	public static ImportXLData urlData;
	public static String name;
	public static String serverType;
	public static int notPresentCount;
	public static String urlStatus;
	public static LinkedHashMap<Integer, UrlStatus> urlInfo = new LinkedHashMap<>();
	public static int urlInfoId = 1;
	
	public void iterateUrlList(WebDriver driver) throws IOException{
		
		urlData = new ImportXLData();
		urlList = urlData.getValueFromLinkedHashMap();
		
		for(Map.Entry<Integer, UrlStruct> e : urlList.entrySet()){
			
			name = e.getValue().getUrlName();
			serverType = e.getValue().getUrlServerType();
			System.out.println("URL Name : "+name+" Server Type : "+serverType);
			
			checkUrlStatus(driver, name, serverType);
			
		}
	}
	
	public void checkUrlStatus(WebDriver driver, String urlName, String serverType){
		
		notPresentCount = 0;
		try {
			driver.get(urlName);
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		try {
			
			driver.findElement(By.id("ci_logo"));
			
		} catch (NoSuchElementException nse) {
			
			notPresentCount++;
		}
		
		try {
			
			driver.findElement(By.className("cb-hm-logo"));
			
		} catch (NoSuchElementException nse) {
			
			notPresentCount++;
			
		}
		
		try {
			
			driver.findElement(By.className("logo-img retina-img"));
			
		} catch (NoSuchElementException nse) {
			
			notPresentCount++;
		}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(notPresentCount<3) {
			
			urlStatus = "Working";
		}
		
		else {
			
			urlStatus = "Not Working";
		}
		
		UrlStatus url = new UrlStatus(urlName, serverType, urlStatus);
		
		urlInfo.put(urlInfoId, url);
		
		urlInfoId++;
		
		System.out.println("URL Name : "+ urlName + " ServerType : "+ serverType + " Status : "+ urlStatus);
		
		
	}
	
	public LinkedHashMap<Integer, UrlStatus> getUrlStatusHashMap(){
		
		return urlInfo;
	}

}
