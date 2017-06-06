/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Nitin.Gupta
 *
 */
public class RestClient {

	public static void main(String[] args) {
		HttpURLConnection connection = null;  
		  try {
		    //Create connection
		    URL url = new URL("http://dev01.ishimaya.com/rest-api/product/feed/sample/GOWN");
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("GET");
		    /*connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");*/

//		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
		    String line;
		    while((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    
		    System.out.println(response.toString());
		    
		  } catch (Exception e) {
		    e.printStackTrace();
		  } finally {
		    if(connection != null) {
		      connection.disconnect(); 
		    }
		  }
		}
}
