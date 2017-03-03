#include <stdio.h>
#include <math.h>
#include <jni.h>
#include  "SecondaryInsertionSort.h"

// C source for the native Insertion Sort
// http://quiz.geeksforgeeks.org/insertion-sort/

JNIEXPORT void JNICALL Java_SecondaryInsertionSort_sort
  (JNIEnv *env, jobject object, jintArray data, jfloat failureProb)
{
	int i, j, key;
	
}
