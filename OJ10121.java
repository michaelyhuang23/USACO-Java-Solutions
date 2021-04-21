import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class OJ10121 {
	static int[] startPos;

	public static void main(String[] args) throws IOException {

		int[] log = new int[1000000];
		log[0] = -1; // all the log values are rounded down except the first; also log means log2
						// here
		for (int i = 1; i < 1000000; i++)
			log[i] = log[i >> 1] + 1;

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int length = Integer.parseInt(st.nextToken());
		int numQuery = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] arr = new int[length + 1];
		for (int i = 1; i <= length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		startPos = new int[length + 1];
		int[] range = new int[length + 1];
		int[] tracker = new int[(int) (2 * 1e6 + 1)];

		for (int i = 1; i <= length; i++) {
			startPos[i] = Math.max(startPos[i - 1], tracker[arr[i] + (int) 1e6] + 1);
			range[i] = i - startPos[i] + 1;
			tracker[arr[i] + (int) 1e6] = i;
		}

		int[][] rmq = new int[length + 1][20];
		for (int i = 1; i <= length; i++) {
			rmq[i][0] = range[i];
		}
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j + (1 << i) - 1 <= length; j++) {
				rmq[j][i] = Math.max(rmq[j][i - 1], rmq[j + (1 << (i - 1))][i - 1]);
			}
		}

		for (int i = 0; i < numQuery; i++) {
			st = new StringTokenizer(f.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int index = findx(left, right);
			int rightVal = 0;
			if (right >= index) {
				int expo = log[right - index + 1];
				rightVal = Math.max(rmq[index][expo], rmq[right - (1 << expo) + 1][expo]);
			}
			int leftVal = index - left;
			System.out.println(Math.max(rightVal, leftVal));
		}
	}

	private static int findx(int l, int r) {
		if (startPos[l] == l)
			return l;

		if (startPos[r] < l)
			return r + 1;

		int ll = l, rr = r;

		while (ll <= rr) {
			int mid = ll + rr >> 1;

			if (startPos[mid] < l)
				ll = mid + 1;
			else
				rr = mid - 1;
		}

		return ll;
	}
}
