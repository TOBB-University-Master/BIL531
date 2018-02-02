/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaca.bil531.hm2;

import java.util.ArrayList;

/**
 *
 * @author kemalsamikaraca
 */
public class Main {
    
    
    public static void main(String[] args){
        System.out.println("***** ***** ***** SUBSET SUM PROBLEM ***** ***** *****");
        int[] test = { -4 , 2 , 4 , 5, 7 , 8 , 9, 11 , 12};
        int sum = 3;
       
        System.out.println("Subset sum O(n) >>");
        System.out.println(subsetSum(test, sum));
    }
    
    /**
     * Special case >> |S| = 2
     * Special case >> Given array is sorted
     * Give an O(n)-algorithm for the special case of the Subset Sum problem
     */
    public static boolean subsetSum(int[] A , int sum){
        
        // if there exist negative number in array A then 
        // turn it to positive number integer
        int min=0;
        for( int i : A){
            if(i<min)
                min = i;
        }
        
        printArray(A);
        if(min<0){
            sum += (Math.abs(min))*2;
            for(int i=0; i<A.length; i++){
                A[i]=A[i] + (Math.abs(min));
            }
        }
        printArray(A);
        
        // Let create an array with size sum
        int[] sumArray = new int[sum+1];
        for ( int i : A ){
            
            // checker for sumArray size
            if(i < sum+1){
                int intPosition = i;
                int sumMinusPosition = sum - i;
                sumArray[intPosition]++;
                    
                printArray(sumArray);
                if(sumArray[sumMinusPosition] == 1 && sumMinusPosition!=intPosition ) 
                    return true;
            }
        }   
        return false;
    }
    
    public static void printArray(int[] arr){
        System.out.print("{");
        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.print("}");
        System.out.println();
    }
}
