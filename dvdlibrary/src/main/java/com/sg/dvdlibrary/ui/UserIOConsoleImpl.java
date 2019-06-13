/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author siyaa
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
       double returnDouble=Double.MIN_VALUE;
       boolean isValid=false;
       while(!isValid){
           print(prompt);
           String userInput=sc.nextLine();
           
           try{
               returnDouble=Double.parseDouble(userInput);
                isValid=true;
           }catch(NumberFormatException ex){
           }
       }
       return returnDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double returnDouble = Double.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnDouble = Double.parseDouble(UserInput);
//                returnInt = readInt(UserInput);
                if (returnDouble >= min && returnDouble <= max) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnDouble;
    }

    @Override
    public float readFloat(String prompt) {
        float returnFloat = Float.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnFloat = Float.parseFloat(UserInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
         float returnFloat = Float.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnFloat = Float.parseFloat(UserInput);
//                returnInt = readInt(UserInput);
                if (returnFloat >= min && returnFloat <= max) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnFloat;
    }

    @Override
    public int readInt(String prompt) {
        int returnInt = Integer.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnInt = Integer.parseInt(UserInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int returnInt = Integer.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnInt = Integer.parseInt(UserInput);
//                returnInt = readInt(UserInput);
                if (returnInt >= min && returnInt <= max) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnInt;
    }

    @Override
    public long readLong(String prompt) {
        long returnLong = Long.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnLong = Long.parseLong(UserInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
       long returnLong = Long.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String UserInput = sc.nextLine();
            try {
                returnLong = Long.parseLong(UserInput);
//                returnInt = readInt(UserInput);
                if (returnLong >= min && returnLong <= max) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                
            }
        }
        return returnLong;
    }

    @Override
    public String readString(String prompt) {
         print(prompt);
        String userInput = sc.nextLine();
        return userInput;
    }

}
