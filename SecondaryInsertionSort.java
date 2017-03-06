// secondary will call C code
// This is the hook to the C binary search native method
import java.util.*;

public class SecondaryInsertionSort extends Thread
{
	private int[] _data;
	private float _fp;
	private boolean _failed;
	private boolean _timedout;

	public SecondaryInsertionSort(int[] data, float fp)
	{
		_data = data;
		_fp = fp;
	}

	private native boolean sort(int[] data, float failureProb);

	public void run()
	{
		try
		{
			_failed = sort(_data, _fp);
		}
		catch(ThreadDeath td)
		{
			_timedout = true;
			throw new ThreadDeath();
		}
	}

	public boolean hwFailure()
	{
		return _failed;
	}

	public boolean timedOut()
	{
		return _timedout;
	}
}