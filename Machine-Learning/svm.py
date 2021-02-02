# -*- coding: utf-8 -*-
"""
Created on Mon May 27 23:14:24 2019

@author: omi
"""
from sklearn import svm
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import classification_report,confusion_matrix

eeg_data=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\2_3_class_data\\2omitrain.csv")
eeg_test_data=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\2_3_class_data\\2omitest.csv")
eeg_data.head()
featute_col=['Theta','Alpha','Low_beta','High_beta','Gamma']
x=eeg_data[featute_col]
y=eeg_data['Class']
y.head()

X_train=eeg_data[featute_col]
y_train=eeg_data['Class']

X_test=eeg_test_data[featute_col]
y_test=eeg_test_data['Class']
#X_train, X_test, y_train, y_test = train_test_split(x,y, random_state=0)
#scaler = StandardScaler()
#X_train_scaled = scaler.fit(X_train).transform(X_train)
#X_test_scaled = scaler.fit(X_test).transform(X_test)
#print(X_train)
#print(X_test)
#print(y_train)
#print(y_test)
print('ok')

#mlp = MLPClassifier(hidden_layer_sizes=(50,40,30),max_iter=1000,random_state=42)
#mlp = MLPClassifier(max_iter=1000,alpha=1,random_state=42)

sv = svm.SVC(kernel='linear')
print('ok')
sv.fit(X_train, y_train)
print('ok')

#print('Accuracy of the training set: {:.2f}'.format(mlp.score(x_train,y_train)*100)+ ' %')
#print('Accuracy of the test set: {:.2f}'.format(mlp.score(x_test,y_test)*100)+' %')
#print('Accuracy on the training subset: {:.3f}'.format(sv.score(X_train, y_train)))
#print('ok')
print('Accuracy on the test subset: {:.3f}'.format(sv.score(X_test, y_test)))
predicted=sv.predict(X_test)
print('completed')
#confusion=confusion_matrix(y_test, predicted, labels=["lefthand", "steady", "righthand"])
confusion=confusion_matrix(y_test, predicted, labels=["steady", "righthand"])
print(confusion)

print(classification_report(y_test, predicted))
