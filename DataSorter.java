// The data sorter must accept input and output filenames, failure probabilities
// for primary and backup variants, and time limit
import java.io.*;
import java.lang.*;
import Helper.*;

public class DataSorter
{
	private static Helper.FileManager fManager;
	private static Helper.UserIOManager ioManager;

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

	public static void main(String[] args) 
	{
		fManager = new Helper.FileManager();
		ioManager = new Helper.UserIOManager();

		String fin = getInputFile();
		String fout = getOutputFile();

		SecondaryInsertionSort secondarySort = new SecondaryInsertionSort();
		System.loadLibrary("insertionsort");
		int i = 2;
		secondarySort.sort(i);
	}
}