import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class geneticsREDO {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> char2Int = new HashMap<>();
        char2Int.put('A', 0);
        char2Int.put('C', 1);
        char2Int.put('G', 2);
        char2Int.put('T', 3);
        char2Int.put('?', 4);
        String input = f.readLine();
        int length = input.length();
        int[] arr = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            arr[i] = char2Int.get(input.charAt(i - 1));
        }
        final long MOD = 1000000007;
        long[][][][] dp = new long[length + 1][4][4][4];
        for (int curEnd = 0; curEnd < 4; curEnd++) {
            if (arr[1] != 4 && curEnd != arr[1])
                continue;
            for (int prevFront = 0; prevFront < 4; prevFront++) {
                dp[1][curEnd][curEnd][prevFront] = 1;
            }
        }
        for (int onChar = 1; onChar <= length; onChar++) {
            for (int curEnd = 0; curEnd < 4; curEnd++) {
                if (arr[onChar] != 4 && curEnd != arr[onChar])
                    continue;
                for (int curFront = 0; curFront < 4; curFront++) {
                    for (int prevFront = 0; prevFront < 4; prevFront++) {
                        for (int lastChar = 0; lastChar < 4; lastChar++) {
                            if (lastChar != curEnd) {
                                dp[onChar][curEnd][curFront][prevFront] += dp[onChar
                                        - 1][lastChar][curFront][prevFront];
                                dp[onChar][curEnd][curFront][prevFront] %= MOD;
                            }
                            if (prevFront == lastChar) {
                                dp[onChar][curEnd][curEnd][curFront] += dp[onChar - 1][lastChar][curFront][prevFront];
                                dp[onChar][curEnd][curEnd][curFront] %= MOD;
                            }
                        }
                    }
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sum += dp[length][i][j][i];
                sum %= MOD;
            }
        }
        System.out.println(sum);
    }
}
