// The data generator must accept an output filename and the number of
// integer values to be generated as command-line arguments. Integer values are to be
// randomly generated. This program is to be written entirely in Java.

import Helper.*;
import java.io.*;
import java.lang.*;

public class DataGenerator 
{
	public static void main(String[] args) 
	{
		Helper.UserIOManager ioManager = new Helper.UserIOManager();
		
		String fname = ioManager.getFilenameInput("Enter output filename: ", "txt");
		int arrLen = ioManager.getIntegerInput("Enter number of integers values to be generated: ");

		// create the output file
		Helper.FileManager fManager = new Helper.FileManager(fname);
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