
package FileIO;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class FileManager
{
	private String _fname = "";

	public FileManager(String fname)
	{
		_fname = fname;
	}

	public void createOutputFile()
	{
		File file = new File(_fname);
		try 
		{
			if(!file.createNewFile())
			{
				file.delete(); // ensure new file
				file.createNewFile();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addNewLine(String nLine)
	{
		try
		{ // true in 2nd param of file writer is to append 
			Writer fout = new BufferedWriter(new FileWriter(_fname, true));
			fout.append(nLine + "\n");
			fout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}