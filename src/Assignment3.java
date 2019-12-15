package assignment3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class Assignment3 extends JFrame {

    private static final long serialVersionUID = 1L;

    public Assignment3(String title, ArrayList<ProcessGUI> _prcesses) 
    {
        super(title);
        // Create dataset  
        IntervalCategoryDataset dataset = getCategoryDataset(_prcesses);

        // Create chart  
        JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt Chart Example", // Chart title  
                "Software Development Phases", // X-Axis Label  
                "Timeline", // Y-Axis Label  
                dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private IntervalCategoryDataset getCategoryDataset(ArrayList<ProcessGUI> _prcesses)
    {
        
        TaskSeries series1 = new TaskSeries("Estimated Date");
        TaskSeries series2 = new TaskSeries("Estimated Daate");
        TaskSeries series3 = new TaskSeries("Estimated Daate");
        TaskSeries series4 = new TaskSeries("Estimated Daate");
        TaskSeries series5 = new TaskSeries("Estimated Daate");
        
        HashMap<String, Integer> map  = new HashMap<>(); 
        
        
        for(int i =0 ; i < _prcesses.size()-1;i++)
        {
            _prcesses.get(i).test();
            
            
            Task task = new Task(_prcesses.get(i).name,
                    new Date(_prcesses.get(i).start),
                    new Date(_prcesses.get(i).end));
            
            series1.add(task);
           
                    
           
            
        }
        
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(series1);
        dataset.add(series2);
        dataset.add(series3);
        dataset.add(series4);
        dataset.add(series5);
        return dataset;
    }

    public static void main(String[] args) 
    {
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        ArrayList<Process> prcesses = new ArrayList<>();
        ArrayList<ProcessGUI> results; // For gui
        int numOfProcesses;

        int contextSwitch = 0;
        int roundRobinTimeQuantum = 0;
        /*
        System.out.print("Enter Number of processes: ");
        numOfProcesses = scannerInt.nextInt();
        
        System.out.print("Enter Quantom for RoundRobin: ");
        roundRobinTimeQuantum = scannerInt.nextInt();

        System.out.print("Enter Process Context switch: ");
        contextSwitch = scannerInt.nextInt();
        
        for (int i = 0; i < numOfProcesses; i++) 
        {
            Process p = new Process();

            System.out.print("Enter Name of process " + (i + 1) + ": ");
            p.name = scannerString.next();
            
            System.out.print("Enter Color of process " + (i + 1) + ": ");
            p.color = scannerString.next();
            
            System.out.print("Enter Arrival time of process " + (i + 1) + ":");
            p.arrivalTime = scannerInt.nextInt();
            
            System.out.print("Enter Brust time of process " + (i + 1) + ":");
            p.burstTime = scannerInt.nextInt();
            
            System.out.print("Enter Priority of process " + (i + 1) + ":");
            p.priorityNumber = scannerInt.nextInt();
            
            p.quantom = roundRobinTimeQuantum;

            prcesses.add(p);
        }
         */

//        System.out.println("\nSJF\n: ");
//        SJF sjf = new SJF(prcesses, contextSwitch);
//        sjf.start();
        System.out.println("\nSRTF: ");
        // _name, _color, _arrivalTime, _burstTime, _priorityNumber = 0, _quantom = 0
        prcesses.add(new Process("P1", "BLACK", 0, 8, 0, 0));
        prcesses.add(new Process("P2", "BLACK", 1, 2, 0, 0));
        prcesses.add(new Process("P3", "BLACK", 4, 3, 0, 0));
        contextSwitch = 0;
        SRTF srtf = new SRTF(prcesses, contextSwitch);
        results = srtf.start();

        //**************************************************************************************************
        /*
        prcesses.add(new Process("P1", "BLACK", 0, 3, 2, 0));
        prcesses.add(new Process("P2", "BLACK", 2, 5, 6, 0));
        prcesses.add(new Process("P3", "BLACK", 1, 4, 3, 0));
        prcesses.add(new Process("P4", "BLACK", 4, 2, 5, 0));
        prcesses.add(new Process("P5", "BLACK", 6, 9, 7, 0));
        prcesses.add(new Process("P6", "BLACK", 5, 4, 4, 0));
        prcesses.add(new Process("P7", "BLACK", 7, 10, 10, 0));

        
        System.out.println("\nPriority Scheduling: \n");
        PriorityScheduling priorityScheduling = new PriorityScheduling(prcesses);
        priorityScheduling.start();
        
`       */
        //**************************************************************************************************
        // _name, _color, _arrivalTime, _burstTime, _priorityNumber, _quantom
        /*
        prcesses.add(new Process("P1", "BLACK", 0, 17, 4, 4));
        prcesses.add(new Process("P2", "BLACK", 3,  6, 9, 4));
        prcesses.add(new Process("P3", "BLACK", 4, 10, 3, 4));
        prcesses.add(new Process("P4", "BLACK", 29, 4, 8, 4));

        System.out.println("\nAG Scheduling:");
        AGScheduling agScheduling = new AGScheduling(prcesses, roundRobinTimeQuantum);
        agScheduling.start();
         */
        //**************************************************************************************************
        
        //***GUI*****
        
        SwingUtilities.invokeLater(() -> {
            Assignment3 example = new Assignment3("Gantt Chart", results);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
        
        
        //***GUI
        
        
        scannerString.close();
        scannerInt.close();
    }

}
