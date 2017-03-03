// The data sorter must accept input and output filenames, failure probabilities
// for primary and backup variants, and time limit
import java.io.*;
import java.lang.*;

public class DataSorter
{
	private static FileManager fileManager;

	public static void main(String[] args) 
	{
		fileManager = new FileManager();

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

		SecondaryInsertionSort secondarySort = new SecondaryInsertionSort();
		System.loadLibrary("insertionsort");
		int i = 2;
		secondarySort.sort(i);
	}
}