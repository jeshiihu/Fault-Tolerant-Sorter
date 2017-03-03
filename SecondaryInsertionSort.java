// secondary will call C code
// This is the hook to the C binary search native method
import java.util.*;

public class SecondaryInsertionSort
{
	public native void sort(ArrayList<Integer> data, float failureProb);
}