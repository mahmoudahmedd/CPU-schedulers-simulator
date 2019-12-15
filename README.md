# CPU-schedulers-simulator
A java program to simulate schedulers such as non-Preemptive Shortest Job First (SJF)... etc.

Scheduling is a fundamental operating-system function. Almost all computer resources are
scheduled before use. The CPU is, of course, one of the primary computer resources. Thus,
its scheduling is central to operating-system design. CPU scheduling determines which
processes run when there are multiple run-able processes. CPU scheduling is important
because it can have a big effect on resource utilization and the overall performance of the
system.


The following schedulers:

1. Non-Preemptive Shortest- Job First (SJF).

2. Shortest- Remaining Time First (SRTF) Scheduling using context switching.

3. Non-preemptive Priority Scheduling (with the solving of starvation problem).

4. AG Scheduling:
4.1. The Round Robin (RR) CPU scheduling algorithm is a fair scheduling
algorithm that gives equal time quantum to all processes So All processes
are provided a static time to execute called quantum.

4.2. A new factor with each submitted process in our AG scheduling algorithm. 
This factor sums the effects of all three basic factors (priority, arrival time and burst time). 
The equation summarizes this relation is:
AG-Factor = Priority + Arrival Time + Burst Time

4.3. Once a process is executed for given time period, it’s called
Non-preemptive AG till the finishing of (ceil (50%)) of its Quantum time,
after that it’s converted to preemptive AG.
