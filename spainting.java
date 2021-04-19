import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class spainting {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("spainting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int numType = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        long MOD = (int) (1e9 + 7);
        long[] dp = new long[length + 1];
        dp[0] = 1;
        long[] prefix = new long[length + 1];
        prefix[0] = 1;
        for (int i = 1; i < width; i++) {
            dp[i] = (dp[i - 1] * numType) % MOD;
            prefix[i] = prefix[i - 1] + dp[i];
        }
        for (int onNum = width; onNum <= length; onNum++) {
            dp[onNum] = (((prefix[onNum - 1] - prefix[onNum - width]) + MOD) % MOD * (numType - 1)) % MOD;
            prefix[onNum] = prefix[onNum - 1] + dp[onNum];
        }
        long expo = 1;
        for (int i = 0; i < length; i++) {
            expo *= numType;
            expo %= MOD;
        }
        long result = expo - dp[length];
        result %= MOD;
        result += MOD;
        result %= MOD;
        out.println(result);
        out.close();
    }
}
