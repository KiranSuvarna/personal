package com.msg91.endpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * Java program to read file using Scanner class in Java.
 * java.util.Scanner is added on Java 5 and offer convenient method to read data
 *
 * @author
 */
public class ScannerExample {

    public static void main(String args[]) throws FileNotFoundException {
  
        //creating File instance to reference text file in Java
        File text = new File("/Users/kiran/Downloads/sample.csv");
      
        //Creating Scanner instnace to read File in Java
        Scanner scnr = new Scanner(text);
      
        //Reading each line of file using Scanner class
        int lineNumber = 1;
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            System.out.println(line);
            lineNumber++;
        }       
    
    }   
  
}

