// secondary will call C code
// This is the hook to the C binary search native method
import java.util.*;

public class SecondaryInsertionSort extends Thread
{
	public native void sort(int[] data, float failureProb);
}