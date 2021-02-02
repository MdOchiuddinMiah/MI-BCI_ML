# CluSem: accurate clustering-based Ensemble Method to predict Motor Imagery tasks from multi-channel EEG data
##### Authors: Md Ochiuddin Miah, Rafsanjani Muhammod, Khondaker Abdullah Al Mamun, Dewan Md. Farid, Alok Sharma, and Abdollah Dehzangi

#### Abstract
The classification of motor imagery electroencephalogram (MI-EEG) is a pivotal task of the biosignal classification process in the brain-computer interface (BCI) applications. Currently, this bio-engineering based technology is being
employed by researchers in various fields to develop cutting edge applications. The classification of real-time MI-EEG signal is the most challenging task in these applications. The prediction performance of the existing classification methods is still limited due to the high dimensionality and dynamic behaviors of the real-time EEG data. To enhance the classification performance of real-time BCI applications, this paper presents a new clustering-based ensemble echnique "CluSem" to mitigate this problem. Our results demonstrate that CluSem is able to improve the classification accuracy between 5% and 15% compared to the existing methods on our collected as well as the publicly available EEG datasets. We also develop a new brain game - CluGame using this method to evaluate the classification performance of real-time motor imagery
movements. In this game, real-time EEG signal classification and prediction tabulation through animated balls are controlled via threads.


By playing this game, users can control the movements of the balls via the brain signals of
motor imagery movements without using any traditional input devices. The
source codes—used to implement CluSem and CluGame—are publicly available
at https://github.com/MdOchiuddinMiah/MI-BCI_ML.

&nbsp;

### 1. Download Package
#### 1.1. Direct Download
We can directly [download](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/MdOchiuddinMiah/MI-BCI_ML) by clicking the link.

> **Note:** The package will download in zip format `(.zip)` named `MI-BCI_ML.zip`.


#### 1.2. Clone a GitHub Repository (Optional)

Cloning a repository syncs it to our local machine (Example for Linux-based OS). After clone, we can add and edit files and then push and pull updates.
- Clone over HTTPS: `user@machine:~$ git clone https://github.com/MdOchiuddinMiah/MI-BCI_ML`
- Clone over SSH: `user@machine:~$ git clone git@github.com:MdOchiuddinMiah/MI-BCI_ML.git `

&nbsp;


### 2. How does it works (Machine Learning Perspective / CluSem)?

Feature Generate Process
|----------------------|
|<img align="center" src="https://github.com/MdOchiuddinMiah/MI-BCI_ML/blob/master/bmi-model.PNG" width="450" height="600" /> |

&nbsp;

### 3. Data acquisition and pre‑processing:

Data acquisition and pre‑processing
|----------------------|
|<img align="center" src="https://github.com/MdOchiuddinMiah/MI-BCI_ML/blob/master/signal-acquisition.png" width="700" height="400" /> |

The source code of the data pre‑processing are available on the open-source repository. Please [click](https://github.com/MdOchiuddinMiah/MI-BCI_ML/tree/master/Pre-Processing) for the download.

The datasets are available on the open-source repository. Please [click](https://github.com/MdOchiuddinMiah/MI-BCI_ML/tree/master/Datasets) for the download.

&nbsp;

### 4. Machine Learning (CluSem):
The source code of the Machine Learning model are available on the open-source repository. Please [click](https://github.com/MdOchiuddinMiah/MI-BCI_ML/tree/master/Machine-Learning) for the download.

&nbsp;

### 5. Brain-Games (CluGame):
The source code of the Brain-Games are available on the open-source repository. Please [click](https://github.com/MdOchiuddinMiah/MI-BCI_ML/tree/master/Brain-Game) for the download.

<img align="center" src="https://github.com/MdOchiuddinMiah/MI-BCI_ML/blob/master/brain-game.png" width="800" height="400" />
