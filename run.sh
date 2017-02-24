#! /bin/bash
#
# Usage:
#	./compile.sh
#
# To be used to compile the files needed for Driver.java to run
# 


echo $'Compiling code...'
javac -d . *.java
javac *.java

# produce header file for C native method
javah SecondaryInsertionSort

# http://mrjoelkemp.com/2012/01/getting-started-with-jni-and-c-on-osx-lion/
#### if you are on a linux machine please use the following line instead
# gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -shared -fpic -o libinsertionsort.so lib_insertionsort.c
gcc -I$JAVA_HOME/include/ -I$JAVA_HOME/include/darwin/ -dynamiclib -o libinsertionsort.jnilib lib_insertionsort.c
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.
