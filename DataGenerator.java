// The data generator must accept an output filename and the number of
// integer values to be generated as command-line arguments. Integer values are to be
// randomly generated. This program is to be written entirely in Java.

import FileIO.*;
import java.io.*;
import java.lang.*;

public class DataGenerator 
{
	public static void main(String[] args) 
	{
		if(args.length != 2)
		{
			System.out.println("Error: arguments expected are {filename} {number of ints}");
			return;
		}

		String fname = args[0];
		int arrLen = Integer.parseInt(args[1]); // throws exception if fails to convert

		FileIO.FileManager fManager = new FileIO.FileManager(fname);
		fManager.createOutputFile();

		// create the array
		int randInts[] = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			// http://stackoverflow.com/questions/7961788/math-random-explained
			// half of the max was int value so the range is possible in the range
			int min = Integer.MIN_VALUE/2;
			int max = Integer.MAX_VALUE/2;
			int range = (max - min)+1;

			int randInt = (int)(Math.random() * range) + min;
			fManager.addNewLine(Integer.toString(randInt));
		}
	}
}