# ece422_p1
Fault-Tolerant Systems, Project1 for ECE 422

This project introduces and follows a Recovery Block fault tolerant system similar to that provided in "Software Fault Tolerance Techniques and Implementation" by Laura L. Pullum. 

In the program, a file is created with randomly generated int values. This file is then sorted using a primary variant (Heap Sort written in Java) and a backup variant (Insertion Sort written in native C). Various factors are considers such as timeout, hardware failure (mimicked with failure probabilities), and an adjudicator.

Credit for compiling the software on Mac OSX:
* gcc compile for mac osx: http://mrjoelkemp.com/2012/01/getting-started-with-jni-and-c-on-osx-lion/

# Running the Program
On the terminal compile the source code. Please ensure you use the correct gcc line in the Makefile for either Linux machines or Mac OSX machines.
```bash
make
```
Please ensure that the var JAVA_HOME is set and if LD_LIBRARY_PATH was not set to the current directory, run the following:
```bash
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:. 
```
---
Generate data to sort: the expected arguments are

String         | int
-------------  | -------------
inputFile.txt  | num of ints to be generated
```bash
java DataGenerator data.txt 1000
```
---
Run the sorting program: the expected arguments are

String         | String         | float                       | float                         | int
-------------  | -------------  | --------------------------- | ----------------------------- | -------------
inputFile.txt  | outputFile.txt | primary failure probability | secondary failure probability | timeout (ms)
```bash
java DataSorter data.txt output.txt 0.99 0.0000001 1000 
```
---
A bash file has been provided that tests and runs each case the program may run into
```bash
./test.sh
```
