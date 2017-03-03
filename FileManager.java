
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class FileManager
{
	public FileManager(){}

	public boolean fileExists(String fname)
	{
		File f = new File(fname);
		return f.exists() && !f.isDirectory();
	}

	public boolean createOutputFile(String fname)
	{
		File f = new File(fname);
		try 
		{
			// https://www.mkyong.com/java/how-to-create-a-file-in-java/
			if(!f.createNewFile())
			{
				System.out.print("File exists. Would you like to overwrite it [y/n]? ");
				Scanner s = new Scanner(System.in);
				if(Pattern.matches("y", s.nextLine()))
				{
					f.delete(); // ensure new file
					f.createNewFile();
				}
				else {
					System.out.println("No file was created");
					return false;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return true;
	}

	public void addNewLine(String fname, String nLine)
	{
		try
		{ // true in 2nd param of file writer is to append 
			Writer fout = new BufferedWriter(new FileWriter(fname, true));
			fout.append(nLine + "\n");
			fout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}