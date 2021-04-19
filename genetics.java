import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class genetics {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        String input = f.readLine();
        int length = input.length();
        input = " " + input;
        int[] arr = new int[length + 1];
        HashMap<Character, Integer> char2Int = new HashMap<>();
        char2Int.put('A', 0);
        char2Int.put('C', 1);
        char2Int.put('G', 2);
        char2Int.put('T', 3);
        char2Int.put('?', 4);
        for (int i = 1; i <= length; i++) {
            arr[i] = char2Int.get(input.charAt(i));
        }
        // ACGT?
        // int[][] prefixCount = new int[length + 1][4];
        // for (int i = 1; i <= length; i++) {
        // for (int j = 0; j < 4; j++)
        // prefixCount[i][j] = prefixCount[i - 1][j];
        // if (arr[i] == 4)
        // for (int j = 0; j < 4; j++)
        // prefixCount[i][j]++;
        // else
        // prefixCount[i][arr[i]]++;
        // }
        final long MOD = 1000000007;
        long[][] dp = new long[4][length + 1];
        for (int i = 1; i <= length; i++) {
            if (arr[i] == arr[i - 1] && arr[i] != 4)
                break; // what if both are ?
            if (arr[1] == 4) {
                dp[0][i]++;
                dp[1][i]++;
                dp[2][i]++;
                dp[3][i]++;
            } else {
                dp[arr[1]][i]++;
            }
        }

        for (int onChar = 1; onChar < length; onChar++) { // the last is exclusive
            // we place the separator after this char
            int nextChar = arr[onChar + 1]; // deal with ? separately
            for (int prevChar = 0; prevChar < 4; prevChar++) {
                for (int nextStop = onChar + 1; nextStop <= length; nextStop++) {
                    if (nextStop > onChar + 1 && arr[nextStop] == arr[nextStop - 1] && arr[nextStop] != 4)
                        break;
                    // stop after the char
                    if (arr[nextStop] != prevChar && arr[nextStop] != 4) // needs more thinking about ?
                        continue;
                    int thisCharCount = 1;
                    if (arr[nextStop] == 4) {
                        for (int i = 0; i < 4; i++) {
                            if (nextStop > onChar + 1 && i == arr[nextStop - 1])
                                continue;
                            // if(nextStop<)
                        }
                    }
                    if (nextChar == 4) {
                        if (onChar + 1 != nextStop)
                            for (int nextCharPermute = 0; nextCharPermute < 4; nextCharPermute++) {
                                dp[nextCharPermute][nextStop] += dp[prevChar][onChar];
                                dp[nextCharPermute][nextStop] %= MOD;
                            }
                        else {
                            dp[prevChar][nextStop] += dp[prevChar][onChar];
                            dp[prevChar][nextStop] %= MOD;
                        }

                    } else {
                        dp[nextChar][nextStop] += dp[prevChar][onChar];
                        dp[nextChar][nextStop] %= MOD;
                    }
                }
            }
        }
        long sum = dp[0][length] + dp[1][length] + dp[2][length] + dp[3][length];
        sum %= MOD;
        System.out.println(sum);

    }
}
