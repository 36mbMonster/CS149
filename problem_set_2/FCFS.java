
import java.util.ArrayList;
import java.util.Random;

/**
 * First-come first-served (FCFS) (nonpreemptive)
 * 
 * @author Alvin Ko
 * @sid    007834684
 */
public class FCFS 
{
    public static void fcfs(int processes)
    {
        ArrayList<Integer> processID = new ArrayList<>(); //stores number of processes either 50 or 100
        ArrayList<Float> arrivalTime = new ArrayList<>(); //arrival time: a float value from 0 through 200 (measured in quanta)
        ArrayList<Float> runTime = new ArrayList<>(); //expected total run time: a float value from 0.1 through 10 quanta
        ArrayList<Float> waitTime = new ArrayList<>(); //waitTime
        ArrayList<Float> turnAroundTime = new ArrayList<>(); //turn-around time
        
        for(int i = 0; i < processes; i++)
        {
            //Add process ID
            processID.add(i + 1);
            
            //Add arrival times and run times
            Random numGenerator = new Random();
            arrivalTime.add(numGenerator.nextFloat() * numGenerator.nextInt(200)); 
            runTime.add(numGenerator.nextFloat() * numGenerator.nextInt(10)); 
        }
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //calculating turn-around time
        Float totalTATime;
        for(int i = 0; i < processes; i++)
        {   
            float toAdd = runTime.get(i);
            //float toAdd = waitTime.get(i);
            turnAroundTime.add(toAdd);
        }
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //calculating waiting time
        waitTime.add(0.0f); //wait time for first process = 0
        for(int i = 1; i < processes; i++) 
        {   
            waitTime.add(0.0f); //set to 0 so index at i exists
            
            float toAdd = 0f; //resets to 0 each time
            for(int j = 0; j < i; j++)
            {
                toAdd += runTime.get(j);
                waitTime.set(i, toAdd);
            }
        }
        
        Float totalWTime = 0f;
        for(int i = 0; i < waitTime.size(); i++)
        {
            totalWTime += waitTime.get(i);
        }
        
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        //calculating response time
        Float totalRTime;
        for(int i = 0; i < processes; i++)
        {
            
        }
        
        //calculating throughput
        for(int i = 0; i < processes; i++)
        {
            
        }
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        //RESULTS
        System.out.println("pid: " + processID);
        //System.out.println(arrivalTime);
        System.out.println("Run Time: " +runTime);
        System.out.println("Wait Time: " + waitTime);
        System.out.println("Turn-around time: " + turnAroundTime);
        
        System.out.println("Average turn-around time: ");
        System.out.println("Average waiting time: " + totalWTime + "/" + processes + ": " + (totalWTime/processes));
        System.out.println("Average response time: ");
        System.out.println("Throughput: ");
        System.out.println("\n");
    }
    
    public static void main(String[] args)
    {
        fcfs(50);
        fcfs(100);
    }
}
