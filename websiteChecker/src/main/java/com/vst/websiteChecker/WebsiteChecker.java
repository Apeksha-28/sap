package com.vst.websiteChecker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

	public class  WebsiteChecker{
    	    	
    	    	 public static void main(String[] args) {
    	    		 
    	 	    	// Define the URLs of the websites to check
    	 	        List<String> urls = new ArrayList<>();
    	 	        urls.add("https://virtuososofttech.com/");
    	 	        urls.add("https://www.snehprecast.in");
    	 	        urls.add("https://www.vpel.in");
    	 	        urls.add("https://www.sneh-constopremix.com");
    	 	        urls.add("https://www.vcharge.in");
    	 	        urls.add("http://www.indsathi.com");
    	 	        
    	 	        // Create a StringBuilder to store the results of the website checks
    	 	        StringBuilder result = new StringBuilder();
    	 	        // Loop through each website and check its status code
    	 	        for (String url : urls) {
    	 	            try {
    	 	                URL obj = new URL(url);
    	 	                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
    	 	                conn.setRequestMethod("GET");
    	 	                System.out.println("Checking site: "+url);
    	 	                int responseCode = conn.getResponseCode();
    	 	                // Append the website and its status code to the result StringBuilder
    	 	                result.append(url).append(": ").append(responseCode == HttpURLConnection.HTTP_OK ? "Working" : "Not Working").append("\n");
    	 	            } catch (IOException e) {
    	 	                // Append the website and an error message to the result StringBuilder
    	 	                result.append(url).append(": ").append("Not Working - ").append(e.getMessage()).append("\n");
    	 	            }
    	 	        }
    	 	        // Send an email with the result of the website checks
    	 	        System.out.println(result.toString());
    	 	        sendEmail(result.toString());
    	 	    }
    	 	    private static void sendEmail(String message) {
    	 	        // Set the email properties
    	 	        Properties props = new Properties();
    	 	        props.put("mail.smtp.host", "smtpout.secureserver.net");
    	 	        props.put("mail.smtp.port", "587");
    	 	        props.put("mail.smtp.auth", "true");
    	 	        props.put("mail.smtp.starttls.enable", "false");
    	 	        // Set the email account credentials
    	 	        final String username = "apeksha.bansod@virtuososofttech.com";
    	 	        final String password = "Virtuoso@123";
    	 	        // Create a session with the email account
    	 	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    	 	            protected PasswordAuthentication getPasswordAuthentication() {
    	 	                return new PasswordAuthentication(username, password);
    	 	            }
    	 	        });
    	 	        try {
    	 	            // Create a message and set the recipient, subject, and content
    	 	            Message msg = new MimeMessage(session);

    	 	            msg.setFrom(new InternetAddress(username));
    	 	            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pratik.pingale@virtuososofttech.com"));
    	 	            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse("pratik.pingle@vpel.in ,mahesh.ghule@virtuososofttech.com ,ajinkya.dahale@virtuososofttech.com"));
    	 	            msg.setSubject("Website Status Report");
    	 	            msg.setText(message); 
    	 	            // Send the message
    	 	            Transport.send(msg);
    	 	            System.out.println("Email sent successfully.");
    	 	        } catch (MessagingException e) {
    	 	            System.out.println("Failed to send email.");
    	 	            e.printStackTrace();
    	 	        }
    	    }
    	

