
public class Adjudicator
{
	float _failure;
	int _memAcc;

	public Adjudicator(float failure, int memAccess)
	{
		_failure = failure;
		_memAcc = memAccess;
	}

	// this mimics a transient HW failure
	public boolean pass()
	{
		float hazard = _failure*(float)_memAcc;
		float randNum = (float)Math.random()*1;

		if(hazard >= 0.5 && hazard <= (0.5+hazard))
			return false;

		return true;
	}
}