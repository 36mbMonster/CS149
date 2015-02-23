import java.util.*;

public class SJF
{
	Random rand;
	int procCount;
	ArrayList<SimuProc> procs;
	
	public SJF(int procCount)
	{
		procs = new ArrayList<SimuProc>();
		rand = new Random(System.currentTimeMillis());
		this.procCount = procCount;
		
		for(int i = 0; i < procCount; i++)
			procs.add(new SimuProc(i));		

	}
	
	private class SimuProc
	{
		float arrival_time;
		float et_runtime;
		int id;
		
		private SimuProc(int id)
		{
			
			arrival_time = rand.nextFloat() * 200.000001f;
			et_runtime = rand.nextFloat() * (10.000001f - .1f) + .1f;
			
			this.id = id;
		}

		@Override
		public String toString(){return "\nPID="+id+" at:"+arrival_time+" etr: "+et_runtime;}
		private float getETR(){return et_runtime;}
		private float getAT(){return arrival_time;}
	}

	public void runSJF()
	{
		Collections.sort(procs, new Comparator<SimuProc>()
		{
			@Override
			public int compare(SimuProc p1, SimuProc p2) {return Float.compare(p1.getETR(), p2.getETR());}
		});

		System.out.println(procs);

		statistics();
	}

	public void statistics()
	{
		float avgTurnAround = 0;
		float avgWait = 0;
		float throughput;
		float totalTime = 0;

		for(int i = 0; i < procs.size(); i++)
		{
			avgWait += procs.get(i).getETR();
			avgTurnAround += procs.get(i).getETR()*i+1;
			totalTime += procs.get(i).getETR();
		}
		avgTurnAround /= procs.size();
		avgWait /= procs.size();

		throughput = procs.size() / totalTime;

		System.out.println("\n\nAverage Turn Around: "+avgTurnAround);
		System.out.println("Average Wait: "+avgWait);
		System.out.println("Average Response: "+avgWait);
		System.out.println("Throughput:"+throughput);
	}
}