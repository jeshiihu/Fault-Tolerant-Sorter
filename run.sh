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
# javah Backup

echo $'Running DataGenerator Program\n'
java DataGenerator test.txt 10

# echo $'Running Driver Program\n'
# java Driver

# javah Backup
# gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -shared -fpic -o
# libinsertionsort.so lib_insertionsort.c
# export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.
