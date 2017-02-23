#! /bin/bash
#
# Usage:
#	./run.sh
#
# To be used to compile the files needed for Driver.java to run
# 


echo $'Compiling code...'
javac -d . FileManager.java
javac *.java

echo $'Running DataGenerator Program\n'
java DataGenerator test.txt 10

# echo $'Running Driver Program\n'
# java Driver
