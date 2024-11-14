import java.util.Scanner;

public class BankersAlgorithm {
    private static int numProcesses;
    private static int numResources;
    private static int[] available;
    private static int[][] max;
    private static int[][] allocation;
    private static int[][] need;

    private static void calculateNeed() {
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    private static boolean isSafeState() {
        int[] work = available.clone();
        boolean[] finish = new boolean[numProcesses];
        int[] safeSequence = new int[numProcesses];
        int count = 0;

        while (count < numProcesses) {
            boolean found = false;

            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i]) {
                    boolean canFinish = true;

                    for (int j = 0; j < numResources; j++) {
                        if (need[i][j] > work[j]) {
                            canFinish = false;
                            break;
                        }
                    }

                    if (canFinish) {
                        for (int j = 0; j < numResources; j++) {
                            work[j] += allocation[i][j];
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("System is not in a safe state.");
                return false;
            }
        }

        System.out.print("System is in a safe state.\nSafe sequence: ");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("P" + (safeSequence[i] + 1) + " ");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        numProcesses = scanner.nextInt();

        System.out.print("Enter the number of resources: ");
        numResources = scanner.nextInt();

        available = new int[numResources];
        max = new int[numProcesses][numResources];
        allocation = new int[numProcesses][numResources];
        need = new int[numProcesses][numResources];

        System.out.println("Enter maximum resources for each process:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Process " + (i + 1) + ": ");
            for (int j = 0; j < numResources; j++) {
                System.out.print("Maximum value for resource " + (j + 1) + ": ");
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter allocated resources for each process:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Process " + (i + 1) + ": ");
            for (int j = 0; j < numResources; j++) {
                System.out.print("Allocated from resource " + (j + 1) + ": ");
                allocation[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter total available resources:");
        for (int i = 0; i < numResources; i++) {
            System.out.print("Total available for resource " + (i + 1) + ": ");
            available[i] = scanner.nextInt();
        }

        calculateNeed();

        isSafeState();
    }
}