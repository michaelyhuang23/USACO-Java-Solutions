import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class nocross {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
        int numField = Integer.parseInt(f.readLine());
        int[] left = new int[numField + 1];
        int[] right = new int[numField + 1];
        for (int i = 1; i <= numField; i++) {
            int item = Integer.parseInt(f.readLine());
            left[i] = item;
        }

        for (int i = 1; i <= numField; i++) {
            int item = Integer.parseInt(f.readLine());
            right[i] = item;
        }

        int[][] dp = new int[numField + 1][numField + 1];
        for (int onLeft = 1; onLeft <= numField; onLeft++) {
            for (int onRight = 1; onRight <= numField; onRight++) {
                dp[onLeft][onRight] = Math.max(dp[onLeft - 1][onRight], dp[onLeft][onRight - 1]);
                if (Math.abs(left[onLeft] - right[onRight]) <= 4) {
                    dp[onLeft][onRight] = Math.max(dp[onLeft][onRight], dp[onLeft - 1][onRight - 1] + 1);
                }
            }
        }

        out.println(dp[numField][numField]);
        out.close();
    }
}
