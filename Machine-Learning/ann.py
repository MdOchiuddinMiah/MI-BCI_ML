from sklearn.neural_network import MLPClassifier
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import classification_report,confusion_matrix
from sklearn.metrics import roc_curve
from sklearn.metrics import roc_auc_score
import matplotlib.pyplot as plt

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

mlp = MLPClassifier(random_state=42)
print('ok')
mlp.fit(X_train, y_train)
print('ok')

#print('Accuracy of the training set: {:.2f}'.format(mlp.score(x_train,y_train)*100)+ ' %')
#print('Accuracy of the test set: {:.2f}'.format(mlp.score(x_test,y_test)*100)+' %')
#print('Accuracy on the training subset: {:.3f}'.format(mlp.score(X_train, y_train)))
print('ok')
#print('Accuracy on the test subset: {:.3f}'.format(mlp.score(X_test, y_test)))
predicted=mlp.predict(X_test)
print('completed')
#confusion=confusion_matrix(y_test, predicted, labels=["lefthand", "steady", "righthand"])
#confusion=confusion_matrix(y_test, predicted, labels=["steady", "righthand"])
#print(confusion)

print(classification_report(y_test, predicted))

print('start roc')
probs = mlp.predict_proba(X_test)
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

#probs = mlp.predict_proba(X_test)
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