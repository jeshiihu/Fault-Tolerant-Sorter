#! /bin/bash
#
# Usage:
#	./run.sh
#
# To be used to compile the files needed for Driver.java to run
# 


echo $'Compiling code...'
javac -d . *.java
javac *.java

# produce header file for C native method
javah SecondaryInsertionSort

# gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -shared -fpic -o
# libinsertionsort.so lib_insertionsort.c
# export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

echo $'Running DataGenerator Program\n'
java DataGenerator test.txt 10