/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package javaapplicationfooling;

import java.util.*;

/**
 *
 * @author MartaandFintan
 */
public class NQueens {
    //This is a classic problem
    //Go through each column
    //for each row, place queen
    //if is safe move on to the next column
    //once all queens are placed add one to the tally
    //print tally
    
    static int res = 0;
    
    public static void main (String args[]){
        
        //take in number of cols on board
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
    
        int[][] board = new int[n][n];
        
        //start the backtrack
        
        backtrack(board, n, 0);
        
        System.out.println("Result: " + res);
    
    
}
    
    //my backtrack method needs a board, board size, column number
    private static void backtrack(int[][] board, int n, int col){
        //first check if we have filled a board
        //System.out.println("Current col: " + col);
        if(col == n){
            System.out.println("Found one");
            printBoard(board, n);
            res +=1;
        }
        else{
            //go through each row and check if the queen is safe there
            for(int i = 0; i< n; i++){
                board[i][col] = 1;
                if(isSafe(board, n, i, col)){
                    
                    
                    
                    backtrack(board, n, col+1);
                }
                //take the queen away
                board[i][col] = 0;
            }
        }
        //if you run out of rows
        
    }
    
    private static void printBoard(int[][] board, int n){
        for(int k = 0; k<n; k++){
            for(int j = 0; j<n; j++){
                System.out.print(board[k][j]);
            }
            System.out.println();
        }
        System.out.println("**********");
    }
    
    private static boolean isSafe(int[][] board,int n, int row, int col){
        
        //check left, left upper diagonal and left lower diagonal
        int os = 1;
        for(int i = col-1; i>=0; i--){
            if(board[row][i] == 1){
                //System.out.println("False 1");
                return false;
            }
            //upper diagonal
            if(row-os >=0){
                if(board[row-os][i] == 1){
                    //System.out.println("False 2");
                    return false;
                }
            }
            if(row+os < n){
                if(board[row+os][i] == 1){
                    //System.out.println("False 3");
                    return false;
                }
            }         
            
                
            os++;
        }
        
        //System.out.println("Found safe move");
        
        
        return true;
    }
    
    
    
}
