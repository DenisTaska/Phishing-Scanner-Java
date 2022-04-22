![Phishing Scanner photo1](https://user-images.githubusercontent.com/77840347/164674999-e202d696-10e0-4382-a7e8-bad13ef5ed25.png)



***Phishing Scanner*** is a simple java application with the primary goal of dettecting common phishing words in text files.

### 📑Table of Contents
- [Purpose](#-purpose)
- [Features](#-features)
- [License](#-license)

## 🤔 Purpose
This project was created to demonstrate some of the topics covered in the Java course *( Java Collections, Searching Algorithms, GUI and Event Driven Programming, Files and Text Manipulation)*. Besides that, we wanted to create something special and more challenging.

The advanced phishing scanners served as an inspiration for this school project. The first stages implemented only two simple textArea where the text file was shown on the left and the number of risk points was shown on the right. After multiple iterations, the option to see all the common phishing words as well as their corresponding risk point were added to the right panel. Furthermore, the program could output the level of risk estimated from the calculation of the total risk point as well as highlight phishing words in the left panel. The highlight is done in three different colors, green for words with 1 risk point, yellow for words with 2 risk points and red for those with 3 risk points. Although this application was done in the context of a university project, we hope that it could find some practical use in real-life users!

## ✨ Features
The following are some of the features included in this project:

- Read and Output the text file
- output the list of the common phishing words as well as their corresponding risk points
- output the number of occurrences of each word detected in the text file
- Highlight the phishing words detected in the text file
    - Green highlight: low risk (1 point)
    - Yellow hightlight: medium risk (2 points)
    - Red highlight: high risk (3 points)
- Calculate the total risk level of the file and the sum of the risk points  
 

## 📃 License
Text Editor Java is licensed under the terms of [GNU GPLv3](LICENSE).
