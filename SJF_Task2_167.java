import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Process {
    int id;
    int arrivalTime;
    int cpuTime;
    int waitingTime;
    int turnaroundTime;

    Process(int id, int arrivalTime, int cpuTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
    }
}

public class SJF_Task2_167 {
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

        int timer = 0;
        boolean[] completed = new boolean[n];
        int completedProcesses = 0;

        while (completedProcesses < n) {
            int idx = -1;
            int minCpuTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= timer && !completed[i]) {
                    if (processes[i].cpuTime < minCpuTime) {
                        minCpuTime = processes[i].cpuTime;
                        idx = i;
                    }
                }
            }

            if (idx != -1) {
                timer += processes[idx].cpuTime;
                processes[idx].waitingTime = Math.max(0, timer - processes[idx].arrivalTime - processes[idx].cpuTime);
                processes[idx].turnaroundTime = timer - processes[idx].arrivalTime;
                completed[idx] = true;
                completedProcesses++;
            } else {
                timer++;
            }
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        System.out.println("\nProcess\tArrival Time\tCPU Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            System.out.println("P" + process.id + "\t\t" + process.arrivalTime + "\t\t" + process.cpuTime + "\t\t"
                               + process.waitingTime + "\t\t" + process.turnaroundTime);
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
        }

        double averageWaitingTime = totalWaitingTime / n;
        double averageTurnaroundTime = totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

        sc.close();
    }
}
