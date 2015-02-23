import java.util.*;

public class SJF
{
	Random rand;
	int procCount;
	
	public SJF(int procCount)
	{
		ArrayList<SimuProc> procs = new ArrayList<SimuProc>();
		rand = new Random(System.currentTimeMillis());
		this.procCount = procCount;
		
		for(int i = 0; i < procCount; i++)
			procs.add(new SimuProc());		

	}
	
	private class SimuProc
	{
		float a_time;
		float et_runtime;
		
		private SimuProc()
		{
			
			a_time = rand.nextFloat() * 201;
			et_runtime = rand.nextFloat() * (11f - .1f) + .1f;
			
			System.out.println(a_time+" "+et_runtime);
		}
	}

	public void runSJF()
	{

	}
}