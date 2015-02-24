
import java.util.ArrayList;
import java.util.Collections;
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
            arrivalTime.add(numGenerator.nextFloat() * numGenerator.nextInt(200)); //add random number to arrivalTime arraylist
            Collections.sort(arrivalTime); //sorts the arrival time for the FCFS algorithmd
            runTime.add(numGenerator.nextFloat() * numGenerator.nextInt(10)); //add random number to runTime arraylist
        }

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //WAIT TIME
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
        //TURN AROUND TIME
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //calculating turn-around time
        //turn around time = completed execution - arrival time
        
        
        for(int i = 0; i < processes; i++)
        {              
            float ceTime = runTime.get(i) + waitTime.get(i); //ceTime = completed execution time
            float toAdd = ceTime - arrivalTime.get(i);
            turnAroundTime.add(toAdd);
        }
        
        Float totalTATime = 0f;
        for(int i = 0; i < turnAroundTime.size(); i++)
        {
            totalTATime += turnAroundTime.get(i);
        }
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Response Time
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //calculating response time
        //Response time same as wait time in both FCFS and SJF
        
        Float totalRTime = totalWTime;
        
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Throughput
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //calculating throughput
        Float startTime = 0f;
        Float endTime = runTime.get(runTime.size() - 1) + waitTime.get(waitTime.size() - 1);
        Float timePeriod = startTime + endTime;
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        //RESULTS
        //System.out.println("pid: " + processID);
        //System.out.println("Arrival Time: " + arrivalTime);
        //System.out.println("Run Time: " +runTime);
        //System.out.println("Wait Time: " + waitTime);
        //System.out.println("Turn-around time: " + turnAroundTime);
        
        System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("Process ID:\t");
            for(int i = 1; i <= processes;i++)
            {
                System.out.print(i + "\t");
            }
        System.out.println("");
        System.out.print("Arrival Time:\t");
            for(int i = 0; i < arrivalTime.size();i++)
            {
                System.out.format("%.2f", arrivalTime.get(i));
                System.out.print("\t");
            }
        System.out.println("");
        System.out.print("Run Time:\t");
            for(int i = 0; i < runTime.size();i++)
            {
                System.out.format("%.2f", runTime.get(i));
                System.out.print("\t");
            }
        System.out.println("");
        

        System.out.println("Average waiting time: " + totalWTime + "/" + processes + ": " + (totalWTime/processes));
        System.out.println("Average turn-around time: " + totalTATime + "/" + processes + ": " + (totalTATime/processes));
        System.out.println("Average response time: " + totalRTime +"/" + processes + ": " + (totalRTime/processes));
        System.out.println("Throughput: " + timePeriod + "/" + processes + ": " + (timePeriod/processes));
        System.out.println("\n");
    }
    
    public static void main(String[] args)
    {
        fcfs(50);
        fcfs(100);
    }
}
