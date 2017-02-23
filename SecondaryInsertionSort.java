// secondary will call C code
// This is the hook to the C binary search native method

public class SecondaryInsertionSort
{
	public native void insertionSort(String fin, String fout, double pPrime, double pBackup, int timeout);
}