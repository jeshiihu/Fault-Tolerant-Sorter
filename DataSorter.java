// The data sorter must accept input and output filenames, failure probabilities
// for primary and backup variants, and time limit
import java.io.*;
import java.lang.*;


public class DataSorter
{
	public static void main(String[] args) 
	{
		// try primary then call c function	
		SecondaryInsertionSort secondarySort = new SecondaryInsertionSort();
		System.loadLibrary("insertionsort");
		int i = 2;
		secondarySort.sort(i);
	}
}