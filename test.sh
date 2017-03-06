
# Test cases!

echo $'=== 1. Primary passes, no HW, no timeout ==='
java DataGenerator data10.txt 10
java DataSorter data10.txt output1.txt 0.0001 0.0001 1000

echo $'\n=== 2. Primary fails HW, no timeout ==='
java DataSorter data10.txt output2.txt 0.99 0.0001 1000

echo $'\n=== 3. Primary timeout ==='
java DataGenerator data500.txt 500
java DataSorter data500.txt output3.txt 0.99 0.0001 1


echo $'\n=== 4. Secondary passes ==='
java DataSorter data10.txt output4.txt 0.99 0.0001 1000

echo $'\n=== 5. Secondary fails HW, no timeout ==='
java DataSorter data10.txt output5.txt 0.99 0.99 1000

echo $'\n=== 6. Secondary timeout ==='
java DataGenerator data1000.txt 1000
java DataSorter data1000.txt output6.txt 0.99 0.0001 1