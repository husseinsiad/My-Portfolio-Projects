/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scsu.datamanipulation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author siyaa
 */
public class App {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //repeat menu option until user to exit
        boolean isGoing=true;
        while(isGoing) {
            System.out.println("1:WEATHER");
            System.out.println("2:FOOTBALL");
            System.out.println("3:Exit");
            System.out.println("___________________________");
            System.out.println("Choose the option you want");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    getWeatherData();
                    break;
                case 2:
                    getFootballData();
                    break;
                case 3:
                    System.out.println("Thanks, See you soon!!!");
                    isGoing=false;

            }
        }

    }

    private static void getWeatherData() throws IOException {
     File weatherFile = new File("weather.dat");
        Weather weather = new Weather(weatherFile);
        weather.dayNumSmallTemp();
        String minTemp = weather.dayNumSmallTemp();
        System.out.println("-----------------------Result--------------------------------");
        System.out.println("THE SMALLEST TEMPERATURE FOR THE DAY: " + weather.convertToInt(minTemp));
        System.out.println("--------------------------------------------------------------");

    }

    private static void getFootballData() throws IOException {
     //printing Team name, F and A score
     File footballFile = new File("football.dat");
        Football football = new Football(footballFile);
        //display smallest difference between teams
        System.out.println("\nThe team is " + football.getSmallestDiff());
    }
}
