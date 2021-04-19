import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class herding {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("herding.in")); // new FileReader("helpcross.in") //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        int numCow = Integer.parseInt(f.readLine());
        int[] allCows = new int[numCow];
        for (int i = 0; i < numCow; i++)
            allCows[i] = Integer.parseInt(f.readLine());
        Arrays.sort(allCows);
        int minEmpty = Integer.MAX_VALUE;
        for (int i = 1; i < numCow; i++) {
            int head = allCows[i];
            int tail = head - numCow + 1;
            if (tail < allCows[0])
                continue;
            int result = Arrays.binarySearch(allCows, tail);
            boolean emptyHead = false;
            if (result < 0) {
                result = -result - 1;
                emptyHead = true;
            }
            result--;
            int count = i - result;
            if (numCow - count == 1) {
                if (i == numCow - 1 && emptyHead)
                    continue;
            }
            minEmpty = Math.min(minEmpty, numCow - count);
        }
        for (int i = 1; i < numCow; i++) {
            int tail = allCows[i];
            int head = tail + numCow - 1;
            if (head > allCows[numCow - 1])
                continue;
            int result = Arrays.binarySearch(allCows, head);
            boolean emptyTail = false;
            if (result < 0) {
                result = -result - 1;
                emptyTail = true;
                result--;
            }

            int count = result - (i - 1);
            if (numCow - count == 1) {
                if (i == 0 && emptyTail)
                    continue;
            }
            minEmpty = Math.min(minEmpty, numCow - count);
        }
        out.println(minEmpty);
        int start = allCows[0], end = allCows[numCow - 1];
        int count = 0;
        if (allCows[0] + 1 == allCows[1] || allCows[numCow - 2] + 1 == allCows[numCow - 1])
            ;
        else {
            if (allCows[1] - allCows[0] < allCows[numCow - 1] - allCows[numCow - 2])
                start = allCows[1];
            else
                end = allCows[numCow - 2];
            count++;
        }

        out.println(end - start + 1 - numCow + count);

        out.close();
    }
}
