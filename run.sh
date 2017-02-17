#! /bin/bash
#
# Usage:
#	./run.sh
#
# To be used to compile the files needed for Driver.java to run
# 


echo "Compiling code..."
javac -d . DataGenerator.java
javac Driver.java

echo "Running Driver Program"
java Driver
