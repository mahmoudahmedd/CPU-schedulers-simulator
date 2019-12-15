/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.ArrayList;
import java.util.Vector;

public class SJF {
    private ArrayList<Process> processes;

    public SJF(ArrayList<Process> processes){
        this.processes = processes;
    }
    Vector<Integer> at = new Vector (); //arrival time to find minimum
    Vector <Integer> bt = new Vector (); //burst time to find minimum
    Vector<Integer> AT = new Vector<> (); //Arrival time
    Vector<Integer> BT = new Vector<> (); //Burst time
    Vector <String> pName = new Vector<> (); //process name
    Vector<Integer> TAT = new Vector<> (); //turn around time
    Vector<Integer> WT = new Vector<> (  ); //waiting time

    public int findMinArrival(){
        int N = processes.size ();
        for (int  i = 0 ; i < N ; i++){
            at.add ( processes.get ( i ).arrivalTime );
        }
        int min = at.get (0);
        int index = 0 ;
        for (int  i = 1 ; i < at.size (); i++){
            if(at.get ( i ) < min){
                min = at.get ( i );
                index = i;
            }
        }
        return index;
    }

    public int findMinBurst(Vector v){
        int min = (int) v.get(0);
        int index = 0;
        for (int  i = 1 ; i < v.size (); i++){
            if( (int) v.get ( i ) < min){
                min = (int) v.get ( i );
                index = i;
            }
        }
        return index;
    }
    boolean isZero(){ //check if all arrival time equal zero or not
        boolean isIdentical = false;
        for(int  i = 0 ; i < at.size () ; i++){
            if (at.get (i) == 0){
                isIdentical = true;
            }
            else {
                isIdentical = false;
                break;
            }
        }
        return isIdentical;
    }
    public void start(){
        int N = processes.size ();
        double totalWT = 0.0;
        double totalTAT = 0.0;
        int index = findMinArrival ();
        boolean check = isZero ();
        if(check){
            int temp = 0;
            String temp2;
            for(int  i = 0 ; i < N-1 ; i++){
                for(int  j = i+1 ; j < N ; j++){
                    if(processes.get (i).burstTime > processes.get ( j ).burstTime){
                        temp = processes.get ( i ).burstTime;
                        processes.get ( i ).burstTime = processes.get ( j ).burstTime;
                        processes.get ( j ).burstTime = temp;
                        temp2 = processes.get (i).name;
                        processes.get (i).name = processes.get (j).name;
                        processes.get (j).name = temp2;
                    }
                }
            }
            processes.get (0).waitingTime = 0;
            processes.get ( 0 ).turnaroundTime =  processes.get (0).burstTime;
            totalTAT = totalTAT + processes.get ( 0 ).turnaroundTime;
            for(int i = 1 ; i < N; i++){
                processes.get (i).waitingTime = processes.get (i-1).waitingTime + processes.get (i-1).burstTime;
                totalWT = totalWT + processes.get ( i ).waitingTime;
                processes.get (i).turnaroundTime = processes.get (i).waitingTime + processes.get (i).burstTime;
                totalTAT = totalTAT + processes.get ( i ).turnaroundTime;
            }
        }
        else {
            for (int  i = 0 ; i < N ; i++){
                bt.add (processes.get (i).burstTime );
            }
            pName.add (processes.get ( index ).name);
            BT.add (processes.get ( index ).burstTime);
            AT.add ( processes.get ( index ).arrivalTime );
            WT.add (0);
            int finish = BT.get (0) + processes.get (index).arrivalTime;
            TAT.add (finish - AT.get ( 0 ));
            bt.set (index,Integer.MAX_VALUE);

            for(int  k = 1 ; k < N ; k++){
                int index2 = findMinBurst (bt);
                if(processes.get(index2).arrivalTime <= finish) {
                    pName.add (processes.get ( index2 ).name);
                    BT.add ( processes.get (index2).burstTime );
                    AT.add ( processes.get ( index2 ).arrivalTime );
                    finish = finish + bt.get(index2);
                    TAT.add (finish - AT.get (k));
                    WT.add (TAT.get ( k )- BT.get ( k ));
                    bt.set (index2,Integer.MAX_VALUE);
                }
                else {
                    Vector <Integer> anotherBT = new Vector<> ( bt );
                    while (!(processes.get(index2).arrivalTime <= finish)){
                        anotherBT.set (index2,Integer.MAX_VALUE);
                        index2 = findMinBurst (anotherBT);
                    }
                    pName.add (processes.get ( index2 ).name);
                    BT.add ( processes.get (index2).burstTime );
                    AT.add ( processes.get ( index2 ).arrivalTime );
                    finish = finish + bt.get(index2);
                    TAT.add (finish - AT.get (k));
                    WT.add (TAT.get ( k )- BT.get ( k ));
                    bt.set (index2,Integer.MAX_VALUE);
                }
            }
            for (int i = 0 ; i < processes.size (); i++){
                processes.get (i).arrivalTime = AT.get (i);
                processes.get (i).burstTime = BT.get (i);
                processes.get (i).name = pName.get (i);
                processes.get (i).turnaroundTime = TAT.get (i);
                totalTAT = totalTAT + processes.get ( i ).turnaroundTime;
                processes.get (i).waitingTime = WT.get (i);
                totalWT = totalWT + processes.get ( i ).waitingTime;
            }
        }
        for(int i = 0 ; i < N ; i++)
        {
            System.out.println(processes.get (i).name +": "
                    + "waiting time = " + processes.get (i).waitingTime + ", "
                    + "turnaround time = " + processes.get ( i ).turnaroundTime);
        }
        System.out.println ("Average waiting time = "+ totalWT/processes.size ());
        System.out.println ("Average turnaround time = "+ totalTAT/processes.size ());
    }
}