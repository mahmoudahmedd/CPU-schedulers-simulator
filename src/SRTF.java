package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SRTF 
{

    private ArrayList<Process> processes = new ArrayList<Process>();
    private ArrayList<Process> processes2 = new ArrayList<Process>();
    private ArrayList<Process> results = new ArrayList<Process>();
    
    private ArrayList<ProcessGUI> GUIresults = new ArrayList<ProcessGUI>();
    private int contextSwitch;


    public SRTF(ArrayList<Process> processes, int contextSwitch) 
    {
        this.processes = processes;
        this.contextSwitch = contextSwitch;
    }
    
    public void setProcesses()
    {
        // Set Remaining Time for all processes (Remaining Time = burstTime), AG-Factor
        for(int i = 0; i < this.processes.size(); i++)
        {
            // Set Remaining Time
            this.processes.get(i).remainingTime = this.processes.get(i).burstTime; 
        }
    }
    
    public boolean isFinished()
    {
        for(int i = 0; i < processes.size(); i++)
        {
            if(processes.get(i).remainingTime != 0)
                return false;
        }
        
        return true;
    }
    
    public Process getleastBurstTime(int _time)
    {
        int minBurstTime = Integer.MAX_VALUE;
        int index = 0;
       
        int i = 0;
        while( (i < this.processes.size()) && (this.processes.get(i).arrivalTime <= _time) )
        {
            if(this.processes.get(i).burstTime < minBurstTime && this.processes.get(i).burstTime != 0)
            {
                minBurstTime = this.processes.get(i).burstTime;
                index = i;
            }
            i++;
        }
        return this.processes.get(index);
    }
    
    public int getProcessIndex(Process _p)
    {
        for(int i = 0; i < processes.size(); i++)
        {
            if(processes.get(i).name == _p.name)
                return i;
        }
        return -1;
    }
    
    public void printResults()
    {
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        
        
        for(Process p: results)
        {
            System.out.println(p);
        }
      
        for(Process p: processes2)
        {
            // Set Turnaround Time
            p.turnaroundTime = p.waitingTime + p.burstTime;
            
            totalTurnaroundTime += p.turnaroundTime;
            totalWaitingTime += p.waitingTime;
        }
        
        System.out.println("AVG - Turnaround Time: " + (double) totalTurnaroundTime/processes2.size());
        System.out.println("AVG - Waiting Time: " + (double) totalWaitingTime/processes2.size());
    }
        
    public ArrayList<ProcessGUI> start() 
    {
        Process current = null;
        int currentIndex;
        int time;
        
        
        // Sort processes (arrival time - ascending order)
        Collections.sort(this.processes);
        
        this.setProcesses();
        
        for(time  = this.processes.get(0).arrivalTime; !this.isFinished(); )
        {
            current = this.getleastBurstTime(time);
            currentIndex = this.getProcessIndex(current);
            int start = time;
            ProcessGUI tempGUI = new ProcessGUI();
            //System.out.println(current + "      " + time);
            
            if(this.processes.get(currentIndex).remainingTime != 0)
            {
                // Set Service Time
                this.processes.get(currentIndex).serviceTime = time;
            }
            
            while(current.remainingTime > 0)
            {
                Process p = this.getleastBurstTime(time);

                if(p.name != current.name)
                    break;

                current.remainingTime--;
                time++;
            }
            
            // Set waiting Time
            int wT = this.processes.get(currentIndex).serviceTime - this.processes.get(currentIndex).arrivalTime;
            this.processes.get(currentIndex).waitingTime += wT;
            this.processes.get(currentIndex).arrivalTime = time;
            
            int end = time;
            tempGUI.name = current.name;
            tempGUI.color = current.color;
            tempGUI.start = start;
            tempGUI.end = end;
            GUIresults.add(tempGUI);
            
            
            // Add contextSwitch
            time += this.contextSwitch;
                    
            if(current.remainingTime == 0)
            {
                this.processes2.add(current); 
                this.processes.remove(currentIndex); 
            }
            
            results.add(current);
        }
        this.printResults();
        
        System.out.println(">>>>>>>>>>>>>      " + time);
        
        return GUIresults;
    }

}
