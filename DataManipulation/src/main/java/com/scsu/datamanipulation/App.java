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

        System.out.println("1:WEATHER");
        System.out.println("2:FOOTBALL");
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
            default:
                System.out.println("Invalid Option");
        }

    }

    private static void getWeatherData() throws IOException {
     File weatherFile = new File("weather.dat");
        Weather wRead = new Weather(weatherFile);
        wRead.dayInSmallTemp();
        String minSpreadTemp = wRead.dayInSmallTemp();
        System.out.println("Day has smallest temp: " + wRead.changeToInt(minSpreadTemp));
    }

    private static void getFootballData() throws IOException {
     //printing Team name, F and A score
     File footballFile = new File("football.dat");
        Football teamReader = new Football(footballFile);
        //teamReader.printSmallestDiffernceIn4NA(); 
        System.out.println("\nThe team is " + teamReader.printSmallestDiffernceIn4NA());
    }
}
