import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class exercise {
    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("exercise.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        long MOD = Integer.parseInt(st.nextToken());
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= numCow; i++) {
            boolean success = true;
            for (int prime : primes) {
                if (prime * prime > i)
                    break;
                if (i % prime == 0) {
                    success = false;
                    break;
                }
            }
            if (!success)
                continue;
            primes.add(i);
        }
        long[][] dp = new long[primes.size() + 1][numCow + 1];
        for (int i = 0; i <= primes.size(); i++)
            dp[i][0] = 1;

        for (int primeI = 1; primeI <= primes.size(); primeI++) { // -1
            for (int size = 1; size <= numCow; size++) {
                int curPrimePow = primes.get(primeI - 1);
                dp[primeI][size] = dp[primeI - 1][size];
                while (curPrimePow <= size) {
                    dp[primeI][size] += dp[primeI - 1][size - curPrimePow] * curPrimePow;
                    dp[primeI][size] %= MOD;
                    curPrimePow *= primes.get(primeI - 1);
                }
            }
        }
        long total = 0;
        for (int size = 0; size <= numCow; size++) {
            total += dp[primes.size()][size];
            total %= MOD;
        }

        out.println(total); // we can have LCM 1 because we can only choose 1 loops
        out.close();
    }
}
