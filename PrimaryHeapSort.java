// primary variant
// heap sort

// Code for heapsort heavily taken from this source
// http://quiz.geeksforgeeks.org/heap-sort/
// ======
// the sort is modified to keep track of the failure

import java.io.*;
import java.lang.*;
import java.util.*;

public class PrimaryHeapSort
{
	float _failureProb;
	ArrayList<Integer> _data;
	int _memAccess = 0;

	public PrimaryHeapSort(ArrayList<Integer> data, float failureProb)
	{
		_data = data;
		_failureProb = failureProb;
	}

	public ArrayList<Integer> sort()
	{
		int size = _data.size();
		int pivot = size/2 -1;

		for(int i = pivot; i >= 0; i--)
			heapify(size,i);

		// exact an element on the heap
		for(int i = size-1; i >=0; i--)
		{
			swapData(0,i);
			heapify(i,0);
		}

		return _data;
	}

	private void heapify(int size, int indx)
	{
		int max = indx;
		int left = 2*indx + 1;
		int right = 2*indx + 2;

		// if left is greater than curr max value equate
		if(left < size && getDataAt(right) > getDataAt(max))
			max = left;

		// if this value was changed from the original we
		// recursively heapify
		if(max != indx)
		{
			swapData(indx, max);
			heapify(size,max);
		}
	}

	private int getDataAt(int indx)
	{
		_memAccess++;
		return _data.get(indx);
	}

	private void setDataAt(int indx, int newVal)
	{
		_memAccess++;
		_data.set(indx, newVal);
	}

	private void swapData(int indx1, int indx2)
	{
		int temp = getDataAt(indx1);
		setDataAt(indx1, getDataAt(indx2));
		setDataAt(indx2, temp);
	}
}