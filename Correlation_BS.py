from scipy.stats.stats import pearsonr
import matplotlib.pyplot as plt
import numpy as np
import csv

f = open('/VAISHALI/IUB/_Semesters_/Semester 2/Data Mining/VAISHALI/primary_results.csv')

f1 = open('/VAISHALI/IUB/_Semesters_/Semester 2/Data Mining/VAISHALI/CountyRank.csv')

f2 = open('/VAISHALI/IUB/_Semesters_/Semester 2/Data Mining/VAISHALI/primary_results.csv')


'''
disctionary with key as one ciounty and value is a list of all closest counties to it
This is done for each county
'''

closest_counties = {}

for line in f1.readlines():
    f1, f2, f3 = line.split(',')
    f1= f1.strip()
    f2=f2.strip()
    if closest_counties.has_key(f1):
        closest_counties[f1].append(f2)
    else:
        closest_counties[f1] = [f2]

'''
Finding the different counties won by each candidate
'''

counties = {}
candidates = {}

for line in f.readlines():
    c1, c2, c3, c4, c5, c6, c7, c8 = line.split(',')
    c3 = c3.strip()
    c6 = c6.strip()
    if counties.has_key(c6):
        counties[c6].append(c3)
    else:
        counties[c6] = [c3]
f.close() 


f = open('/VAISHALI/IUB/_Semesters_/Semester 2/Data Mining/VAISHALI/primary_results.csv')


'''
finding different candidates who participates in each county
'''

for line in f.readlines():
    c1, c2, c3, c4, c5, c6, c7, c8 = line.split(',')
    c3 = c3.strip()
    c6 = c6.strip()
    if candidates.has_key(c3):
        candidates[c3].append(c6)
    else:
        candidates[c3] = [c6]
        
f.close()

'''
function to remove all duplicate counties
'''

def remove_duplicates(lst):
    lst.sort()
    i = len(lst) - 1
    while i > 0:  
        if lst[i] == lst[i - 1]:
            lst.pop(i)
        i -= 1
    return lst

'''
Number of counties won by Hillary Clinton and Bernie Sanders
'''
counties_won_by_hillary_clinton = []
counties_won_by_bernie_sanders = []

for key in counties:
    if key == 'Hillary Clinton':
        counties_won_by_hillary_clinton.append(counties[key])
    elif key == 'Bernie Sanders':
        counties_won_by_bernie_sanders.append(counties[key])
        
len1 = len(counties_won_by_hillary_clinton[0])
len2 = len(counties_won_by_bernie_sanders[0])

'''
Finding closest counties to counties won by Hillary Clinton and Bernie Sanders
And, finding candidates who won in those counties
'''

lst = []
county_candidate = {}

for i in range(0,len2):
    for key in closest_counties:
        if counties_won_by_bernie_sanders[0][i] == key:
            lst.append(closest_counties[key])
            for j in range(0,len(lst[0])):
                for key in candidates:
                    if lst[0][j] == key:
                        if county_candidate.has_key(key):
                            county_candidate[key].append(remove_duplicates(candidates[key]))
                        else:
                            county_candidate[key] = remove_duplicates(candidates[key])
            lst = []


'''
Finding the number of counties won by candidates obtained above
'''
dic = {}


for key in county_candidate:
    for i in range(1,len(county_candidate[key])-1):
        if dic.has_key(county_candidate[key][i]):
            dic[county_candidate[key][i]].append([key])
        else:
            dic[county_candidate[key][i]] = [key]

candidate_county_count = {}            
for key in dic:
    if candidate_county_count.has_key(key):
        candidate_county_count[key].append(len(dic[key]))
    else:
        candidate_county_count[key] = len(dic[key])
        
for key in candidate_county_count:
    print key, candidate_county_count[key]

'''
Finding candidate who is most correlated and second most correlated to Bernie Sanders
'''

number_counties = []

for key in candidate_county_count:
    if key == 'Bernie Sanders':
        tempo = candidate_county_count[key]

for key in candidate_county_count:
    number_counties.append(abs(tempo - candidate_county_count[key]))


def minimum(temp_lst):
    minimum = min(temp_lst)
    temp = 0
    for i in range(0,len(temp_lst)):
        if temp_lst[i] == minimum:
            temp = i
    return temp

del number_counties[minimum(number_counties)]
minimum2 = min(number_counties)

candidate = []
for key in candidate_county_count:
    if abs(tempo - candidate_county_count[key]) == minimum2:
        candidate.append(key)

print "The most correlated candidate to Bernie Sanders is %s" %(candidate)
print

candidate2 = []
del number_counties[minimum(number_counties)]
minimum3 = min(number_counties)

for key in candidate_county_count:
    if abs(tempo - candidate_county_count[key]) == minimum3:
        candidate2.append(key)

print "The second most correlated candidate to Bernie Sanders is %s" %(candidate2)
print


'''
Drawing BarPlots for candidates correlated with Hillary Clinton 
'''

visual = []

for key in candidate_county_count:
    if key == 'Bernie Sanders':
        visual.append(candidate_county_count[key])
        
for key in candidate_county_count:
    if key == 'Marco Rubio':
        visual.append(candidate_county_count[key])


plt.figure()
plt.boxplot(visual)
plt.xlabel('Candidate Who Is Most Correlated With Bernie Sanders')
plt.ylabel('Number of Counties Candidates Participated In')
plt.xticks([1], ['Marco Rubio'])
plt.show()

visual2 = []

for key in candidate_county_count:
    if key == 'Bernie Sanders':
        visual2.append(candidate_county_count[key])
        
for key in candidate_county_count:
    if key == 'Donald Trump':
        visual2.append(candidate_county_count[key])


plt.figure()
plt.boxplot(visual2)
plt.xlabel('Candidate Who Is Most Correlated With Bernie Sanders')
plt.ylabel('Number of Counties Candidates Participated In')
plt.xticks([1], ['Donald Trump'])
plt.show()













