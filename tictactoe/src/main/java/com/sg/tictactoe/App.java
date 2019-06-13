/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.tictactoe;

import java.util.Scanner;

/**
 *
 * @author dsmelser
 */
public class App {
    
    public static void main( String[] args ){
        //Tic-Tac-Toe
        
        //3x3 grid
        //user takes turns placing an x or an o
        //  each time we'll detect if someone won (3 across, down, or diagonally)
        
        //tracks whose turn it is (true = X's turn, false = O's turn)
        boolean isXTurn = true;
        
        //board representation
//        String[] board = new String[9];
//        
//        //initialize board
//        for( int i = 0; i < board.length; i++){
//            
//            board[i] = " ";
//        }


        String[] board = {  " ", " ", " ", 
                            " ", " ", " ", 
                            " ", " ", " " };
        
        
        //main game loop
        while( !isGameOver( board ) )
        {
            printBoard( board );
            
            if( isXTurn ){
                System.out.println("Player X");
            } else {
                System.out.println( "Player O");
            }

            
            System.out.print( "Please enter the position for your move: ");
            
            int movePosition = getValidMoveFromUser( board );
            
            //using ternary operator
            //we set the position we get from the user
            //to X or O depending on isXTurn
            board[movePosition] = isXTurn ? "X" : "O";
            
            isXTurn = !isXTurn;
        }
        
         printBoard( board );
        
    }
    
    public static void printBoard( String[] boardData ){
        
        int spacesPrintedForRow = 0;
        
        for( int i = 0; i < boardData.length; i++ ){
            System.out.print(boardData[i] + "|");
            
            spacesPrintedForRow++;
            
            if( spacesPrintedForRow == 3 ){
                System.out.println();
                System.out.println("------");
                spacesPrintedForRow = 0;
            }
        }
        
    }
    
    public static boolean isGameOver( String[] toCheck ){
        
        boolean gameOver = false;
        
        //first check rows
        gameOver = gameOver || 
                ( toCheck[0].equals( toCheck[1] ) &&
                  toCheck[0].equals( toCheck[2] ) &&
                  !(toCheck[0].equals( " " ))
                );
        
        gameOver = gameOver || 
                ( toCheck[3].equals( toCheck[4] ) &&
                  toCheck[3].equals( toCheck[5] ) &&
                  !(toCheck[3].equals( " " ))
                );
 
        gameOver = gameOver || 
                ( toCheck[6].equals( toCheck[7] ) &&
                  toCheck[6].equals( toCheck[8] ) &&
                  !(toCheck[6].equals( " " ))
                );
        
        //second check columns
        gameOver = gameOver || 
                ( toCheck[0].equals( toCheck[3] ) &&
                  toCheck[0].equals( toCheck[6] ) &&
                  !(toCheck[0].equals( " " ))
                );        

        gameOver = gameOver || 
                ( toCheck[1].equals( toCheck[4] ) &&
                  toCheck[1].equals( toCheck[7] ) &&
                  !(toCheck[1].equals( " " ))
                ); 
        
        gameOver = gameOver || 
                ( toCheck[2].equals( toCheck[5] ) &&
                  toCheck[2].equals( toCheck[8] ) &&
                  !(toCheck[2].equals( " " ))
                );         
        
        //third check diagonals
         gameOver = gameOver || 
                ( toCheck[0].equals( toCheck[4] ) &&
                  toCheck[0].equals( toCheck[8] ) &&
                  !(toCheck[0].equals( " " ))
                );
         
         gameOver = gameOver || 
                ( toCheck[2].equals( toCheck[4] ) &&
                  toCheck[2].equals( toCheck[6] ) &&
                  !(toCheck[2].equals( " " ))
                ); 
         
         if( !gameOver ){
             
             boolean spaceFound = false;
             
             for( int i =0;i < toCheck.length; i++ ){
                 if( toCheck[i].equals(" ")){
                     spaceFound = true;
                     break;
                 }
             }
             
             gameOver = !spaceFound;
             
         }
         
         return gameOver;
    }

    private static int getValidMoveFromUser(String[] board) {
        int validPosition = Integer.MIN_VALUE;
        
        Scanner userInput = new Scanner(System.in);
        
        boolean valid = false;
        
        while( !valid ){
            //do something
            int attempt = userInput.nextInt();
            if( attempt >= 0 && attempt < board.length ){
                if( board[attempt].equals(" ")){
                    valid = true;
                    validPosition = attempt;
                }
            }
        }
        

        return validPosition;
    }
    
}
