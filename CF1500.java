import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CF1500 {
	static int u, v, gcduv, p, q, uS, vS, h, p_q, p_h, q_h;
	static long k;
	static int[] a, aApp, bApp, b;
	static HashSet<Integer> sharedDiv;
	static HashMap<Integer, Integer> uSDiv;
	static HashMap<Integer, Integer> vSDiv;

	static int gcd(int a, int b) {
		while (b != 0) {
			int t = a;
			a = b;
			b = t % b;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		u = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		gcduv = gcd(u, v);
		sharedDiv = new HashSet<>();
		uSDiv = new HashMap<>();
		vSDiv = new HashMap<>();
		int temp = gcduv;
		ArrayList<Integer> primes = new ArrayList<>();
		for (int i = 2; i * i <= temp; i++) {
			boolean isP = true;
			for (int prime : primes) {
				if (i % prime == 0) {
					isP = false;
					break;
				}
			}
			if (!isP)
				continue;
			primes.add(i);
			while (temp % i == 0) {
				sharedDiv.add(i);
				temp /= i;
			}
		}
		if (temp > 1)
			sharedDiv.add(temp);
		p = u;
		for (int div : sharedDiv) {
			int count = 1;
			while (p % div == 0) {
				p /= div;
				count *= div;
			}
			uSDiv.put(div, count);
		}
		q = v;
		for (int div : sharedDiv) {
			int count = 1;
			while (q % div == 0) {
				q /= div;
				count *= div;
			}
			vSDiv.put(div, count);
		}
		uS = u / p;
		vS = v / q;
		h = uS * vS / gcduv;
		for (int i = 0; i < q; i++) {
			if ((i * p) % q == 1)
				p_q = i;
		}
		for (int i = 0; i < h; i++) {
			if ((i * p) % h == 1)
				p_h = i;
			if ((i * q) % h == 1)
				q_h = i;
		}

		k = Long.parseLong(st.nextToken());
		st = new StringTokenizer(f.readLine());
		a = new int[u];
		aApp = new int[Math.max(u, v) * 2 + 1];
		bApp = new int[Math.max(u, v) * 2 + 1];
		Arrays.fill(aApp, -1);
		Arrays.fill(bApp, -1);
		for (int i = 0; i < u; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			aApp[a[i]] = i;
		}
		st = new StringTokenizer(f.readLine());
		b = new int[v];
		for (int i = 0; i < v; i++) {
			b[i] = Integer.parseInt(st.nextToken());
			bApp[b[i]] = i;
		}

		long upperB = Long.MAX_VALUE - 1;
		long lowB = 0;
		long ans = 0;

		while (lowB <= upperB) {
			long mid = (upperB / 2) + (lowB / 2) + ((upperB % 2 + lowB % 2) / 2);
			if (compute(mid) >= k) {
				ans = mid;
				upperB = mid - 1;
			} else {
				lowB = mid + 1;
			}
		}
		System.out.println(ans);
	}

	private static long compute(long n) {
		long totalS = 0;
		for (int i = 1; i <= 2 * Math.max(u, v); i++) {
			if (aApp[i] == -1 || bApp[i] == -1)
				continue;
			int x = aApp[i];
			int y = bApp[i];
			boolean success = true;
			int frac = 1;
			for (int div : sharedDiv) {
				int uN = uSDiv.get(div);
				int vN = vSDiv.get(div);
				if (x % Math.min(uN, vN) != y % Math.min(uN, vN)) {
					success = false;
					break;
				}
				if (vN > uN)
					frac *= y;
				else
					frac *= x;
				frac %= h;
			}
			if (!success)
				continue;
			long g = x + p * (long) p_q * (y - x);
			long m = g + p * q * p_h * (long) q_h * (frac - g);
			long lmax = divRoundDown((n - 1 - m), (p * q * (long) h));
			long lmin = divRoundUp(-m, p * q * (long) h);
			totalS += lmax - lmin + 1;
		}
		return n - totalS;
	}

	private static long divRoundUp(long x, long y) {
		if (x * y > 0)
			return x / y + (x % y == 0 ? 0 : 1);
		else
			return x / y;
	}

	private static long divRoundDown(long x, long y) {
		if (x * y > 0)
			return x / y;
		else
			return x / y - (x % y == 0 ? 0 : 1);
	}
}
