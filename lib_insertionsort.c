#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <jni.h>
#include  "SecondaryInsertionSort.h"

// C source for the native Insertion Sort
// http://quiz.geeksforgeeks.org/insertion-sort/
jint* insertionSort(jint *data, jsize size);
int getDataAt(jint *data, int indx);
void setData(jint *data, int indx, int newVal);
void checkForFailure(JNIEnv *env, jfloat fp);
jintArray convertToJintArray(JNIEnv *env, jint *data, jsize size);


int _memAccess = 0;


JNIEXPORT jintArray JNICALL Java_SecondaryInsertionSort_sort
  (JNIEnv *env, jobject object, jintArray data, jfloat failureProb)
{
	jboolean *is_copy = 0;
	jsize size = (*env)->GetArrayLength(env, data);
  	jint *dataCopy = (jint *) (*env)->GetIntArrayElements(env, data, is_copy);

  	if (dataCopy == NULL){
    	printf("Cannot obtain data array from JVM\n");
    	exit(0);
  	}

  	jint *sortedData = insertionSort(dataCopy, size);
  	checkForFailure(env, failureProb);

  	return convertToJintArray(env, sortedData, size);
}

jint* insertionSort(jint *data, jsize size)
{
	int i, j, key;
	for(i = 1; i < size; i++)
	{
		key = getDataAt(data, i);
		j = i-1;
	}

	// vals greater than key move to position -1
	while(j >= 0 && getDataAt(data, j) > key)
	{
		setData(data, j+1, getDataAt(data, j));
		j = j-1;
	}

	// put the key back to its correct spot!
	setData(data, j+1, key);
	return data;
}

int getDataAt(jint *data, int indx)
{
	_memAccess++;
	return data[indx];	
}

void setData(jint *data, int indx, int newVal)
{
	_memAccess++;
	data[indx] = newVal;
}

void checkForFailure(JNIEnv *env, jfloat fp)
{
	float hazard = fp*(float)_memAccess;

	srand(time(NULL));
 	float randNum = ((float)rand()/(float)(RAND_MAX))*1; // 1 indicates max
 
 	if(hazard >= 0.5 && hazard <= (0.5+hazard))
 	{
 		jclass Exception = (*env)->FindClass(env, "java/lang/Exception");
 		(*env)->ThrowNew(env, Exception, "Secondary HW Failed");
 	}
}

jintArray convertToJintArray(JNIEnv *env, jint *data, jsize size)
{
	// create the new jintArray and then a new jint* to coppy
	jintArray newArr = (*env)->NewIntArray(env, size);
	jint *new = (*env)->GetIntArrayElements(env, newArr, (void*)NULL);

	int i;
	for(i = 0; i < size; i++)
	{
		new[i] = data[i];
	}

	(*env)->ReleaseIntArrayElements(env, newArr, new, (jint)NULL);
	return newArr;
}

