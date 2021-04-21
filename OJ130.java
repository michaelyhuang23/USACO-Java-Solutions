import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OJ130 {
	static long[] bit;
	static int length, numQuery;

	private static int lowbit(int x) {
		return x & (-x);
	}

	private static long getSum(int index) {
		long sum = 0;
		for (int i = index; i > 0; i -= lowbit(i))
			sum += bit[i];
		return sum;
	}

	private static void update(int index, int value) {
		for (int i = index; i <= length; i += lowbit(i))
			bit[i] += value;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		length = Integer.parseInt(st.nextToken());
		numQuery = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] orig = new int[length + 1];
		bit = new long[length + 1];
		for (int i = 1; i <= length; i++) {
			orig[i] = Integer.parseInt(st.nextToken());
			update(i, orig[i]);
		}

		for (int i = 0; i < numQuery; i++) {
			st = new StringTokenizer(f.readLine());
			if (st.nextToken().charAt(0) == '1') {
				int index = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				update(index, val);
			} else {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				System.out.println(getSum(right) - getSum(left - 1));
			}
		}
	}
}
