/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scsu.datamanipulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 *
 * @author siyaa
 */
public class Weather {
    //File and BufferedReader 
    private File file;
    private BufferedReader bReader; //reads file
    
//    HashMap: to store key and values
    private Map<String, Integer> MinAndMaxTem = new HashMap<>();
    
    //constructor for weather in order to create file
    public Weather(File theFile){
	file = theFile; // store file
    }//end Weather consther
    
    //super constructor
    public Weather(){
	super();
    }
    
    // Find the day number with smallest temperature spread in column two and maximum temp to third column
    public String dayNumSmallTemp() throws IOException{
	
	bReader = new BufferedReader(getdata(file));
	//keep track the first 3 columns on the table
	String firstLine = null;//set with null
	String numberOfDays= "";
	String maxTemp = "";
	String minTemp = "";
		System.out.println("---------------------------------------------");
	System.out.printf("%s %15s %15s\n", "Day", "Min-Temp", "Max-Temp");
		System.out.println("---------------------------------------------");
	while((firstLine =bReader.readLine())!=null){
	    String[] enterD = gap_Line(firstLine);
	    numberOfDays= findV(enterD, 1);
	    minTemp= findV(enterD, 2);
	    maxTemp = findV(enterD, 3);
		keepDayTemprature(numberOfDays, convertToInt(minTemp), convertToInt(minTemp));
	   //print number of days, minTemp
	    System.out.printf("%d %15d %15d\n",convertToInt(numberOfDays),convertToInt(minTemp),convertToInt(minTemp));
	    
	}//end while loop
	
	return getSmallestinfo();
    }//end method
   
    //get smallest infor
    protected String getSmallestinfo() {
	String dAy = ""; 
	int spData = 0;
	for(Entry<String, Integer>map:this.MinAndMaxTem.entrySet()){
	    if(spData==0||map.getValue()<spData && isNum(map.getKey())&& nonNegative(map.getValue())){
		dAy = map.getKey();
		spData = map.getValue();
	    }//end if
	}//end for while
	return dAy;
    }//end getSmallestinfo
	
	///it will return non negative value
	private boolean nonNegative(Integer value) {
	// TODO Auto-generated method stub
	return value > 0;
    }
    
    //very if not integer
    private boolean isNum(String key) {
	// TODO Auto-generated method stub
	
	return -1!=convertToInt(key);
    }

    protected void keepDayTemprature(String numberOfDays, int minTemp, int maxTemp) {
	// TODO Auto-generated method stub
	if(minTemp!=-1||maxTemp!=-1){
	    this.MinAndMaxTem.put(numberOfDays, maxTemp - minTemp);
	}//end if
	
    }//end storedayTemp

    //convert string into int
    public int convertToInt(String temp) {
	try{
	    return Integer.valueOf(temp);
	}
	catch(NumberFormatException ex){
	    
	}
	return -1;
    }

    public String findV(String[] enterD, int x) {
	for(int i = 0; i < enterD.length; i ++){
	    if(i ==x)
		return enterD[i];
	}
	return ""; //returns empty 
    }

    // putting gab or spilit lines 
    protected String[] gap_Line(String line) {
	Pattern pat=  Pattern.compile("\\s+");// compiles regular given into a pattern
	String[] data = pat.split(line);
	
	
	return data;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    //Find data and return with file
    public static FileReader getdata(File file) throws IOException{
	FileReader reader = new FileReader(file);
	
	return reader;
    }
    
    //printing toString
}
