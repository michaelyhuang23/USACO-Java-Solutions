import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class diamond {
    static class Dia implements Comparable<Dia> {
        int size, id;

        public Dia(int s, int i) {
            size = s;
            id = i;
        }

        @Override
        public int compareTo(diamond.Dia o) {
            if (size == o.size)
                return id - o.id;
            return size - o.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int maxDiff = Integer.parseInt(st.nextToken());
        Dia[] values = new Dia[numNode + 1];
        values[0] = new Dia(0, 0);
        for (int i = 1; i <= numNode; i++)
            values[i] = new Dia(Integer.parseInt(f.readLine()), 0);
        Arrays.sort(values);
        for (int i = 1; i <= numNode; i++)
            values[i].id = i;
        int[][] dp = new int[numNode + 1][3];
        for (int node = 1; node <= numNode; node++) {
            for (int numSeg = 1; numSeg <= 2; numSeg++) {
                dp[node][1] = Math.max(dp[node][1], dp[node - 1][1]);
                dp[node][2] = Math.max(dp[node][2], dp[node - 1][2]);
                int tail = values[node].size;
                int head = tail - maxDiff;
                int result;
                if (head >= values[1].size) {
                    result = Arrays.binarySearch(values, new Dia(head, 0));
                    if (result < 0)
                        result = -result - 1;
                    result--;
                } else {
                    result = 0;
                }
                dp[node][1] = Math.max(dp[node][1], dp[result][0] + node - result);
                dp[node][2] = Math.max(dp[node][2], dp[result][1] + node - result);

            }
        }
        out.println(dp[numNode][2]);
        out.close();
    }
}
