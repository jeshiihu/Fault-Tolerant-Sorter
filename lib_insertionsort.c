#include <stdio.h>
#include <jni.h>
#include  "SecondaryInsertionSort.h"

// C source for the native Insertion Sort

JNIEXPORT void JNICALL Java_SecondaryInsertionSort_sort
  (JNIEnv *env, jobject object, jint timeout)
{
	printf("testing\n");
}