#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <jni.h>
#include  "SecondaryInsertionSort.h"

// C source for the native Insertion Sort
// http://quiz.geeksforgeeks.org/insertion-sort/

// pre-declaration of the functions used
void insertionSort(jint *data, jsize size);
jint getDataAt(jint *data, jint indx);
void setData(jint *data, jint indx, jint newVal);
jboolean checkForFailure(JNIEnv *env, jfloat fp);

// global var to keep track of mem accesses
int _memAccess = 0;

JNIEXPORT jboolean JNICALL Java_SecondaryInsertionSort_sort
  (JNIEnv *env, jobject object, jintArray data, jfloat failureProb)
{
	jboolean *is_copy = 0;
	jsize size = (*env)->GetArrayLength(env, data);

	// ensure that isccopy is 0 so it will change it in memory
  	jint *dataCopy = (jint *) (*env)->GetIntArrayElements(env, data, is_copy);

  	if (dataCopy == NULL){
    	printf("Cannot obtain data array from JVM\n");
    	exit(0);
  	}

  	insertionSort(dataCopy,size);
  	// releasing the int array copies back into memory
  	(*env)->ReleaseIntArrayElements(env, data, dataCopy, 0);
  	return checkForFailure(env, failureProb);
}

void insertionSort(jint *data, jsize size)
{
	int i, j, key;
	for(i = 1; i < size; i++)
	{
		key = getDataAt(data, i);
		j = i-1;

		// vals greater than key move to position -1
		while(j >= 0 && getDataAt(data, j) > key)
		{
			setData(data, j+1, getDataAt(data, j));
			j = j-1;
		}

		// put the key back to its correct spot!
		setData(data, j+1, key);
	}
}

int getDataAt(jint *data, jint indx)
{
	_memAccess++;
	return data[indx];	
}

void setData(jint *data, jint indx, jint newVal)
{
	_memAccess++;
	data[indx] = newVal;
}

// mimics a hardware failure
jboolean checkForFailure(JNIEnv *env, jfloat fp)
{
	jboolean failed = 0;
	float hazard = fp*(float)_memAccess;

	srand(time(NULL));
 	float randNum = ((float)rand()/(float)(RAND_MAX))*1; // 1 indicates max
 	if(hazard >= 0.5 && hazard <= (0.5+hazard))
		failed = 1;

	return failed;
}
