// primary variant
// heap sort

// Code for heapsort heavily taken from this source
// http://quiz.geeksforgeeks.org/heap-sort/
// ======
// the sort is modified to keep track of the failure

import java.io.*;
import java.lang.*;
import java.util.*;

public class PrimaryHeapSort extends Thread
{
	float _failureProb;
	boolean _fail;
	boolean _thDeath;
	ArrayList<Integer> _data;
	int _memAccess = 0;

	public PrimaryHeapSort(ArrayList<Integer> data, float failureProb)
	{
		_data = data;
		_failureProb = failureProb;
		_fail = false;
		_thDeath = false;
	}

	public void run() throws ThreadDeath
	{
		try
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

			checkForFailure();
		}
		catch(ThreadDeath td)
		{
			_thDeath = true;
			throw new ThreadDeath();
		}
	}

	private void heapify(int size, int indx)
	{
		int max = indx;
		int left = 2*indx + 1;
		int right = 2*indx + 2;

		// if left is greater than curr max value equate
		if(left < size && getDataAt(left) > getDataAt(max))
			max = left;

		if(right < size && getDataAt(right) > getDataAt(max))
			max = right;

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

	private void checkForFailure()
	{
		float hazard = _failureProb*(float)_memAccess;
 		float randNum = (float)Math.random()*1;
 		System.out.println("rand float " + Float.toString(randNum));
 		System.out.println("hazard " + Float.toString(hazard));

 		if(hazard >= 0.5 && hazard <= (0.5+hazard))
	 		_fail = true;
	}

	// since its hard to return a var or throw inside a thread
	// this function allows us to check for timeout
	public boolean timedOut()
	{
		return _thDeath;
	}

	// this function allows us to check for hardware failure
	public boolean hwFailure()
	{
		return _fail;
	}
}