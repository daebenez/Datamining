package com.datamining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/*
 * @author David Ebenezer
 * Program that finds k closest county for each county 
 * Task 2 : Correlation between Candidates
 */

public class ClosestCounty {
	ArrayList<ArrayList<String>> columnList = new ArrayList<ArrayList<String>>();
	ArrayList<String> rowList = null;
	ArrayList<String> sorter = new ArrayList<String>();
	int columnNum = 0;
	
	/*
	 * Reads all the data from file and uploads into arraylist of arrayList.
	 * Input Cleaned county demographic data : DATA_ClEANING.csv
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
	
	/*
	 * Compute distance between 2 counties and find the closest k counties for every county
	 * eliminate duplicates using HashMap
	 */
	private void computeDistance()
	{
		double temp = 0;
		double sum = 0;
		for(ArrayList<String> CountyOne : columnList)
		{
			HashMap<ArrayList<String>,Double> duplicates = new HashMap<ArrayList<String>,Double>();
			for(ArrayList<String> CountyTwo : columnList)
			{
				temp = 0;
				if(!CountyOne.equals(CountyTwo))
				{
					sorter = new ArrayList<String>();
					for(int i = 3;i < CountyOne.size();i++)
					{
						temp = Double.parseDouble(CountyOne.get(i)) - Double.parseDouble(CountyTwo.get(i));
						temp = temp * temp;
						sum = sum + temp;
					}
					sum = Math.sqrt(sum);
					sorter.add(CountyOne.get(1));
					sorter.add(CountyTwo.get(1));
					Collections.sort(sorter);
					duplicates.put(sorter, sum);
					//System.out.println("Result:"+CountyOne.get(1)+","+CountyTwo.get(1)+","+sum);
				}
			}
			rankCounties(duplicates);
			
		}
		
	}
	
	/*
	 * Method to rank counties by distance using Treeset and extract k closest counties
	 */
	private void rankCounties(HashMap<ArrayList<String>,Double> duplicates)
	{
		TreeMap<Double,ArrayList<String>> rank = new TreeMap<Double,ArrayList<String>>();
		 for (Map.Entry<ArrayList<String>,Double> entry : duplicates.entrySet()) {
		  rank.put(entry.getValue(), entry.getKey());
		}
		 writeToFile(rank);
	}
	
	/*
	 * Write output to file CountyRank.csv
	 */
	private void writeToFile(TreeMap<Double,ArrayList<String>> rank)
	{
		int max = 4;
		String outFile = "C:/Users/User_David/Documents/IU Academics/Spring 2016/Datamining/Output/CountyRank.csv";
		try
		{
			FileWriter writer = new FileWriter(outFile,true);
			
			for(Map.Entry<Double,ArrayList<String>> entry : rank.entrySet()) {
				if(max == 0){ break; }
				  //writer.append(String.valueOf(entry.getValue()));
				  ArrayList<String> vla = entry.getValue();
				  for(String nme : vla)
				  {
					  writer.append(String.valueOf(nme));
					  writer.append(',');
				  }
				  //writer.append(',');
				  writer.append(String.valueOf(entry.getKey()));
				  writer.append('\n');
				  max--;
				}
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	/*
	 * Test function
	 */
	public static void main(String[] args)
	{
		ClosestCounty cl = new ClosestCounty();
		cl.readFile();
		/*for(ArrayList<String> row : cl.columnList)
		{
			System.out.println("");
			for(String st : row)
			{
				System.out.print(st);
				System.out.print(",");
			}
			System.out.println("");
		}*/
		cl.computeDistance();
		/*Iterator<Map.Entry<ArrayList<String>,Double>> it = cl.duplicates.entrySet().iterator() ;
		   while (it.hasNext())
		   {
			   System.out.print(it.next().getKey());
			   System.out.print(it.next().getValue());
			   System.out.println();
		     
		   }*/
		//cl.rankCounties();
		//cl.writeToFile();
		//System.out.println(cl.duplicates.size());
		
	}

}
