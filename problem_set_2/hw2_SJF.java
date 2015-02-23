import java.util.*;

public class hw2_SJF
{
	Random rand;
	
	public static void main(String[]args)
	{
		if(args.length < 1)
		{
			System.out.println("Useage: java hw2_SJF [number of simulated processes to generate]");
			System.exit(0);
		}
		hw2_SJF hw = new hw2_SJF(Integer.parseInt(args[0]));
	}
	
	public hw2_SJF(int args)
	{
		ArrayList<SimuProc> procs = new ArrayList<SimuProc>();
		rand = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < args; i++)
			procs.add(new SimuProc());		
	}
	
	private class SimuProc
	{
		int a_time;
		float et_runtime;
		
		private SimuProc()
		{
			a_time = rand.nextInt(201);
			et_runtime = rand.nextFloat() * (10.1f - .1f) + .1f;
			
			System.out.println(a_time+" "+et_runtime);
		}
	}
}
