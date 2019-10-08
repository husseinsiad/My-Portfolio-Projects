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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author siyaa
 */
public class Football extends Weather{

    //File and BufferedReader 
    private File footFile;
    private BufferedReader bReader; //reads file
    
    
//    HashMap: to store key and values
    private Map<String, Integer> goals = new HashMap<>();
    
    public Football(File theFile) {
	super(null);
	footFile = theFile;
    }
    
    public String getSmallestDiff() throws IOException {
	bReader = new BufferedReader(getData(footFile));
	//keep track the first 3 columns on the table
	String line = null;// default null
	String teams= "";
	String forGoals = "";
	String againGoals = "";
	System.out.printf("%s %15s %15s\n", "Team", "F", "A");
	while ((line = bReader.readLine()) != null) {
	    String[] enterD = gap_Line(line);
		teams = findV(enterD, 2);
		forGoals = findV(enterD, 7);
		againGoals = findV(enterD, 9);
		keepScores(teams, convertToInt(forGoals), convertToInt(againGoals));
		System.out.println(teams + " \t " + forGoals + " \t " + againGoals);
	}
	bReader.close();
	return getSmallestScored();
	
}

    private String getSmallestScored() {
	final List<Integer> scored = new ArrayList<Integer>();
	final List<String> teams = new ArrayList<String>();

	for (Entry<String, Integer> entryMap : this.goals.entrySet()) {
		scored.add(Math.abs(entryMap.getValue()));
		teams.add(entryMap.getKey());
	}

	int l = 0;
	int c = 0;
	int m = 0;
	for (int i = 0; i < scored.size(); i++) {
		c = scored.get(i);
		if (i != 0) {
			l = scored.get(i - 1);
		}//end if
		if (l != 0 && c < scored.get(m)) {
			m = i;
		}//end if
	}//end for loops
	return teams.get(m);
}

    private void keepScores(String teams, int forGoals, int againstGoals) {
	// TODO Auto-generated method stub
	if(forGoals!=-1 || againstGoals!=-1){
	    this.goals.put(teams, (forGoals-againstGoals));
	}
	
    }
    //Find data and return with file
    public static FileReader getData(File footFile) throws IOException{
	FileReader reader = new FileReader(footFile);
	
	return reader;
    }
    
	   
}
