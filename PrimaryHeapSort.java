// primary variant
// heap sort

// https://github.com/jeshiihu/virtualMemory/blob/master/heapsort.c
// in-place and non recursive algorithm
// ======
// the sort is modified to keep track of the failure

import java.io.*;
import java.lang.*;
import java.util.*;

public class PrimaryHeapSort extends Thread
{
	private float _failureProb;
	private boolean _fail;
	private boolean _thDeath;
	private ArrayList<Integer> _data;
	private int _memAccess = 0;

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
			int pivot = (size-2)/2;

			for(int i = pivot; i >= 0; i--)
				downHeap(size, i);

			for(int i = 0; i < size; i++)
			{
				swapData(size-i-1, 0);
				downHeap(size-i-1,0);
			}

			checkForFailure();
		}
		catch(ThreadDeath td)
		{
			_thDeath = true;
			throw new ThreadDeath();
		}
	}

	private void downHeap(int n, int i)
	{
		while(true)
		{
			int j = getMax(n, i, 2*i+1, 2*i+2);
			if(j == i)
				break;
			
			swapData(i, j);
			i = j;
		}
	}

	private int getMax(int n, int i, int j, int k)
	{
		if(j > n)
			return i;

		if(j < n && getDataAt(j) > getDataAt(i))
			i = j;

		if(k > n)
			return i;

		if(k < n && getDataAt(k) > getDataAt(i))
			i = k;

		return i;
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