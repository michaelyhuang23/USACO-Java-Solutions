import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class stalling {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] heights = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        int[] stalls = new int[length + 1];
        st = new StringTokenizer(f.readLine());
        for (int i = 1; i <= length; i++) {
            stalls[i] = Integer.parseInt(st.nextToken());
        }
        int maxState = 1 << length;
        long[] dp = new long[maxState];
        dp[0] = 1;
        for (int state = 1; state < maxState; state++) {
            int usedCount = 0;
            for (int i = 0; i < length; i++)
                if ((state & (1 << i)) > 0)
                    usedCount++;
            for (int last = 0; last < length; last++)
                if ((state & (1 << last)) > 0) {
                    if (stalls[usedCount] >= heights[last + 1])
                        dp[state] += dp[state ^ (1 << last)];
                }
        }

        System.out.println(dp[maxState - 1]);
    }
}
