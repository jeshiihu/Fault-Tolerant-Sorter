#! /bin/bash
#
# Usage:
#	./run.sh
#
# To be used to compile the files needed for Driver.java to run
# 


echo $'Compiling code...'
javac -d . FileManager.java

javac DataGenerator.java
javac Driver.java

# echo $'Running DataGenerator Program\n'
# java DataGenerator

# echo $'Running Driver Program\n'
# java Driver
