package com.url.dao;

public class UrlStruct {
	
	String urlName;
	String urlServerType;
	
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
	
	public UrlStruct(String urlName, String urlServerType) {
		super();
		this.urlName = urlName;
		this.urlServerType = urlServerType;
	}
	

}
