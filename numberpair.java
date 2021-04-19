import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class numberpair {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		long[] exp = new long[61];
		exp[0] = 1;
		for (int i = 1; i < 61; i++)
			exp[i] = exp[i - 1] * 2; // if a==2 : exp[i] = exp[i-1]<<1
		int maxt = (int) (2 * 1e7);
		int[] primes = new int[(int) (Math.sqrt(maxt) + 1)];
		int primeCount = 0;
		primes[0] = 2;
		primes[1] = 3;
		primeCount = 2;
		for (int j = 1; (j * 6 + 1) * (j * 6 + 1) <= maxt; j++) {
			int a = j * 6 - 1;
			boolean isP = true;
			for (int i = 0; i < primeCount; i++) {
				if (a % primes[i] == 0) {
					isP = false;
					break;
				}
			}
			if (isP) {
				primes[primeCount] = a;
				primeCount++;
			}
			a = j * 6 + 1;
			isP = true;
			for (int i = 0; i < primeCount; i++) {
				if (a % primes[i] == 0) {
					isP = false;
					break;
				}
			}
			if (isP) {
				primes[primeCount] = a;
				primeCount++;
			}
		}
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			long c = Integer.parseInt(st.nextToken());
			long d = Integer.parseInt(st.nextToken());
			long x = Integer.parseInt(st.nextToken());
			HashSet<Long> ps = new HashSet<>();
			for (long p = 1; p*p <= x; p++) {
				if (x % p == 0) {
					if((p+d)%c==0)
						ps.add(p);
					if((x/p+d)%c==0)
						ps.add(x / p);
				}
			}
			long sum = 0;
			for (long p : ps) {
				long u = x / p;
				long k = (p + d) / c; // should all be int!
				long v = k * u;
				// find num distinct prime factor of k
				int factorC = 0;
				long temp = k;
				for (int j = 0; j < primeCount; j++) {
					if (primes[j] * primes[j] > temp)
						break;
					if (temp % primes[j] == 0)
						factorC++;
					while (temp % primes[j] == 0)
						temp /= primes[j];
				}
				if (temp > 1)
					factorC++;
				sum += exp[factorC];
			}
			System.out.println(sum);
		}

	}
}
