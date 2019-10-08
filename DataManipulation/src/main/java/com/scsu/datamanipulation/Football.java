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
    
    
    /*
     *  NOTE: Using HashMaps helps to store null key or values that are null, then comparing to HashTable
     *  	HashMap is also linkedHashmap.
     *  The following two line of codes uses to store String and Integer, maps day to temperature
     */
    private Map<String, Integer> goals = new HashMap<>();
    
    public Football(File theFile) {
	super(null);
	footFile = theFile;
    }
    
    public String printSmallestDiffernceIn4NA() throws IOException {
	bReader = new BufferedReader(findDat2(footFile));
	//temp store first 3 columns
	String line = null;//set with null
	String plTeam= "";
	String score4Goals = "";
	String scoreAgainGoals = "";
	System.out.printf("%s %15s %15s\n", "Team", "F", "A");
	while ((line = bReader.readLine()) != null) {
	    String[] enterD = gap_Line(line);
	    plTeam = findV(enterD, 2);
		score4Goals = findV(enterD, 7);
		scoreAgainGoals = findV(enterD, 9);
		storeGoalScores(plTeam, changeToInt(score4Goals), changeToInt(scoreAgainGoals));
		System.out.println(plTeam + " \t " + score4Goals + " \t " + scoreAgainGoals);
	}
	bReader.close();
	return findSmallestGoalScored();
	
}

    private String findSmallestGoalScored() {
	final List<Integer> scoredVal = new ArrayList<Integer>();
	final List<String> plTeams = new ArrayList<String>();

	for (Entry<String, Integer> entryMap : this.goals.entrySet()) {
	    	scoredVal.add(Math.abs(entryMap.getValue()));
		plTeams.add(entryMap.getKey());
	}

	int l = 0;
	int c = 0;
	int m = 0;
	for (int i = 0; i < scoredVal.size(); i++) {
		c = scoredVal.get(i);
		if (i != 0) {
			l = scoredVal.get(i - 1);
		}//end if
		if (l != 0 && c < scoredVal.get(m)) {
			m = i;
		}//end if
	}//end for loops
	return plTeams.get(m);
}

    private void storeGoalScores(String plTeam, int score4Goals, int scoreAgainst) {
	// TODO Auto-generated method stub
	if(score4Goals!=-1 || scoreAgainst!=-1){
	    this.goals.put(plTeam, (score4Goals-scoreAgainst));
	}
	
    }
    //Find data and return with file
    public static FileReader findDat2(File footFile) throws IOException{
	FileReader reader = new FileReader(footFile);
	
	return reader;
    }
    
	   
}
