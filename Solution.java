package main;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	//standard max function
	static int max(int x, int y) { return (y > x) ? y : x; }
	
	//modified version of Kadane's algorithm
	static int maxSubarrayContiguous(int[] arr, int indexMax) {
		//takes in first value just so it has _something_
		int maxSoFar = arr[0];
		int currMax = arr[0];
		 
		   for (int i = 1; i < indexMax; i++) {
		        currMax = max(arr[i], currMax + arr[i]);
		        maxSoFar = max(maxSoFar, currMax);
		   }
		   return maxSoFar;
		}
	
	static int maxSubarrayNonContiguous(int[] arr, int indexMax) {
		int sumMax = 0;
		int max = arr[0];
		boolean negArray = true;
		int res = 0;
		for (int i = 0; i < indexMax; i++) {
			//add all positive numbers
			if (arr[i] >= 0) {
				sumMax += arr[i];
				negArray = false;
			}
			//find the smallest negative number
			if (arr[i] >= max) { max = arr[i];}
		}
	
		if (negArray == false)
			res = sumMax;
		//if the array consists of all negative numbers
		//return the smallest number
		if (negArray)
			res = max;
		
		return res;
		
	}
	

    public static void main(String[] args) {
    	
    	Scanner in = new Scanner(System.in);
    	//array to store continous solutions
    	int[] testSolutionsC = new int[10];
    	//array to store non-continuous
    	int[] testSolutionsNC = new int[10];
        int resC;
        int resNC;
        int runs;
        runs = Integer.parseInt(in.nextLine());
        
        //go through the amount of solutions
        for (int i = 0; i < runs; i++) {
        	String inputString;
        	int[] arr = new int[100000];
        	int indexMax = Integer.parseInt(in.nextLine());
        	
        	//tokenize input values;
        	//all entries are separated by a space
        	inputString = in.nextLine();
        	int arrIndex=0;
    		StringTokenizer st = new StringTokenizer(inputString);
    		while(st.hasMoreTokens()) {
    			arr[arrIndex] = Integer.parseInt(st.nextToken());
    			arrIndex++;
    		}
    		
    		//continuous solution
        	resC = maxSubarrayContiguous(arr, indexMax);
        	//non-continuous solution
        	resNC = maxSubarrayNonContiguous(arr, indexMax);
        	
        	//add continuous solutions to array
        	testSolutionsC[i] = resC;
        	//add non-continuous solutions to array
        	testSolutionsNC[i] = resNC;
        	
        }
        
        //print out entries
    	for(int i = 0; i < runs; i++) {
    		System.out.print(testSolutionsC[i] + " ");
    		System.out.print(testSolutionsNC[i]);
    		System.out.println();
    	}
    	
    }
}