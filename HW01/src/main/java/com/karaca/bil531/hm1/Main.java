/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaca.bil531.hm1;

import java.util.ArrayList;

/**
 *
 * @author kemalsamikaraca
 */
public class Main {
    
    
    public static void main(String[] args){
        
        System.out.println("***** ***** ***** FERMATS CONJECTURE ***** ***** *****");
        fermatConjecture(2);
        
        System.out.println("***** ***** ***** SUBSET SUM PROBLEM ***** ***** *****");
        int[] test = {  2 , 3 , 4 , 5, 7 , 8 , 9, 11 , 12};
        int sum = 11;
        System.out.println("Subset sum q1 >>");
        System.out.println(subsetSumQ1(test, 16));
        
        System.out.println("Subset sum q2 >>");
        ArrayList<ArrayList<Integer>> subsetList = subsetSumQ2(test, sum);
        for (ArrayList<Integer> x : subsetList)
            System.out.println(x);
        
        System.out.println("Subset sum q3 >>");
        subsetSumQ3(test,0,sum,new int[test.length]);
        
        System.out.println("Subset sum q4 >>");
        ArrayList<ArrayList<Integer>> subsetListQ4 = subsetSumQ4(test, sum);
        for (ArrayList<Integer> x : subsetListQ4)
            System.out.println(x);
    }
    
    //**************************************************************************
    //**************************************************************************
    //  PROPERLY ORDERING A COUNTABLE SET
    //**************************************************************************
    //**************************************************************************
    
    /**
     * Math.pow(a,n) + Math.pow(b,n) = Math.pow(c,n)
     * @param k 
     */
    public static void fermatConjecture(int k){
        int c = 2;
        mainLoop:
        do{
            for(int a=c-1; a>0; a--){
                for(int b=1; b<=a; b++){
                    if ( Math.pow(c, k) == Math.pow(a, k) + Math.pow(b, k) ){
                        System.out.printf(a+"^%d"+" + "+b+"^%d"+" = "+c+"^%d \n",k,k,k);
                        break mainLoop;
                    }
                }
            } 
            c++;
        }while(true);
    }
    
    
    //**************************************************************************
    //**************************************************************************
    //  EFFICIENT ALGORITHM DESIGN FOR SPECIAL CASES OF SUBSET SUM 
    //**************************************************************************
    //**************************************************************************
    /**
     * Special case >> |S| = 2
     */
    public static boolean subsetSumQ1(int[] A , int sum){
        // linear search O(n)
        for (int i : A)
            if(i == sum) return true;
        
        // O(n^2)
        for ( int i : A )
            for (int j : A)
                if (i!=j && sum==i+j) return true;
        
        return false;
    }
    
    
    /**
     * Special case >> |S| = 2
     */
    public static ArrayList<ArrayList<Integer>> subsetSumQ2(int[] A , int sum){
        ArrayList<ArrayList<Integer>> resultSets = new ArrayList<ArrayList<Integer>>();
        // linear search O(n)
        for (int i : A)
            if(i == sum){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                resultSets.add(temp);
            }
        
        // O(n^2)
        for ( int i : A )
            for (int j : A)
                if (i<j && sum==i+j){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(i);
                    temp.add(j);
                    resultSets.add(temp);
                }
        return resultSets;
    }
    
    
    /**
     * Special case >> |S| = 2
     */
    public static void subsetSumQ3(int[] A, int index, int sum, int[] solution){
        
        int currSum = 0;
        for(int i=0; i<A.length ; i++)
            currSum += A[i] * solution[i];
        
        if(index != A.length){
            if (currSum == sum) {
                ArrayList<Integer> x = new ArrayList<>();
                for (int i = 0; i < solution.length; i++) {
                    if (solution[i] == 1) {
                        x.add(A[i]);
                    }
                }
                System.out.println(x);
            } else {
                solution[index] = 1;
                subsetSumQ3(A, index + 1, sum, solution);
                solution[index] = 0;
                subsetSumQ3(A, index + 1, sum, solution);
            }
        }
    }
    
    
    /**
     * Special case >> |S| = 2
     * Give an O(n·lgn)-algorithm for the special case of the Subset Sum problem
     */
    public static ArrayList<ArrayList<Integer>> subsetSumQ4(int[] A , int sum){
        // eğer verilen array sorted ise O(n.logn) ile bulunabilir çünkü her sayı için binary searchte arama yapılır
        
        ArrayList<ArrayList<Integer>> resultSets = new ArrayList<ArrayList<Integer>>();
        for ( int i : A ){
            int result = binarySearch(A, 0 , A.length-1, sum-i);
            if(result>=1){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                temp.add(A[result]);
                resultSets.add(temp);
            }
        }
            
        return resultSets;
    }
    
    /**
     * Util function
     */
    public static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            
            if (arr[mid] == x) return mid;

            if (arr[mid] > x) return binarySearch(arr, l, mid - 1, x);

            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }
}
