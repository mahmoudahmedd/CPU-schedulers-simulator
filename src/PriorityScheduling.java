/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mahmoud Ahmed
 */
public class PriorityScheduling 
{
    private ArrayList<Process> processes = new ArrayList<Process>();
    private ArrayList<ProcessGUI> GUIresults = new ArrayList<ProcessGUI>();
    
    public PriorityScheduling(ArrayList<Process> _processes)
    {
        this.processes = _processes;
    }
    
    public int getBestProcess(int _arrivalTime)
    {
        int minPriorityNumber = Integer.MAX_VALUE;
        int index = 0;
       
        int i = 0;
        while( (i < this.processes.size()) && (this.processes.get(i).arrivalTime <= _arrivalTime)  )
        {
            if(this.processes.get(i).priorityNumber < minPriorityNumber)
            {
                minPriorityNumber = this.processes.get(i).priorityNumber;
                index = i;
            }
            i++;
        }
        
        return index;
    }
            
    public ArrayList<ProcessGUI> start()
    {
        int processesCount = this.processes.size();
        int arrivalTime = 0;
        
        Collections.sort(this.processes);

        while(processesCount > 0)
        {
            int start = arrivalTime;
            ProcessGUI tempGUI = new ProcessGUI();
            
            int index = this.getBestProcess(arrivalTime);
            
            // Start + burstTime
            this.processes.get(index).completionTime = arrivalTime + this.processes.get(index).burstTime;
            
            // Turn Around Time = Completion Time - Arrival Time   
            this.processes.get(index).turnaroundTime = this.processes.get(index).completionTime - this.processes.get(index).arrivalTime;
            
            // Waiting Time = Turn Around Time - Burst Time   
            this.processes.get(index).waitingTime = this.processes.get(index).turnaroundTime - this.processes.get(index).burstTime;
            
            arrivalTime += this.processes.get(index).burstTime;
            
            System.out.println(this.processes.get(index));
            
            this.processes.remove(index); 
            
            // Starvation problem 
            for(int i = 0; i < this.processes.size() && this.processes.get(i).arrivalTime <= arrivalTime; i++)
            {
                Math.max(--this.processes.get(i).priorityNumber, 0);
            }
            
            int end = arrivalTime;
            tempGUI.name = this.processes.get(index).name;
            tempGUI.color = this.processes.get(index).color;
            tempGUI.start = start;
            tempGUI.end = end;
            GUIresults.add(tempGUI);
            
            processesCount--;
        }
        
        return GUIresults;
        
    }
}
