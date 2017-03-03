// The data sorter must accept input and output filenames, failure probabilities
// for primary and backup variants, and time limit
import com.jhuynh.helpers.FileManager;
import com.jhuynh.helpers.UserIOManager;

import java.io.*;
import java.lang.*;

public class DataSorter
{
	private static FileManager fManager;
	private static UserIOManager ioManager;

	public static void main(String[] args) 
	{
		fManager = new FileManager();
		ioManager = new UserIOManager();

		// get all the user inputs needed
		String fin = getInputFile();
		String fout = getOutputFile();
		
		float fpPrimary = ioManager.getFailureProbability("Enter failure probability of the primary variant: ");
		float fpSecondary = ioManager.getFailureProbability("Enter failure probability of the Secondary variant: ");

		int timeout = ioManager.getIntegerInput("Enter the time limit(sec): ");

		SecondaryInsertionSort secondarySort = new SecondaryInsertionSort();
		System.loadLibrary("insertionsort");
		int i = 2;
		secondarySort.sort(i);
	}


	// -------------------------------------------------------------------------
	// recursive function to get the file!
	private static String getInputFile()
	{
		String file = ioManager.getFilenameInput("Enter existing input filename: ", "txt");
		if(!fManager.fileExists(file))
		{
			System.out.println("This file does not exist, please try again.\n");
			return getInputFile();
		}

		return file;
	}
	
	// recursive function to create an output file!
	private static String getOutputFile()
	{
		String fout = ioManager.getFilenameInput("Enter new output filename: ", "txt");
		if(!fManager.createOutputFile(fout))
		{
			System.out.println("Please try again.\n");
			return getOutputFile();
		}

		return fout;
	}


}