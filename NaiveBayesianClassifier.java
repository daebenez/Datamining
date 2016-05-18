/*
 * @author David Ebenezer
 * Naive Bayesian Classifier
 * Task 3 Classification
 */

package com.datamining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class NaiveBayesianClassifier {
	
	ArrayList<ArrayList<String>> columnList = new ArrayList<ArrayList<String>>();
	ArrayList<String> rowList = null;
	int columnNum = 0;
	double PofPopgH = 0;
	double PofAgegH = 0;
	double PofGengH = 0;
	double PofRacgH = 0;
	double PofBacgH = 0;
	double PofHillary = 0;
	double PofBernie = 0;
	
	double PofPopg = 0;
	double PofAgeg = 0;
	double PofGeng = 0;
	double PofRacg = 0;
	double PofBacg = 0;
	
	double PofPopgB = 0;
	double PofAgegB = 0;
	double PofGengB = 0;
	double PofRacgB = 0;
	double PofBacgB = 0;
	
	/*
	 * Load the data from the file ouput of the preprocessing step into arraylist of arraylists
	 */
	private void readFile()
	{
		String FileUrl = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Output/NBFinal.csv";
		BufferedReader br = null;
		String line = "";
		try{
			br = new BufferedReader(new FileReader(FileUrl));
			while ((line = br.readLine()) != null) {
				rowList = new ArrayList<String>();
				String[] eachLine = line.split(",");
				columnNum = rowList.size();
				for(int i=0;i<eachLine.length;i++)
				{	
					rowList.add(eachLine[i]);
				}
				columnList.add(rowList);
			}
			
		}
		 catch (FileNotFoundException e) {
				System.out.println("The input csv file you specified was not found at the path: "+FileUrl);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * Calculate conditional probability for each of the 5 demographic features
	 */
	private void calculateConditionalProbability()
	{
		for(ArrayList<String> al : columnList)
		{
			//Probability of Hillary Clinton winning
			if(al.get(2).equals("Hillary Clinton"))
			{
				PofHillary = PofHillary + 1;
			}
			//Probability of Bernie Sanders winning
			else if(al.get(2).equals("Bernie Sanders"))
			{
				PofBernie = PofBernie + 1;
			}
			//Probability of population increase given Hillary Clinton wins
			if(al.get(2).equals("Hillary Clinton") && al.get(3).equals("increased"))
			{
				PofPopgH = PofPopgH + 1;
			}
			//Probability of high % of young children given Hillary CLinton Wins
			if(al.get(2).equals("Hillary Clinton")&& al.get(4).equals("high"))
			{
				PofAgegH = PofAgegH + 1;
			}
			//Probability of high % of female given Hillary Clinton Wins
			if(al.get(2).equals("Hillary Clinton")&& al.get(5).equals("high"))
			{
				PofGengH = PofGengH + 1;
			}
			//Probability of high Percentage of racial minorities given Hillary Clinton wins
			if(al.get(2).equals("Hillary Clinton")&& al.get(6).equals("high"))
			{
				PofRacgH = PofRacgH + 1;
			}
			//Probability of high percentage of population with bachelors degree given Hillary CLinton wins
			if(al.get(2).equals("Hillary Clinton")&& al.get(7).equals("high"))
			{
				PofBacgH = PofBacgH + 1;
			}
			
	
			//Probability of population increase
			if(al.get(3).equals("increased"))
			{
				PofPopg = PofPopg + 1;
			}
			//Probability of high % of young children
			if(al.get(4).equals("high"))
			{
				PofAgeg = PofAgeg + 1;
			}
			//Probability of high % of female
			if(al.get(5).equals("high"))
			{
				PofGeng = PofGeng + 1;
			}
			//Probability of high Percentage of racial minorities
			if(al.get(6).equals("high"))
			{
				PofRacg = PofRacg + 1;
			}
			//Probability of high percentage of population with bachelors degree
			if(al.get(7).equals("high"))
			{
				PofBacg = PofBacg + 1;
			}
			
			
			//Probability of population increase given Bernie Sanders wins
			if(al.get(2).equals("Bernie Sanders") && al.get(3).equals("increased"))
			{
				PofPopgB = PofPopgB + 1;
			}
			//Probability of high % of young children given Bernie Sanders Wins
			if(al.get(2).equals("Bernie Sanders")&& al.get(4).equals("high"))
			{
				PofAgegB = PofAgegB + 1;
			}
			//Probability of high % of female given Bernie Sanders Wins
			if(al.get(2).equals("Bernie Sanders")&& al.get(5).equals("high"))
			{
				PofGengB = PofGengB + 1;
			}
			//Probability of high Percentage of racial minorities given Bernie Sanders wins
			if(al.get(2).equals("Bernie Sanders")&& al.get(6).equals("high"))
			{
				PofRacgB = PofRacgB + 1;
			}
			//Probability of high percentage of population with bachelors degree given Bernie Sanders wins
			if(al.get(2).equals("Bernie Sanders")&& al.get(7).equals("high"))
			{
				PofBacgB = PofBacgB + 1;
			}
			
		}
		//Divide by Probability of Class
		PofPopgH = PofPopgH / PofHillary;
		PofAgegH = PofAgegH / PofHillary;
		PofGengH = PofGengH / PofHillary;
		PofRacgH = PofRacgH / PofHillary;
		PofBacgH = PofBacgH / PofHillary;
		
		PofPopgB = PofPopgB / PofBernie;
		PofAgegB = PofAgegB / PofBernie;
		PofGengB = PofGengB / PofBernie;
		PofRacgB = PofRacgB / PofBernie;
		PofBacgB = PofBacgB / PofBernie;
		
		PofHillary = PofHillary / columnList.size();
		PofBernie = PofBernie / columnList.size();
		
		System.out.println(PofPopgH);
		
	}
	
	/*
	 * Calculate posterior probability 
	 */
	private void posteriorProbability()
	{
		//probability of Hillary winning given increasing population, high % of young children, high % of female, high % of racial minorities, high % of Educated people
		double postHillary = 0;
		double postBernie = 0;
		postHillary = (PofHillary * PofPopgH * PofAgegH * PofGengH * PofRacgH * PofBacgH); 
		//postHillary = (PofHillary * PofGengH * PofRacgH);
		System.out.println("Posterior Hillary:"+ postHillary);
		postBernie = (PofBernie * PofPopgB * PofAgegB * PofGengB * PofRacgB * PofBacgB); 
		//postBernie = (PofBernie * PofGengB * PofRacgB);
		System.out.println("Posterior Bernie:"+ postBernie);
	}
	
	/*
	 * Test function 
	 */
	public static void main(String[] args)
	{
		NaiveBayesianClassifier nbc = new NaiveBayesianClassifier();
		nbc.readFile();
		nbc.calculateConditionalProbability();
		nbc.posteriorProbability();
		/*for(ArrayList<String> row : nbc.columnList)
		{
			System.out.println("");
			for(String st : row)
			{
				System.out.print(st);
				System.out.print(",");
			}
			System.out.println("");
		}
		System.out.println(nbc.columnList.size());*/
		
		
	}
}
