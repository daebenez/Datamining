/**
 * @author  David Ebenezer
 * Program to perform Data Conversion 
 * Task 3 : Classification 
 */

package com.datamining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class DatasetConversion {
	
	ArrayList<ArrayList<String>> columnListDS = new ArrayList<ArrayList<String>>();
	ArrayList<String> rowListDS = null;
	ArrayList<ArrayList<String>> columnListFinal = new ArrayList<ArrayList<String>>();
	ArrayList<String> rowListFinal = null;
	int columnNumDS = 0,columnNumFinal = 0;
	int columnNum = 0;
	ArrayList<ArrayList<String>> columnList = new ArrayList<ArrayList<String>>();
	ArrayList<String> rowList = null;
	double age = 0, gender = 0,race = 0, education = 0;
	
	/**
	 * Method to read the results dataset and populate results in an arraylist of arraylists
	 * Input File : Primary_results.csv
	 */
	private void readResults()
	{
		String FileUrl = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Final Project dataset/2016_presidential_election/primary_results.csv";
		BufferedReader br = null;
		String line = "";
		
		try{
			br = new BufferedReader(new FileReader(FileUrl));
			while ((line = br.readLine()) != null) {
				rowListDS = new ArrayList<String>();
				String[] eachLine = line.split(",");
				columnNumDS = rowListDS.size();
				
				// if pincode is 4 numbers else if its 5 numbers
				for(int i=0;i<eachLine.length;i++)
				{	
					rowListDS.add(eachLine[i]);
				}
				columnListDS.add(rowListDS);
			}
			columnListDS.remove(0);
			
		}
		 catch (FileNotFoundException e) {
				System.out.println("The input csv file you specified was not found at the path: "+FileUrl);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to write intermediate output to file with all relevant features extracted from results.csv and county_facts.csv
	 * Output File : NBInput.csv
	 */
	private void writeToFile()
	{
		String outFile = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Output/NBInput.csv";
		try
		{
			FileWriter writer = new FileWriter(outFile,true);
			for(ArrayList<String> al : columnListDS)
			{
			 for(ArrayList<String> lg : columnList)
			 {	
				if(lg.get(0).equals(al.get(3)))
				{
					if(al.get(5).equals("Hillary Clinton"))
					{
						// fips
						writer.append(al.get(3));
						writer.append(',');
						//county name
						//writer.append(al.get(2));
						writer.append(lg.get(1));
						writer.append(',');
						// Winner
						if(Double.parseDouble(al.get(7)) > 0.5)
						{
							writer.append("Hillary Clinton");
						}
						else if(Double.parseDouble(al.get(7)) == 0.5)
						{
							writer.append("Tie");
						}
						else if(Double.parseDouble(al.get(7)) < 0.5)
						{
							writer.append("Bernie Sanders");
						}
						writer.append(',');
						// Population growth percentage
						writer.append(lg.get(5));
						writer.append(',');
						// % children
						writer.append(lg.get(7));
						writer.append(',');
						// % female
						writer.append(lg.get(10));
						writer.append(',');
						// % white
						writer.append(lg.get(11));
						writer.append(',');
						// % with bachelors degree
						writer.append(lg.get(22));
						
						writer.append('\n');
						
					}
					
				}
				
				
			  }
			}
			
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Read Intermediate File  and load to arraylist of arraylists
	 */
	private void writeOut()
	{
		String FileUrl = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Output/NBInput.csv";
		BufferedReader br = null;
		String line = "";
		
		try{
			br = new BufferedReader(new FileReader(FileUrl));
			while ((line = br.readLine()) != null) {
				rowListFinal = new ArrayList<String>();
				String[] eachLine = line.split(",");
				columnNumFinal = rowListFinal.size();
				
				for(int i=0;i<eachLine.length;i++)
				{	
					rowListFinal.add(eachLine[i]);
				}
				columnListFinal.add(rowListFinal);
			}
			
		}
		 catch (FileNotFoundException e) {
				System.out.println("The input csv file you specified was not found at the path: "+FileUrl);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * read data_cleaning.csv file to arraylist of arrayLists
	 * @param args
	 */
	private void readFile()
	{
		String FileUrl = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Clean/DATA_CLEANING.csv";
		BufferedReader br = null;
		String line = "";
		try{
			br = new BufferedReader(new FileReader(FileUrl));
			while ((line = br.readLine()) != null) {
				rowList = new ArrayList<String>();
				String[] eachLine = line.split(",");
				String[] pincode = eachLine[0].split("(?!^)");
				columnNum = rowList.size();
				columnList.add(rowList);
				// if pincode is 4 numbers else if its 5 numbers
				if(pincode.length == 4)
				{
					//check if last 4 digits is 0
					if(pincode[1].equals("0")&& pincode[2].equals("0")&& pincode[3].equals("0"))
					{
						columnList.remove(rowList);
					}
				}
				else if(pincode.length == 5)
				{
					if(pincode[4].equals("0")&& pincode[2].equals("0")&& pincode[3].equals("0"))
					{
						columnList.remove(rowList);
					}
				}
				for(int i=0;i<eachLine.length;i++)
				{	
					rowList.add(eachLine[i]);
				}
			}
			columnList.remove(0);
			columnList.remove(0);
			
		}
		 catch (FileNotFoundException e) {
				System.out.println("The input csv file you specified was not found at the path: "+FileUrl);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Find Median : Calculate median for 4 numerical features Age, Gender, Race, Education
	 */
	private void findMedian()
	{
		int size = 0;
		ArrayList<Double> ageMedian = new ArrayList<Double>();
		ArrayList<Double> genderMedian = new ArrayList<Double>();
		ArrayList<Double> raceMedian = new ArrayList<Double>();
		ArrayList<Double> educationMedian = new ArrayList<Double>();
		for(ArrayList<String> item : columnListFinal)
		{
			ageMedian.add(Double.parseDouble(item.get(4)));
			genderMedian.add(Double.parseDouble(item.get(5)));
			raceMedian.add(Double.parseDouble(item.get(6)));
			educationMedian.add(Double.parseDouble(item.get(7)));
		}
		Collections.sort(ageMedian);
		size = ageMedian.size();
		age = ageMedian.get(size/2);
		gender = genderMedian.get(size/2);
		race = raceMedian.get(size/2);
		education = educationMedian.get(size/2);
		/*System.out.println("Stats");
		System.out.println(age);
		System.out.println(gender);
		System.out.println(race);
		System.out.println(education);*/
	}
	
	/**
	 * Create the final NBFinal.csv file
	 * @param args
	 */
	private void writeFinal()
	{
		String outFile = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Output/NBFinal.csv";
			try
			{
				FileWriter writer = new FileWriter(outFile,true);
				for(ArrayList<String> tl : columnListFinal)
				{	
					
					// fips
					writer.append(tl.get(0));
					writer.append(',');
					//county name
					writer.append(tl.get(1));
					writer.append(',');
					//Winner
					writer.append(tl.get(2));
					writer.append(',');
					//Population
					if(Double.parseDouble(tl.get(3))<=0)
					{
					  writer.append("shrank");
					  writer.append(',');
					}
					else
					{
						writer.append("increased");
						writer.append(',');
					}
					//Under 18
					if(Double.parseDouble(tl.get(4))<age)
					{
						writer.append("low");
						writer.append(',');
					}
					else
					{
						writer.append("high");
						writer.append(',');
					}
					//Gender
					if(Double.parseDouble(tl.get(5))<gender)
					{
						writer.append("low");
						writer.append(',');
					}
					else
					{
						writer.append("high");
						writer.append(',');
					}
					//Race
					if(Double.parseDouble(tl.get(6))<race)
					{
						writer.append("high");
						writer.append(',');
					}
					else
					{
						writer.append("low");
						writer.append(',');
					}
					//Education Bachelors
					if(Double.parseDouble(tl.get(7))<education)
					{
						writer.append("low");
						writer.append(',');
					}
					else
					{
						writer.append("high");
						writer.append(',');
					}
					 writer.append('\n');
					
		}
				writer.flush();
				writer.close();
	}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * Test functions
	 */
	public static void main(String[] args)
	{
		DatasetConversion dc = new DatasetConversion();
		dc.readResults();
		dc.readFile();
		dc.writeToFile();
		dc.writeOut();
		dc.findMedian();
		dc.writeFinal();
		/*for(ArrayList<String> row : dc.columnListDS)
		{
			System.out.println("");
			for(String st : row)
			{
				System.out.print(st);
				System.out.print(",");
			}
			System.out.println("");
		}
		System.out.println(dc.columnListDS.size());*/
		
		  /*for(ArrayList<String> row : dc.columnListFinal)
		{
			System.out.println("");
			for(String st : row)
			{
				System.out.print(st);
				System.out.print(",");
			}
			System.out.println("");
		}
		System.out.println(dc.columnListFinal.size());*/
	}

}
