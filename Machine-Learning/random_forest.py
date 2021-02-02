# -*- coding: utf-8 -*-
"""
Created on Sat Dec 22 22:10:00 2018

@author: omi
"""

from sklearn.ensemble import RandomForestClassifier
import pandas as pd
from sklearn.metrics import classification_report,confusion_matrix
from sklearn.metrics import roc_curve
from sklearn.metrics import roc_auc_score
from sklearn.preprocessing import label_binarize
import matplotlib.pyplot as plt

eeg_data=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\2_3_class_data\\2omitrain.csv")
eeg_test_data=pd.read_csv("E:\\Research\\Ensemble DT in BMI\\2_3_class_data\\2omitest.csv")

#eeg_data=pd.read_csv("E:\\Research\\ensemble classifier in BMI\\sensors data\\3FC5_train.csv")
#eeg_test_data=pd.read_csv("E:\\Research\\ensemble classifier in BMI\\sensors data\\3FC5_test.csv")

eeg_data.head()
featute_col=['Theta','Alpha','Low_beta','High_beta','Gamma']

X_train=eeg_data[featute_col]
y_train=eeg_data['Class']

X_test=eeg_test_data[featute_col]
y_test=eeg_test_data['Class']

print('ok')

clf = RandomForestClassifier()
clf = clf.fit(X_train, y_train)

#print('Accuracy on the test subset: {:.3f}'.format(clf.score(X_test, y_test)))

predicted=clf.predict(X_test)
print('completed')
#confusion=confusion_matrix(y_test, predicted, labels=["lefthand", "righthand","steady"])
confusion=confusion_matrix(y_test, predicted, labels=["steady", "righthand"])
print(confusion)



print(classification_report(y_test, predicted))

print(predicted)



probs = clf.predict_proba(X_test)
probs = probs[:, 1]
# calculate AUC
# Binarize the output
y=y_test
y=y.replace(['righthand'],0)
y=y.replace(['steady'],1)
auc_random = roc_auc_score(y, probs)
print('AUC: %.3f' % auc_random)
# calculate roc curve
fpr_random, tpr_random, thresholds_random = roc_curve(y, probs)

plt.figure()
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.plot([0, 1], [0, 1], color='navy', linestyle='--')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.title('SVM Classifier ROC')
plt.plot(fpr_random, tpr_random, color='blue', lw=2, label='AUC = %0.2f)' % auc_random)
plt.legend(loc="lower right")
plt.show()


#probs = clf.predict_proba(X_test)
#probs = probs[:, 1]
## calculate AUC
## Binarize the output
#y = y_test
#y=y.replace(['righthand'],1)
#y=y.replace(['steady'],0)
#y=y.replace(['lefthand'],0)
#auc = roc_auc_score(y, probs)
#print('AUC: %.3f' % auc)
## calculate roc curve
#fpr, tpr, thresholds = roc_curve(y, probs)
#
#plt.figure()
#plt.xlabel('False Positive Rate')
#plt.ylabel('True Positive Rate')
#plt.plot([0, 1], [0, 1], color='navy', linestyle='--')
#plt.xlim([0.0, 1.0])
#plt.ylim([0.0, 1.05])
#plt.title('SVM Classifier ROC')
#plt.plot(fpr, tpr, color='blue', lw=2, label='AUC = %0.2f)' % auc)
#plt.legend(loc="lower right")
#plt.show()

print('end roc')