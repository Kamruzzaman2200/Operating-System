
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.Scanner;
 
 class Process {
     int id;
     int arrivalTime;
     int cpuTime;
 
     Process(int id, int arrivalTime, int cpuTime) {
         this.id = id;
         this.arrivalTime = arrivalTime;
         this.cpuTime = cpuTime;
     }
 }
 
 /**
  *
  * @author pikachu
  */
 public class FCFS_Task1_167 {
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         System.out.print("Enter the number of processes: ");
         int n = sc.nextInt();
 
         Process[] processes = new Process[n];
 
         for (int i = 0; i < n; i++) {
             System.out.print("Enter arrival time for process " + (i + 1) + ": ");
             int arrivalTime = sc.nextInt();
             System.out.print("Enter CPU time for process " + (i + 1) + ": ");
             int cpuTime = sc.nextInt();
             processes[i] = new Process(i + 1, arrivalTime, cpuTime);
         }
 
         Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));
 
         int[] waitingTime = new int[n];
         int[] turnaroundTime = new int[n];
         waitingTime[0] = 0;
 
         for (int i = 1; i < n; i++) {
             waitingTime[i] = processes[i - 1].cpuTime + waitingTime[i - 1] - processes[i].arrivalTime;
             if (waitingTime[i] < 0) {
                 waitingTime[i] = 0;
             }
         }
 
         for (int i = 0; i < n; i++) {
             turnaroundTime[i] = waitingTime[i] + processes[i].cpuTime;
         }
 
         double totalWaitingTime = 0;
         double totalTurnaroundTime = 0;
         for (int i = 0; i < n; i++) {
             totalWaitingTime += waitingTime[i];
             totalTurnaroundTime += turnaroundTime[i];
         }
 
         double averageWaitingTime = totalWaitingTime / n;
         double averageTurnaroundTime = totalTurnaroundTime / n;
 
         System.out.println("\nProcessArrivalTimeCPUTimeWaitingTimeTurnaroundTime");
         for (int i = 0; i < n; i++) {
             System.out.println("P" + processes[i].id + waitingTime[i] + turnaroundTime[i]);
         }
 
         System.out.println("\nAverageWaitingTime:" + averageWaitingTime);
         System.out.println("AverageTurnaroundTime:" + averageTurnaroundTime);
 
         sc.close();
     }
 }
 