
package com.jhuynh.helpers;
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
				if(input > 0)
					return input;
				else
					System.out.println("..Error: invalid integer, please try again (> 0)\n");
			}
			catch(Exception e)
			{
				System.out.println("..Error: invalid integer, please try again\n");
			}
		}
	}

	public float getFailureProbability(String prompt)
	{
		while(true)
		{
			System.out.print(prompt);
			
			try
			{
				float input = Float.parseFloat(scan.nextLine());
				if(input >= 0 && input <= 1)
					return input;
				else
					System.out.println("..Error: invalid probability, please try again with range [0,1]\n");
			}
			catch(Exception e)
			{
				System.out.println("..Error: invalid integer, please try again with range [0,1]\n");
			}
		}
	}
}