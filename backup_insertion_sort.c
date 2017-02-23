#include <stdio.h>
#include <jni.h>
#include  "SecondaryInsertionSort.h"

// C source for the native Insertion Sort

JNIEXPORT void JNICALL Java_SecondaryInsertionSort_insertionSort
  (JNIEnv *, jobject, jstring, jstring, jdouble, jdouble, jint)
{
	printf("testing\n");
}
