import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class talentREDO {
    static int numCow, maxWeight;
    static int[] weights, talents;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("talent.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numCow = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());
        weights = new int[numCow + 1];
        talents = new int[numCow + 1];
        for (int i = 1; i <= numCow; i++) {
            st = new StringTokenizer(f.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int talent = Integer.parseInt(st.nextToken());
            weights[i] = weight;
            talents[i] = talent;
        }
        int lbound = 0, rbound = Integer.MAX_VALUE / 2;
        int ans = 0;
        while (lbound <= rbound) {
            int mid = (lbound + rbound) / 2;
            if (check(mid)) {
                ans = mid;
                lbound = mid + 1;
            } else
                rbound = mid - 1;
        }
        // System.out.println(check(1066));
        out.println(ans);
        out.close();
    }

    private static boolean check(long ratio1000) {
        // max 1000*T-ratio1000*w
        long[][] dp = new long[numCow + 1][maxWeight + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Long.MIN_VALUE / 2);
            dp[i][0] = 0;
        }
        for (int cow = 1; cow <= numCow; cow++) {
            for (int weight = 1; weight < maxWeight; weight++) {
                if (weight >= weights[cow])
                    dp[cow][weight] = Math.max(dp[cow][weight],
                            dp[cow - 1][weight - weights[cow]] + 1000L * talents[cow] - ratio1000 * weights[cow]);
                dp[cow][weight] = Math.max(dp[cow][weight], dp[cow - 1][weight]);
            }
        }
        for (int cow = 1; cow <= numCow; cow++) {
            dp[cow][maxWeight] = Math.max(dp[cow][maxWeight], dp[cow - 1][maxWeight]);
            for (int prevWeight = Math.max(maxWeight - weights[cow], 0); prevWeight <= maxWeight; prevWeight++) {
                dp[cow][maxWeight] = Math.max(dp[cow][maxWeight],
                        dp[cow - 1][prevWeight] + 1000L * talents[cow] - ratio1000 * weights[cow]);
            }
        }
        return dp[numCow][maxWeight] >= 0;
    }
}
