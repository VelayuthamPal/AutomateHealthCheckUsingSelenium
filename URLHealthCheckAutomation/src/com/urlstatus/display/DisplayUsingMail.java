package com.urlstatus.display;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.url.dao.UrlStatus;
import com.url.process.ProcessUrl;

public class DisplayUsingMail {
	
	public static String htmlString = null;
	public static String date;
	public static ProcessUrl p = new ProcessUrl();
	public static LinkedHashMap<Integer, UrlStatus> lhmUrlStatus = p.getUrlStatusHashMap();
	
	public String getHeaderString(){
		
		date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
		
		System.out.println("Current date and time : "+date);
		
		String htmlHeader = "<table>"+
								"<tr>"+
									"<th bgcolor='orange'>Process Name</th>"+
									"<th bgcolor='orange'>Date and Time Stamp(IST)</th>"+
								"</tr>"+
								"<tr>"+
									"<td>URL Health Check</td>"+
									"<td>"+date+"</td>"+
								"</tr>"+
							"</table>"+
							"<table>"+
								"<tr>"+
									"<th bgcolor='#FFA500'>URL Name</th>"+
									"<th bgcolor='green'>Server Type</th>"+
									"<th bgcolor='#FFA500'>Working Status</th>"+
								"</tr>";
		
		return htmlHeader;
	}
	
	
	
	public String getHTMLContentString(){
		
		String htmlContent ="";
		
		for(Map.Entry<Integer, UrlStatus> urlList : lhmUrlStatus.entrySet()){
			
			htmlContent+= "<tr>"+
									"<td><a href="+urlList.getValue().getUrlName()+">"+urlList.getValue().getUrlName()+"</td>"+
									"<td>"+urlList.getValue().getUrlServerType()+"</td>"+
									"<td>"+urlList.getValue().getUrlStatus()+"</td>"+
								"</tr>";
		}
		return htmlContent+"</table>";	
	}
	
	public String getHTMLString(){
		
		htmlString = getHeaderString()+getHTMLContentString();
		
		return htmlString;
	}
	
	public void sendEmailHtml(){
		
		String to = "makayasatni@gmail.com";
		
		String from = "youngconfidencelearner@gmail.com";
		
		String host = "localhost";
		
		Properties properties = System.getProperties();
		
		properties.setProperty("mail.smtp.host", host);
		
		Session session = Session.getDefaultInstance(properties);
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("URL Health Check for "+date);
			
			message.setText(getHTMLString());
			
			System.out.println(getHTMLString());
			
			//Transport.send(message);
			
			System.out.println("Message Sent Succesfully");
			
		} catch (MessagingException mex) {
			
			mex.printStackTrace();
		}
	}

}
