package assignment3;

public class Process implements Comparable<Process>
{
    public String name;
    public String color;
    public int arrivalTime;
    public int burstTime;
    public int priorityNumber;
    public int quantom;
    public int completionTime;
    public int waitingTime;
    public int turnaroundTime;
    public int remainingTime; // For AG Scheduling
    public int agFactor; // For AG Scheduling
    public int serviceTime; 
    
    public Process(){}
    
    public Process(String _name, 
                   String _color, 
                   int _arrivalTime, 
                   int _burstTime, 
                   int _priorityNumber, 
                   int _quantom) 
    {
        this.name = _name;
        this.color = _color;
        this.arrivalTime = _arrivalTime;
        this.burstTime = _burstTime;
        this.priorityNumber = _priorityNumber;
        this.quantom = _quantom;
    }

    @Override
    public String toString() 
    {
            return "Process [Name=" + this.name + ", WaitingTime=" + this.waitingTime + ", TurnaroundTime=" + this.turnaroundTime + "]";
    }
    
    @Override
    public int compareTo(Process _p) 
    {
        // For ascending order
        return this.arrivalTime - _p.arrivalTime;
    }
    
    void test()
    {
        System.out.println("Name: "+ name + " - Color: " + color 
                + " arrivalTime: " +  arrivalTime + " burstTime: " +
                burstTime + " completionTime : "+  completionTime + " waitingTime: " + waitingTime + 
                " turnaroundTime: " +turnaroundTime + " remainingTime: " + remainingTime + " serviceTime: " + serviceTime);
    }
}
