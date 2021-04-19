import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class talent {
    static class Cow implements Comparable<Cow> {
        int talent, weight;

        public Cow(int t, int w) {
            talent = t;
            weight = w;
        }

        @Override
        public int compareTo(talent.Cow o) {
            return -Long.compare(talent * (long) o.weight, o.talent * (long) weight);// big ones go first
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new FileReader("talent.in"));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int minWeight = Integer.parseInt(st.nextToken());
        Cow[] allCows = new Cow[numCow + 1];
        for (int i = 1; i <= numCow; i++) {
            st = new StringTokenizer(f.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            allCows[i] = new Cow(val, weight);
        }
        Arrays.sort(allCows, 1, allCows.length);
        int[][] dp = new int[numCow + 1][minWeight + 1]; // store the val
        for (int onCow = 1; onCow <= numCow; onCow++) {
            for (int weight = 1; weight < minWeight; weight++) { // < or <= ?
                // remember we want to do something special for the last line
                dp[onCow][weight] = Math.max(dp[onCow][weight], dp[onCow - 1][weight]);
                if (weight >= allCows[onCow].weight)
                    dp[onCow][weight] = Math.max(dp[onCow][weight],
                            dp[onCow - 1][weight - allCows[onCow].weight] + allCows[onCow].talent);
            }
        }
        double[] lastLine = new double[numCow + 1];
        for (int onCow = 1; onCow <= numCow; onCow++) {
            lastLine[onCow] = Math.max(lastLine[onCow], lastLine[onCow - 1]);
            for (int prevWeight = Math.max(0,
                    minWeight - allCows[onCow].weight); prevWeight < minWeight; prevWeight++) {
                // overlap at the bottom (or on the top, depending on your direction)
                if (dp[onCow - 1][prevWeight] > 0)
                    lastLine[onCow] = Math.max(lastLine[onCow], (dp[onCow - 1][prevWeight] + allCows[onCow].talent)
                            / (double) (allCows[onCow].weight + prevWeight));
                if (lastLine[onCow] > 9)
                    System.out.println(lastLine[onCow]);
            }
        }
        System.out.println((int) (lastLine[numCow] * 1000));
        out.close();
    }
}
