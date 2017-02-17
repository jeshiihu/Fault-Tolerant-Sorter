// The data generator must accept an output filename and the number of
// integer values to be generated as command-line arguments. Integer values are to be
// randomly generated. This program is to be written entirely in Java.

package project1;
import java.io.*;

public class DataGenerator 
{
	private String _outputFile;

	public DataGenerator(String outputFile)
	{
		_outputFile = outputFile; 
		System.out.println("Output file: " + _outputFile);
	}
}