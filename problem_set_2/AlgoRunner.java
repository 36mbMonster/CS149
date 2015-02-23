public class AlgoRunner
{
	public static void main(String[]args)
	{
		int procCount = Integer.parseInt(args[1]);

		if(args.length != 2 || (procCount != 50 && procCount != 100))
		{
			System.out.println("Useage: java AlgoRunner [time slice] [50 | 100]");
		}
		else
		{
			//Run the Algorithms here.
			SJF sjf = new SJF(procCount);

			sjf.runSJF();
		}
	}
}