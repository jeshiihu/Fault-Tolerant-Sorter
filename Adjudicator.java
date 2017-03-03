
// acceptance test for correct sorting algorithm
// will be using java's collection sort to test against
import java.util.*;
import java.io.*;

public class Adjudicator
{
	public Adjudicator(){}

	// this mimics a transient HW failure
	public boolean pass(ArrayList<Integer> data)
	{
		// make a copy to be the sorted data!
		ArrayList<Integer> sortedData = new ArrayList<Integer>(data);
		Collections.sort(sortedData);
		// printArr(data);
		// printArr(sortedData);

		for(int i = 0; i < data.size(); i++)
		{
			if(sortedData.get(i) != data.get(i))
				return false;
		}

		return true;
	}

	private void printArr(ArrayList<Integer> data)
	{
		System.out.println("");

		for(int val : data)
			System.out.println(Integer.toString(val));

		System.out.println("");
	}
}