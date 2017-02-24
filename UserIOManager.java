
package Helper;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class UserIOManager
{
	Scanner scan;

	public UserIOManager()
	{
		scan = new Scanner(System.in);
	}

	public String getFilenameInput(String prompt, String ext)
	{
		while(true)
		{
			System.out.print(prompt + "(" + ext + ") ");
			String input = scan.nextLine();

			String p = "(.+)(\\.)" + ext;
			if(Pattern.matches(p, input))
				return input;

			System.out.println("..Error: invalid filename, fname." + ext + "\n");
		}
	}

	public int getIntegerInput(String prompt)
	{
		while(true)
		{
			System.out.print(prompt);
			
			try
			{
				int input = Integer.parseInt(scan.nextLine());
				return input;
			}
			catch(Exception e)
			{
				System.out.println("..Error: invalid integer, please try again\n");
			}
		}
	}
}