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
			System.err.println("Error, please enter:\n  String_fin(.txt) String_fout(.txt) float_primaryFailure[0,1] float_secondaryFailure[0,1] int_timeout(ms)");
			return;
		}

		// get all the user inputs needed
		String fin = args[0]; // our checkpoint file
		String fout = args[1];
		fileManager.createOutputFile(fout);
		
		float fpPrimary = Float.parseFloat(args[2]);
		float fpSecondary = Float.parseFloat(args[3]);

		int timeout = Integer.parseInt(args[4]);

		// get the data array from input file!
		ArrayList<Integer> data = new ArrayList<Integer>();
		restoreCheckpoint(fin, data);
		
		try // try the primary first!
		{
			System.out.println("Attempting to sort using primary\n");
			PrimaryHeapSort primarySort = new PrimaryHeapSort(data, fpPrimary);
			
			Timer t = new Timer();
			Watchdog wd = new Watchdog(primarySort);
			t.schedule(wd, timeout);			
			primarySort.start();
			
			primarySort.join();
			t.cancel();

			if(primarySort.timedOut())
				throw new Exception("Primary timed out");

			if(primarySort.hwFailure())
				throw new Exception("Primary HW Failure");

			Adjudicator adj = new Adjudicator();
			if(!adj.pass(data))
 				throw new Exception("Primary Failed AT");
 		}
		catch(Exception e)
		{
			try 
			{   // now try the secondary
				System.err.println(e);
				restoreCheckpoint(fin, data);
				System.out.println("\nAttempting to sort using backup");

				int dataArr[] = convertDataToPrimIntArr(data);
				SecondaryInsertionSort secondarySort = new SecondaryInsertionSort(dataArr, fpSecondary);

				Timer t = new Timer();
				Watchdog wd = new Watchdog(secondarySort);
				t.schedule(wd, timeout);			
				secondarySort.start();
				
				secondarySort.join();
				t.cancel();
				data = convertToArrayList(dataArr);

				if(secondarySort.timedOut())
					throw new Exception("Secondary timed out");

				if(secondarySort.hwFailure())
					throw new Exception("Secondary HW Failure");

				Adjudicator adj = new Adjudicator();
				if(!adj.pass(data))
		 			throw new Exception("Secondary Failed AT");
	 		}
	 		catch(Exception er)
	 		{
	 			System.err.println(er);

	 			System.out.println("...deleting output file");
	 			fileManager.deleteFile(fout);

	 			System.out.println("...terminating program");
	 			System.exit(-1);
	 		}
		}

		for(int val : data)
			fileManager.addNewLine(fout, Integer.toString(val));
		
		System.out.println("Successfully sorted data to " + fout + "\n");
	}

	private static void restoreCheckpoint(String fin, ArrayList<Integer> data)
	{
		data.clear();
		try
		{
			String line = "";
			BufferedReader buf = new BufferedReader(new FileReader(fin));
			while((line = buf.readLine()) != null)
			{
				data.add(Integer.parseInt(line));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// convert to an int arr so its easier to use in jni
	private static int[] convertDataToPrimIntArr(ArrayList<Integer> arr)
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