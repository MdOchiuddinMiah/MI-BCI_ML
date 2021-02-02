# -*- coding: utf-8 -*-
"""
Created on Sat Dec 22 14:01:47 2018

@author: omi
"""
import pandas as pd

eeg_data=pd.read_csv("E:\\Research\\ensemble classifier in BMI\\2_3_class_data\\3omitrain.csv")
eeg_test_data=pd.read_csv("E:\\Research\ensemble classifier in BMI\2_3_class_data\\3omitest.csv")
eeg_data.head()
featute_col=['Theta','Alpha','Low_beta','High_beta','Gamma','Class']
x=eeg_data[featute_col]
y=eeg_data['Class']
## training data
lefthand_data=eeg_data.query('Class=="lefthand"')
lefthand_data.to_csv('3lefthanddata_train.csv')
righthand_data=eeg_data.query('Class=="righthand"')
righthand_data.to_csv('3righthanddata_train.csv')
steady_data=eeg_data.query('Class=="steady"')
steady_data.to_csv('3steadydata_train.csv')

lefthand_data=pd.read_csv("F:\\Python scikit learner\\3lefthanddata_train.csv")
righthand_data=pd.read_csv("F:\\Python scikit learner\\3righthanddata_train.csv")
steady_data=pd.read_csv("F:\\Python scikit learner\\3steadydata_train.csv")
righthand_data=righthand_data[featute_col]
steady_data=steady_data[featute_col]

lefthand_FC5=lefthand_data.query('Id%4==0')
lefthand_F3=lefthand_data.query('Id%4==1')
lefthand_F4=lefthand_data.query('Id%4==2')
lefthand_FC6=lefthand_data.query('Id%4==3')

righthand_FC5=righthand_data.query('Id%4==0')
righthand_F3=righthand_data.query('Id%4==1')
righthand_F4=righthand_data.query('Id%4==2')
righthand_FC6=righthand_data.query('Id%4==3')

steady_FC5=steady_data.query('Id%4==0')
steady_F3=steady_data.query('Id%4==1')
steady_F4=steady_data.query('Id%4==2')
steady_FC6=steady_data.query('Id%4==3')

FC5=lefthand_FC5
FC5=FC5.append(righthand_FC5)
FC5=FC5.append(steady_FC5)
FC5=FC5[featute_col]
FC5.to_csv('3FC5_train.csv')

F3=lefthand_F3
F3=F3.append(righthand_F3)
F3=F3.append(steady_F3)
F3=F3[featute_col]
F3.to_csv('3F3_train.csv')

F4=lefthand_F4
F4=F4.append(righthand_F4)
F4=F4.append(steady_F4)
F4=F4[featute_col]
F4.to_csv('3F4_train.csv')

FC6=lefthand_FC6
FC6=FC6.append(righthand_FC6)
FC6=FC6.append(steady_FC6)
FC6=FC6[featute_col]
FC6.to_csv('3FC6_train.csv')


## test data
lefthand_data=eeg_test_data.query('Class=="lefthand"')
lefthand_data.to_csv('3lefthanddata_test.csv')
righthand_data=eeg_test_data.query('Class=="righthand"')
righthand_data.to_csv('3righthanddata_test.csv')
steady_data=eeg_test_data.query('Class=="steady"')
steady_data.to_csv('3steadydata_test.csv')

lefthand_data=pd.read_csv("F:\\Python scikit learner\\3lefthanddata_test.csv")
righthand_data=pd.read_csv("F:\\Python scikit learner\\3righthanddata_test.csv")
steady_data=pd.read_csv("F:\\Python scikit learner\\3steadydata_test.csv")
righthand_data=righthand_data[featute_col]
steady_data=steady_data[featute_col]

lefthand_FC5=lefthand_data.query('Id%4==0')
lefthand_F3=lefthand_data.query('Id%4==1')
lefthand_F4=lefthand_data.query('Id%4==2')
lefthand_FC6=lefthand_data.query('Id%4==3')

righthand_FC5=righthand_data.query('Id%4==0')
righthand_F3=righthand_data.query('Id%4==1')
righthand_F4=righthand_data.query('Id%4==2')
righthand_FC6=righthand_data.query('Id%4==3')

steady_FC5=steady_data.query('Id%4==0')
steady_F3=steady_data.query('Id%4==1')
steady_F4=steady_data.query('Id%4==2')
steady_FC6=steady_data.query('Id%4==3')

FC5=lefthand_FC5
FC5=FC5.append(righthand_FC5)
FC5=FC5.append(steady_FC5)
FC5=FC5[featute_col]
FC5.to_csv('3FC5_test.csv')

F3=lefthand_F3
F3=F3.append(righthand_F3)
F3=F3.append(steady_F3)
F3=F3[featute_col]
F3.to_csv('3F3_test.csv')

F4=lefthand_F4
F4=F4.append(righthand_F4)
F4=F4.append(steady_F4)
F4=F4[featute_col]
F4.to_csv('3F4_test.csv')

FC6=lefthand_FC6
FC6=FC6.append(righthand_FC6)
FC6=FC6.append(steady_FC6)
FC6=FC6[featute_col]
FC6.to_csv('3FC6_test.csv')