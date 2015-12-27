package com.url.dao;

public class UrlStatus {
	
	String urlName;
	String urlServerType;
	String urlStatus;
	
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getUrlServerType() {
		return urlServerType;
	}
	public void setUrlServerType(String urlServerType) {
		this.urlServerType = urlServerType;
	}
	public String getUrlStatus() {
		return urlStatus;
	}
	public void setUrlStatus(String urlStatus) {
		this.urlStatus = urlStatus;
	}
	
	public UrlStatus(String urlName, String urlServerType, String urlStatus) {
		super();
		this.urlName = urlName;
		this.urlServerType = urlServerType;
		this.urlStatus = urlStatus;
	}
	
	
	
	

}
