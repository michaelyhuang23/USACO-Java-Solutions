import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class game248 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("248.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
        int length = Integer.parseInt(f.readLine());
        int[] orig = new int[length + 1];
        for (int i = 1; i <= length; i++)
            orig[i] = Integer.parseInt(f.readLine());

        int[][] dp = new int[length + 1][length + 1];
        for (int i = 1; i <= length; i++) {
            dp[i][i] = orig[i];
        }
        for (int range = 2; range <= length; range++) {
            for (int left = 1; left + range - 1 <= length; left++) {
                int right = left + range - 1;
                dp[left][right] = -1;
                for (int mid = left; mid < right; mid++) {
                    if (dp[left][mid] == dp[mid + 1][right] && dp[left][mid] > 0) {
                        dp[left][right] = Math.max(dp[left][right], dp[left][mid] + 1);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = i; j < dp.length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        out.println(max);
        out.close();
    }
}
