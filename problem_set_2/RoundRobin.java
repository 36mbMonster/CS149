import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
import java.util.*;

public class RoundRobin 
{	
	static int processNumber;
	static int quantumNumber;
	static float totalTurnAroundTime;
	static float totalWaitTime;
	static float totalResponseTime;
	static float totalCompletionTime;
	
	public static void main(String[] args)
	{
		quantumNumber = Integer.parseInt(args[0]);
		processNumber = Integer.parseInt(args[1]);
		ArrayList<Process> processes = new ArrayList<Process>();
		LinkedList<Process> inQueue;
		
		for ( int i = 0; i < processNumber; i++)
		{
			processes.add(new Process(i));
			Process temp = processes.get(i);
			System.out.print("PID: " + i + ". ");
			System.out.print("Arrival Time: " + temp.arrivalTime +". ");
			System.out.println("Run Time: " + temp.runTime + ".");
		}
		Collections.sort(processes, new processComparator());
		
		float timer = 1;
		float start = processes.get(0).arrivalTime;
		inQueue = new LinkedList<Process>();
		
		//Checks for things in queue or latest arrival time
		while ( inQueue.peek()!= null || !processes.isEmpty())
		{
			while ( !processes.isEmpty() && processes.get(0).arrivalTime <= timer )
			{
				inQueue.add(processes.remove(0));
			}
			
			Process temp = inQueue.peek();
			
			if ( temp != null)
			{
				if ( temp.progress == 0)
				{
					totalResponseTime = timer - temp.arrivalTime;
				}
				if ( temp.progress + 1 > temp.runTime)
				{
					temp.completionTime = timer + temp.runTime % 1.0f;
					totalTurnAroundTime += temp.completionTime - temp.arrivalTime;
					totalWaitTime += temp.completionTime - temp.arrivalTime - temp.runTime;
					inQueue.remove();
				}
				else
				{
					temp.progress++;
					// When the process gets cut off depends on the time slice
					if ( temp.progress % quantumNumber == 0)
					{
						inQueue.remove();
						inQueue.add(temp);
					}
				}
			}
			if ( !processes.isEmpty() || inQueue.peek() != null)
			{
				timer ++;
			}
			else
			{
				timer = temp.completionTime; //Time last process is completed
			}
		}
		
		System.out.println("Average Turn-Around Time: " + totalTurnAroundTime/processNumber + ".");
		System.out.println("Average Waiting Time: " + totalWaitTime/processNumber + ".");
		System.out.println("Average Response Time: " + totalResponseTime/processNumber + ".");
		System.out.println("Throughput: " + processNumber/(timer-start) + ".");
	}	
}

class Process
{
	public int processID;
	public float arrivalTime;
	public float runTime;
	public int progress;
	public float completionTime;

	Random random = new Random();
	
	public Process(int number)
	{
		processID = number;
		this.arrivalTime = random.nextInt(200) * random.nextFloat();
		this.runTime = ( 10.000001f - .1f) * random.nextFloat() + .1f;
		progress = 0;
	}
}

class processComparator implements Comparator<Process>
{
	@Override
	public int compare(Process a, Process b)
	{
		float A = a.arrivalTime;
		float B = b.arrivalTime;
		return Float.compare(A, B);
	}
}
