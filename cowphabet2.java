import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class cowphabet2 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        String str = f.readLine();
        int length = str.length();
        int[][] seq = new int[27][27];
        HashMap<Character, Integer> char2Int = new HashMap<>();
        int count = 1;
        for (int i = 0; i < length - 1; i++) {
            char cur = str.charAt(i);
            char next = str.charAt(i + 1);
            int curI = 0;
            int nextI = 0;
            if (char2Int.containsKey(cur))
                curI = char2Int.get(cur);
            else {
                char2Int.put(cur, count);
                curI = count;
                count++;
            }
            if (char2Int.containsKey(next))
                nextI = char2Int.get(next);
            else {
                char2Int.put(next, count);
                nextI = count;
                count++;
            }

            seq[curI][nextI]++;
        }

        int size = 1 << (count - 1); // should be < size

        int[] dp = new int[size];
        Arrays.fill(dp, length - 1);
        dp[0] = 0;
        for (int state = 1; state < size; state++) {
            for (int cur = 1; cur < count; cur++) {
                if ((state & (1 << (cur - 1))) == 0)
                    continue;
                int prevState = state & (~(1 << (cur - 1))); // watch out the sign
                int reverseSum = 0;
                for (int prev = 0; prev < count; prev++) { // cur==prev is ok
                    if ((state & (1 << (prev - 1))) == 0)
                        continue;
                    reverseSum += seq[cur][prev];
                }
                dp[state] = Math.min(dp[state], dp[prevState] + reverseSum);

            }
        }

        System.out.println(dp[size - 1] + 1);
    }
}
