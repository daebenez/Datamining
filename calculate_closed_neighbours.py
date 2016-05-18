# -*- coding: utf-8 -*-
"""
Spyder Editor
Author Kushal
This is a temporary script file.
"""

import pandas as pd
import math

def data1_extract1(path,filename,ext,encoding="iso-8859-1"):
    
    dataset = pd.read_csv(path+filename+ext,encoding=encoding,header=-1)
    return dataset

    
def main():
     
    data_set = data1_extract1("./","similar_matrix",".csv")
    #initializing a haspmap with 172 keys with an 0
    result = {}
    for i in range (0, 172):
        result[i] = 0
    print(result)
            
    #filling the values in hashmap on basis of max closest counties values        
    for i in range(len(data_set)):
        max = 0
        for j in range (0, 172):
            if(max < data_set.loc[i][j]):
                max = data_set.loc[i][j]
                max_index = j
        result[max_index] += 1        
    print(result)
                
if __name__ == "__main__":
    main()