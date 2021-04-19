import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hayfeast {
    public static void main(String[] args) throws IOException {
        long minSpice = Long.MAX_VALUE;
        int[] log = new int[1000000];
        log[0] = -1; // all the log values are rounded down except the first; also log means log2
                     // here
        for (int i = 1; i < 1000000; i++)
            log[i] = log[i >> 1] + 1;
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numHay = Integer.parseInt(st.nextToken());
        long minFlaver = Long.parseLong(st.nextToken());
        long[] flavers = new long[numHay + 1];
        long[] spices = new long[numHay + 1];
        long[][] maxInRange = new long[numHay + 1][log[numHay] + 1];
        long[] prefixSum = new long[numHay + 1];
        for (int i = 1; i <= numHay; i++) {
            st = new StringTokenizer(f.readLine());
            long flaver = Long.parseLong(st.nextToken());
            long spice = Long.parseLong(st.nextToken());
            flavers[i] = flaver;
            spices[i] = spice;
        }

        for (int i = 1; i <= numHay; i++)
            maxInRange[i][0] = spices[i]; // the right handside is none-inclusive
        for (int j = 1; j < maxInRange[0].length; j++) {
            for (int i = 1; i + (1 << j) - 1 <= numHay; i++) {
                maxInRange[i][j] = Math.max(maxInRange[i][j - 1], maxInRange[i + (1 << (j - 1))][j - 1]);
            }
        }
        for (int i = 1; i <= numHay; i++)
            prefixSum[i] = prefixSum[i - 1] + flavers[i];
        int first = 1, second = 1; // inclusive
        while (second <= numHay && first <= numHay) { // watch out terminating condition
            long curSum = prefixSum[second] - prefixSum[first - 1];
            if (curSum >= minFlaver) {
                int trimmedExpo = log[second + 1 - first];
                long maxSpice = 0;

                maxSpice = Math.max(maxInRange[first][trimmedExpo],
                        maxInRange[second - (1 << trimmedExpo) + 1][trimmedExpo]);

                minSpice = Math.min(minSpice, maxSpice);
                first++;
            } else {
                second++;
            }
        }

        out.println(minSpice);
        out.close();
    }
}
