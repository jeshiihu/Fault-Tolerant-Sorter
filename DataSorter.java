// The data sorter must accept input and output filenames, failure probabilities
// for primary and backup variants, and time limit
import java.io.*;
import java.lang.*;
import java.util.*;

public class DataSorter
{
	private static FileManager fileManager;

	public static void main(String[] args) 
	{
		fileManager = new FileManager();
		System.loadLibrary("insertionsort");

		if(args.length != 5)
		{
			System.err.println("Invalid arguments, enter: String_fin String_fout float_primaryFailure float_secondaryFailure int_timeoutInSec");
			return;
		}

		// get all the user inputs needed
		String fin = args[0];
		String fout = args[1];
		fileManager.createOutputFile(fout);
		
		float fpPrimary = Float.parseFloat(args[2]);
		float fpSecondary = Float.parseFloat(args[3]);

		int timeout = Integer.parseInt(args[4]);

		// get the data array from input file!
		ArrayList<Integer> data = getInputData(fin);

		// start the sorting algorithms in parallel
		Adjudicator adj = new Adjudicator();

		try
		{
			PrimaryHeapSort primarySort = new PrimaryHeapSort(data, fpPrimary);
			ArrayList<Integer> primSorted = primarySort.sort();

			if(!adj.pass(primSorted))
 				throw new Exception("Primary Failed AT");

			// convert to an int arr so its easier to use in jni
			int[] dataArr = convertToPrimativeIntArr(data);
			SecondaryInsertionSort secondarySort = new SecondaryInsertionSort();
			
			int[] seconSorted = secondarySort.sort(dataArr, fpSecondary);
			ArrayList<Integer> secSorted = convertToArrayList(seconSorted);

			if(!adj.pass(secSorted))
	 			throw new Exception("Secondary Failed AT");
 		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}

	private static ArrayList<Integer> getInputData(String fin)
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();
		try
		{
			String line = "";
			BufferedReader buf = new BufferedReader(new FileReader(fin));
			while((line = buf.readLine()) != null)
			{
				arr.add(Integer.parseInt(line));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return arr;
	}

	private static int[] convertToPrimativeIntArr(ArrayList<Integer> arr)
	{
		int[] conv = new int[arr.size()];
		for(int i = 0; i < arr.size(); i++)
			conv[i] = arr.get(i).intValue();

		return conv;
	}

	private static ArrayList<Integer> convertToArrayList(int data[])
	{
		ArrayList<Integer> newArr = new ArrayList<Integer>(data.length);
		for(int val : data)
			newArr.add(val);

		return newArr;
	}
}