# -*- coding: utf-8 -*-
"""
Created on Sat Dec 22 20:35:08 2018

@author: omi
"""
from sklearn import tree
from sklearn.naive_bayes import GaussianNB
from sklearn.naive_bayes import MultinomialNB
import pandas as pd
from sklearn.metrics import classification_report,confusion_matrix
from sklearn.metrics import roc_curve
from sklearn.metrics import roc_auc_score
from sklearn.preprocessing import label_binarize
import matplotlib.pyplot as plt

featute_col=['Theta','Alpha','Low_beta','High_beta','Gamma']

# FC5 location
eeg_3FC5_train=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3FC5_train.csv")
eeg_3FC5_test=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3FC5_test.csv")

X_train_3FC5=eeg_3FC5_train[featute_col]
y_train_3FC5=eeg_3FC5_train['Class']

X_test_3FC5=eeg_3FC5_test[featute_col]
y_test_3FC5=eeg_3FC5_test['Class']

M_FC5 =tree.DecisionTreeClassifier()
M_FC5 = M_FC5.fit(X_train_3FC5, y_train_3FC5)

print('Accuracy on the FC5: {:.3f}'.format(M_FC5.score(X_test_3FC5, y_test_3FC5)))

predicted_FC5=M_FC5.predict(X_test_3FC5)
print('completed FC5')


# F3 location
eeg_3F3_train=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3F3_train.csv")
eeg_3F3_test=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3F3_test.csv")

X_train_3F3=eeg_3F3_train[featute_col]
y_train_3F3=eeg_3F3_train['Class']

X_test_3F3=eeg_3F3_test[featute_col]
y_test_3F3=eeg_3F3_test['Class']

M_F3 =tree.DecisionTreeClassifier()
M_F3 = M_F3.fit(X_train_3F3, y_train_3F3)

print('Accuracy on the F3: {:.3f}'.format(M_F3.score(X_test_3F3, y_test_3F3)))

predicted_F3=M_F3.predict(X_test_3F3)
print('completed F3')

# F4 location
eeg_3F4_train=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3F4_train.csv")
eeg_3F4_test=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3F4_test.csv")

X_train_3F4=eeg_3F4_train[featute_col]
y_train_3F4=eeg_3F4_train['Class']

X_test_3F4=eeg_3F4_test[featute_col]
y_test_3F4=eeg_3F4_test['Class']

M_F4 =tree.DecisionTreeClassifier()
M_F4 = M_F4.fit(X_train_3F4, y_train_3F4)

print('Accuracy on the F4: {:.3f}'.format(M_F4.score(X_test_3F4, y_test_3F4)))

predicted_F4=M_F4.predict(X_test_3F4)
print('completed F4')

# FC6 location
eeg_3FC6_train=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3FC6_train.csv")
eeg_3FC6_test=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\sensors data\\3class\\3FC6_test.csv")

X_train_3FC6=eeg_3FC6_train[featute_col]
y_train_3FC6=eeg_3FC6_train['Class']

X_test_3FC6=eeg_3FC6_test[featute_col]
y_test_3FC6=eeg_3FC6_test['Class']

M_FC6 =tree.DecisionTreeClassifier()
M_FC6 = M_FC6.fit(X_train_3FC6, y_train_3FC6)

print('Accuracy on the FC6: {:.3f}'.format(M_FC6.score(X_test_3FC6, y_test_3FC6)))

predicted_FC6=M_FC6.predict(X_test_3FC6)
print('completed FC6')

probs = M_F4.predict_proba(X_test_3F4)
probs = probs[:, 1]
# calculate AUC
# Binarize the output
y = y_test_3F4
y=y.replace(['righthand'],1)
y=y.replace(['steady'],0)
y=y.replace(['lefthand'],0)
auc = roc_auc_score(y, probs)
print('AUC: %.3f' % auc)
# calculate roc curve
fpr, tpr, thresholds = roc_curve(y, probs)

plt.figure()
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.plot([0, 1], [0, 1], color='navy', linestyle='--')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.title('SVM Classifier ROC')
plt.plot(fpr, tpr, color='blue', lw=2, label='AUC = %0.2f)' % auc)
plt.legend(loc="lower right")
plt.show()

print('ok')

    
count=0
i=0
while i<31122:
    if y_test_3FC5[i]==y_test_3F3[i] and y_test_3F3[i]==y_test_3F4[i] and y_test_3F4[i]==y_test_3FC6[i]:
        count+=1
    else:
        print('not equal')
    i+=1 
  
print(count)


final_predict=[]
count=0
i=0  
while i<31122:
    lefthand=0
    righthand=0
    steady=0
    final_state=''
    if predicted_FC5[i]=='lefthand':
        lefthand+=1
    elif predicted_FC5[i]=='righthand':
        righthand+=1
    else:
        steady+=1
        
    if predicted_F3[i]=='lefthand':
        lefthand+=1
    elif predicted_F3[i]=='righthand':
        righthand+=1
    else:
        steady+=1
        
    if predicted_F4[i]=='lefthand':
        lefthand+=1
    elif predicted_F4[i]=='righthand':
        righthand+=1
    else:
        steady+=1
        
    if predicted_FC6[i]=='lefthand':
        lefthand+=1
    elif  predicted_FC6[i]=='righthand':
        righthand+=1
    else:
        steady+=1
        
    if lefthand>righthand and lefthand>steady:
        final_state='lefthand'
    elif righthand> lefthand and righthand>steady:
        final_state='righthand'
    else:
        final_state='steady'
    
    if y_test_3FC5[i]==final_state:
        count+=1
        
    final_predict.append(final_state)    
        
    i+=1

print(count)
print('final accuracy:')
print(count/31122)

confusion=confusion_matrix(y_test_3FC5, final_predict, labels=["lefthand", "steady", "righthand"])
print(confusion)

print(classification_report(y_test_3FC5, final_predict))