import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int minStep = Integer.parseInt(st.nextToken());
        int maxStep = Integer.parseInt(st.nextToken());
        int numRock = Integer.parseInt(st.nextToken());
        int repeatStep = (minStep * maxStep) * 2;
        st = new StringTokenizer(f.readLine());
        int[] rocks = new int[numRock + 1];
        for (int i = 1; i <= numRock; i++)
            rocks[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(rocks, 1, numRock + 1);
        int[] newRocks = new int[numRock + 1];
        for (int i = 1; i <= numRock; i++) {
            int diff = (rocks[i] - rocks[i - 1]) % repeatStep;
            if (rocks[i] - rocks[i - 1] >= repeatStep)
                diff += repeatStep;
            newRocks[i] = newRocks[i - 1] + diff;
        }
        length = newRocks[numRock] + (length - rocks[numRock]) % repeatStep + maxStep;

        boolean[] hasRock = new boolean[length + 1];
        for (int i = 1; i <= numRock; i++)
            hasRock[newRocks[i]] = true;

        int[] dp = new int[length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int pos = 1; pos <= length; pos++)
            for (int prevPos = Math.max(0, pos - maxStep); prevPos <= pos - minStep; prevPos++)
                dp[pos] = Math.min(dp[pos], dp[prevPos] + ((hasRock[pos]) ? 1 : 0));
        int ans = Integer.MAX_VALUE / 2;
        for (int pos = length - maxStep; pos <= length; pos++)
            ans = Math.min(ans, dp[pos]);
        System.out.println(ans);
    }
}
