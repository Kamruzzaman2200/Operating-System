import java.util.Arrays;

public class DeadlockDetection {
    static final int MAX_NODES = 20;
    static int[][] adjMatrix = new int[MAX_NODES][MAX_NODES];
    static int[] visited = new int[MAX_NODES];
    static int[] recStack = new int[MAX_NODES];
    static boolean deadlockDetected = false;

    static int findNodeIndex(char[] nodes, int[] nodeCount, char node) {
        for (int i = 0; i < nodeCount[0]; i++) {
            if (nodes[i] == node) return i;
        }
        nodes[nodeCount[0]] = node;
        return nodeCount[0]++;
    }

    static void detectCycle(int node, int nodeCount) {
        visited[node] = 1;
        recStack[node] = 1;

        for (int i = 0; i < nodeCount; i++) {
            if (adjMatrix[node][i] == 1) {  // Check for an edge
                if (visited[i] == 0) {
                    detectCycle(i, nodeCount);
                } else if (recStack[i] == 1) {
                    deadlockDetected = true;  // Deadlock detected
                }
            }
        }

        recStack[node] = 0;
    }

    static void checkDeadlock(int nodeCount) {
        Arrays.fill(visited, 0);
        Arrays.fill(recStack, 0);

        for (int i = 0; i < nodeCount && !deadlockDetected; i++) {
            if (visited[i] == 0) {
                detectCycle(i, nodeCount);
            }
        }
    }

    public static void main(String[] args) {
        char[] nodes = new char[MAX_NODES];
        int[] nodeCount = {0};

        for (int i = 0; i < MAX_NODES; i++) {
            Arrays.fill(adjMatrix[i], 0);
        }

        char[][] edges = {
            {'R', 'A'}, {'A', 'S'}, {'C', 'S'}, {'F', 'S'},
            {'W', 'F'}, {'D', 'S'}, {'U', 'D'}, {'G', 'U'},
            {'V', 'G'}, {'D', 'T'}, {'T', 'E'}, {'E', 'V'},
            {'B', 'T'}
        };
        int edgeCount = edges.length;

        for (int i = 0; i < edgeCount; i++) {
            char u = edges[i][0];
            char v = edges[i][1];

            int uIndex = findNodeIndex(nodes, nodeCount, u);
            int vIndex = findNodeIndex(nodes, nodeCount, v);

            adjMatrix[uIndex][vIndex] = 1;
        }

        checkDeadlock(nodeCount[0]);

        if (deadlockDetected) {
            System.out.println("Deadlock: Yes");
        } else {
            System.out.println("Deadlock: No");
        }
    }
}
