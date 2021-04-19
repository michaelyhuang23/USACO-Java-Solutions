import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class unitedcow {
	static class Interval implements Comparable<Interval> {
		int l, r;

		public Interval(int x, int y) {
			l = x;
			r = y;
		}

		@Override
		public int compareTo(unitedcow.Interval o) {
			return r - o.r;
		}
	}

	static long[] bit;

	private static int lowbit(int x) {
		return x & (-x);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(f.readLine());
		int[] b = new int[n + 1];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 1; i <= n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		int[] prevPosNum = new int[n + 1];
		int[] prev = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prev[i] = prevPosNum[b[i]];
			prevPosNum[b[i]] = i;
		}
		bit = new long[n + 1];

		int[] prevPosNum2 = new int[n + 1];
		long total = 0;
		for (int i = 1; i <= n; i++) {
			int l = prev[i] + 1;
			int r = i - 1;
			if (r < l)
				continue;
			if (prevPosNum2[b[r]] != 0) {
				updatePos(prevPosNum2[b[r]], -1);
			}
			prevPosNum2[b[r]] = r;
			updatePos(r, 1);
			long count = getPrefixSum(r) - getPrefixSum(l - 1);
			total += count;
		}
		System.out.println(total);
	}

	private static long getPrefixSum(int index) {
		long sum = 0;
		for (int i = index; i > 0; i -= lowbit(i))
			sum += bit[i];
		return sum;
	}

	private static void updatePos(int index, int value) {
		for (int i = index; i <= n; i += lowbit(i))
			bit[i] += value;
	}

	static int n;
}
