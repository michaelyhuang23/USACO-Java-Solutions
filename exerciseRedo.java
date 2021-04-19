import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class exerciseRedo {
	public static void main(String[] args) throws IOException {
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader f = new BufferedReader(new FileReader("exercise.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		long MOD = Long.parseLong(st.nextToken());
		long[] primes = new long[n];
		primes[1] = 2;
		int pC = 2;
		for (int i = 3; i <= n; i += 2) {
			boolean isP = true;
			for (int j = 1; j < pC; j++) {
				if (i % primes[j] == 0) {
					isP = false;
					break;
				}
				if (primes[j] * primes[j] > i)
					break;
			}
			if (isP) {
				primes[pC] = i;
				pC++;
			}
		}
		long[][] dp = new long[pC][n + 1];
		for (int i = 0; i < pC; i++) {
			dp[i][0] = 1;
		}
		for (int pi = 1; pi < pC; pi++) {
			for (int k = 1; k <= n; k++) {
				long p = primes[pi];
				dp[pi][k] = dp[pi - 1][k];
				while(k-p>=0) {
					dp[pi][k] += (p * dp[pi-1][(int) (k - p)]) % MOD;
					p*=primes[pi];
				}
			}
		}
		long sum = 0;
		for (int i = 0; i <= n; i++) {
			sum += dp[pC - 1][i];
			sum %= MOD;
		}
		out.println(sum);
		out.close();
	}
}
