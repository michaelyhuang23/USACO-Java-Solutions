import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cowmbat {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowmbat.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int numType = Integer.parseInt(st.nextToken());
        int minLen = Integer.parseInt(st.nextToken());
        String initial = " " + f.readLine();
        int[] original = new int[length + 1];
        for (int i = 1; i < initial.length(); i++)
            original[i] = Character.getNumericValue(initial.charAt(i)) - Character.getNumericValue('a') + 1;

        int[][] weights = new int[numType + 1][numType + 1];
        for (int i = 1; i <= numType; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 1; j <= numType; j++)
                weights[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int inter = 1; inter <= numType; inter++)
            for (int first = 1; first <= numType; first++)
                for (int second = 1; second <= numType; second++)
                    weights[first][second] = Math.min(weights[first][second],
                            weights[first][inter] + weights[inter][second]);

        int[][] sum = new int[length + 1][numType + 1];
        for (int onChar = 1; onChar <= length; onChar++)
            for (int let = 1; let <= numType; let++)
                sum[onChar][let] = sum[onChar - 1][let] + weights[original[onChar]][let];

        int[][] dp = new int[length + 1][numType + 1];
        for (int i = 0; i <= length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        dp[0][1] = 0;
        for (int onChar = minLen; onChar <= length; onChar++) {
            int prevMin = Integer.MAX_VALUE / 2;
            for (int let = 1; let <= numType; let++)
                prevMin = Math.min(prevMin, dp[onChar - minLen][let]);
            for (int let = 1; let <= numType; let++) {
                dp[onChar][let] = Math.min(dp[onChar - 1][let] + weights[original[onChar]][let],
                        prevMin + sum[onChar][let] - sum[onChar - minLen][let]);
            }
        }
        int min = Integer.MAX_VALUE / 2;
        for (int let = 1; let <= numType; let++)
            min = Math.min(min, dp[length][let]);
        out.println(min);
        out.close();
    }
}
