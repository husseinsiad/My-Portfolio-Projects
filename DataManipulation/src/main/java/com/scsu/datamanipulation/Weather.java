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
    
    /*
     *  NOTE: Using HashMaps helps to store null key or values that are null, then comparing to HashTable
     *  	HashMap is also linkedHashmap.
     *  The following two line of codes uses to store String and Integer, maps day to temperature
     */
    private Map<String, Integer> dayMinMaxTem = new HashMap<>();
    
    //weather constructor to create file 
    public Weather(File theFile){
	file = theFile; // store file
    }//end Weather consther
    
    //super constructor
    public Weather(){
	super();
    }
    
    // Find the day number with smallest temperature spread in column two and maximum temp to third column
    public String dayInSmallTemp() throws IOException{
	
	bReader = new BufferedReader(findDat(file));
	//temp store first 3 columns
	String line = null;//set with null
	String dayNumber= "";
	String tempMax = "";
	String tempMin = "";
	System.out.printf("%s %15s %15s\n", "Day", "Min Temp", "Max Temp");
	while((line =bReader.readLine())!=null){
	    String[] enterD = gap_Line(line); 
	    dayNumber= findV(enterD, 1);
	    tempMin= findV(enterD, 2);
	    tempMax = findV(enterD, 3);
	    storeDayTemp(dayNumber, changeToInt(tempMin), changeToInt(tempMin));// column1=dayNum, column2=temMin, column3 temMax
	  //  System.out.println(dayNumber+" "+ changeToInt(tempMin)+" "+changeToInt(tempMin));
	    System.out.printf("%d %15d %15d\n",changeToInt(dayNumber),changeToInt(tempMin),changeToInt(tempMin));
	    
	}//end while loop
	
	return findSmallestData();
    }//end method
   
    //reusable method findingSmmalest
    protected String findSmallestData() {
	String dAy = ""; 
	int spData = 0; // spreed
	for(Entry<String, Integer>map:this.dayMinMaxTem.entrySet()){
	    if(spData==0||map.getValue()<spData && isNum(map.getKey())&& nonNegative(map.getValue())){
		dAy = map.getKey();
		spData = map.getValue();
	    }//end if
	}//end for while
	return dAy;
    }//end findSmalestData
	
	///return non negative to be true
	private boolean nonNegative(Integer value) {
	// TODO Auto-generated method stub
	return value > 0;
    }
    
    //very if not integer
    private boolean isNum(String key) {
	// TODO Auto-generated method stub
	
	return -1!=changeToInt(key);
    }

    protected void storeDayTemp(String dayNumber, int tempMin, int tempMax) {
	// TODO Auto-generated method stub
	if(tempMin!=-1||tempMax!=-1){
	    this.dayMinMaxTem.put(dayNumber, tempMax - tempMin);
	}//end if
	
    }//end storedayTemp

    //changing string into int
    public int changeToInt(String temp) {
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
    public static FileReader findDat(File file) throws IOException{
	FileReader reader = new FileReader(file);
	
	return reader;
    }
    
    //printing toString
}
