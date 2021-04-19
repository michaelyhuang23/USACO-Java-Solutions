import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class countcows {
	static HashMap<Integer, Integer> baseSum;
	static long[] exp = new long[40];
	static int[][] baseCase = { { 1, 0, 1 }, { 0, 2, 0 }, { 1, 0, 3 } };

	public static void main(String[] args) throws IOException {
		exp[0] = 1;
		for (int i = 1; i < 40; i++)
			exp[i] = exp[i - 1] * 3;
		baseSum = new HashMap<>();
		baseSum.put(-2, 1);
		baseSum.put(0, 3);
		baseSum.put(2, 1);
		// System.out.println(calcSum(225345587882474028L, 224938317491908602L));
		// System.out.println(calcSum(225345587882474000L, 224938317491908574L));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numQuery = Integer.parseInt(f.readLine());
		for (int i = 0; i < numQuery; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			long d = Long.parseLong(st.nextToken());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			if (Math.abs(x - y) % 2 == 1)
				System.out.println(0);
			else
				System.out.println(calcSum(x + d, y + d) - calcSum(x - 1, y - 1));
		}
	}

	private static long fullSum(long diff, int p) {
		long more = exp[p];
		if (diff >= more || diff <= -more)
			return 0;
		if (Math.abs(diff) % 2 == 1)
			return 0;
		if (p == 1) {
			return baseSum.get((int) diff);
		}
		long less = exp[p - 1];
		if (diff > less) {
			return fullSum(diff - less * 2, p - 1);
		} else if (diff < -less) {
			return fullSum(diff + less * 2, p - 1);
		}
		return 3 * fullSum(diff, p - 1);

	}

	private static long calcSum(long x, long y) {
		if (Math.abs(x - y) % 2 == 1)
			return 0;
		if (x < 0 || y < 0)
			return 0;
		if (x / 3 == 0 && y / 3 == 0) {
			return baseCase[(int) x][(int) y];
		}
		int ix;
		for (ix = 0; ix < 40; ix++) {
			if (x / exp[ix] == 0)
				break;
		}

		int iy;
		for (iy = 0; iy < 40; iy++) {
			if (y / exp[iy] == 0)
				break;
		}
		ix--;
		iy--;
		int imax = Math.max(ix, iy);
		long expoMax = exp[imax];
		long newX = x / expoMax;
		long newY = y / expoMax;

		long partial = 0;
		if ((newX + newY) % 2 == 0)
			partial = calcSum(x % expoMax, y % expoMax);
		// System.out.println(x % expoMax + " " + y % expoMax + " " + partial);
		long full = 0;
		if (newX == 1 && newY == 1)
			full = fullSum(x - y, imax);
		if (newX == 2 && newY == 2)
			full = 2 * fullSum(x - y, imax);
		if (newX == 0 && newY == 1 || newX == 1 && newY == 0)
			full = fullSum(x - y, imax);
		if (newX == 1 && newY == 2 || newX == 2 && newY == 1) {
			if (Math.abs(x - y) < expoMax)
				full = 2 * fullSum(x - y, imax);
			else
				full = fullSum(Math.abs(x - y) - 2 * expoMax, imax);
		}
		return full + partial;
	}
}
