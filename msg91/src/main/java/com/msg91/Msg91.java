package com.msg91;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import com.csvreader.CsvWriter;

public class Msg91 {
	private static final String authKey = "125910A85t0t2o57e1377a";
	//static String mobiles = "+918105505579";
	static String message = "Test Again";
	public  String senderId = "YUPLUS";
	public  String baseUrl = "https://control.msg91.com/api/sendhttp.php?";
	
	public String msg91(String phoneNumber){
		StringBuilder sb = new StringBuilder(baseUrl);
		String encoded_message = URLEncoder.encode(message);
		sb.append("authkey="+authKey);
		sb.append("&mobiles="+phoneNumber);
		sb.append("&message="+encoded_message);
		sb.append("&sender="+senderId);
		sb.append("&route=4");
		sb.append("&response=json");
		
		baseUrl = sb.toString();
		System.out.println(baseUrl);
		String response = null;
		try{
			URL url = new URL(baseUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			
			while((response = reader.readLine()) != null){
				System.out.println(response);
				File text1 = new File("C:/Users/Admin/Dropbox/delivery_status.csv");

				CsvWriter csvOutput;
				try {
					csvOutput = new CsvWriter(new FileWriter(text1, true), ',');
					 //Create Header for CSV
			        csvOutput.write(response);
			        csvOutput.write(phoneNumber);
			        
			        csvOutput.endRecord();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static void main(String[] args) {
		//creating File instance to reference text file in Java
        File text = new File("C:/Users/Admin/Dropbox/user_table_all_data_till_17.csv");
      
        //Creating Scanner instnace to read File in Java
        Scanner scnr = null;
		try {
			scnr = new Scanner(text);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        //Reading each line of file using Scanner class
        int lineNumber = 1;
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            //System.out.println(line);
            lineNumber++;
		Msg91 m = new Msg91();
		
		String res = m.msg91(line);
        

       
	}
	}
}
