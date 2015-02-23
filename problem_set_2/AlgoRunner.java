public class AlgoRunner
{
	public static void main(String[]args)
	{

		if(args.length != 2 || (Integer.parseInt(args[1]) != 50 && Integer.parseInt(args[1]) != 100))
		{
			System.out.println("Useage: java AlgoRunner [time slice] [50 | 100]");
		}
		else
		{
			//Run the Algorithms here.
			SJF sjf = new SJF(Integer.parseInt(args[1]));

			//Run SJF 5 times.
			for(int i = 0; i < 5; i++)
				sjf.runSJF();
		}
	}
}