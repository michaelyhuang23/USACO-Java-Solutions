import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CFE106D {
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Pair> pDivs;
	static ArrayList<Integer> ns;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		int maxN = 2 * (int) 1e7;
		int[] maxPrimeDiv = new int[maxN + 1];
		for (int i = 2; i <= maxN; i++) {
			if (maxPrimeDiv[i] == 0)
				for (int j = i; j <= maxN; j += i)
					maxPrimeDiv[j] = i;
		}
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int tmp = x;
			pDivs = new ArrayList<Pair>();
			while (tmp > 1) {
				int div = maxPrimeDiv[tmp];
				int count = 0;
				while (tmp % div == 0) {
					tmp /= div;
					count++;
				}
				pDivs.add(new Pair(div, count));
			}
			ns = new ArrayList<>();
			enumerate(0, 1);
			int count = 0;
			for (int n : ns) {
				assert x % n == 0;
				int p = x / n;
				if ((p + d) % c != 0)
					continue;
				int k = (p + d) / c;
				int m = n * k;
				int tmp2 = k;
				int prod = 1;
				while (tmp2 > 1) {
					int div = maxPrimeDiv[tmp2];
					while (tmp2 % div == 0)
						tmp2 /= div;
					prod *= 2;
				}
				count += prod;
			}
			System.out.println(count);
		}
	}

	private static void enumerate(int index, int factor) {
		if (index >= pDivs.size()) {
			ns.add(factor);
			return;
		}
		int extra = 1;
		for (int i = 0; i <= pDivs.get(index).y; i++) {
			enumerate(index + 1, factor * extra);
			extra *= pDivs.get(index).x;
		}
	}
}
