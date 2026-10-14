import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = in.nextInt();

        int[] at = new int[n];   // arrival time
        int[] bt = new int[n];   // burst time
        int[] ct = new int[n];   // completion time
        int[] tat = new int[n];  // turnaround time
        int[] wt = new int[n];   // waiting time
        int[] pid = new int[n];  // process id

        // Input at and bt
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;

            System.out.print("Enter arrival time of P" + (i + 1) + ": ");
            at[i] = in.nextInt();

            System.out.print("Enter burst time of P" + (i + 1) + ": ");
            bt[i] = in.nextInt();
        }

        // Arrange processes based on at (bubble sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (at[j] > at[j + 1]) {
                    // swap arrival time
                    int temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    // swap burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    // swap process id
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        // Compute completion, turnaround, and waiting time
        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            // if CPU is idle, jump to the arrival of the next process
            if (currentTime < at[i]) {
                currentTime = at[i];
            }
            currentTime += bt[i];
            ct[i] = currentTime;
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // Output table
        double totalTat = 0, totalWt = 0;
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t"
                    + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            totalTat += tat[i];
            totalWt += wt[i];
        }

        System.out.printf("%nAverage Turnaround Time: %.2f%n", totalTat / n);
        System.out.printf("Average Waiting Time: %.2f%n", totalWt / n);

        // Gantt chart
        System.out.println("\nGantt Chart:");
        for (int i = 0; i < n; i++) {
            int start = ct[i] - bt[i];
            System.out.print("| P" + pid[i] + "(" + start + "-" + ct[i] + ") ");
        }
        System.out.println("|");

        in.close();
    }
}