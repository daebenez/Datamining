# -*- coding: utf-8 -*-
"""
Spyder Editor
Author Kushal
This is a temporary script file.
"""

import pandas as pd
import math

def data1_extract1(path,filename,ext,encoding="iso-8859-1"):
    
    data_1 = pd.read_csv(path+filename+ext,encoding=encoding,header=0)
    return data_1

def data2_extract2(path,filename,ext,encoding="iso-8859-1"):
    
    data_2 = pd.read_csv(path+filename+ext,encoding=encoding,header= 0)
    return data_2
    
def main():
     
    data1 = data1_extract1("./","2972_rows_csvfile_edit",".csv")
    data2 = data2_extract2("./","172_rows_csvfile_edit",".csv")
  
    result = []
    #count variable will count that how many calculations are done
    count = 0
    #outer for loop will select the rows from 2972_rows_csvfile_edit dataset
    for i in range(len(data1)):
        interim = []
        count = count + 1
    #inner for loop will select the rows from 172_rows_csvfile_edit dataset
        for j in range (len(data2)):
            sum = 0
    #innermost loop will select each attribute
            for k in range(0,29):
                sum = math.pow(data2.loc[j][k] - data1.loc[i][k],2) + sum
            e_dist = round (1/(1 + math.sqrt(sum)),8)
            interim.append(e_dist)
        result.append(interim)
        print(count)

    #making a output file to store the entire matrix
    output = open("./similar_matrix.csv","w")    
        
    for each_item in result:
        for single_item in each_item[:-1]:
            output.write(str(single_item)+",")
        output.write(str(each_item[-1])+"\n")
    
    output.close()
    
if __name__ == "__main__":
    main()