import java.io.*;
import java.util.*;

public class loan_EXAMPLE {
	static long n, k, m;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("loan.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Long.parseLong(st.nextToken());
		k = Long.parseLong(st.nextToken());
		m = Long.parseLong(st.nextToken());
		long l = 1, r = n, answer = 0;
		while (l <= r) {
			long mid = (l + r) / 2;
			if (check(mid)) {
				answer = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		pw.println(answer);

		pw.close();
	}

	private static boolean check(long x) {
		long g = 0, leftDays = k;
		while (leftDays > 0) {
			long y = (n - g) / x;
			if (y <= m) {
				return g + leftDays*m >= n;
			}
			long duration = Math.min(leftDays, ((n - g) % x) / y + 1);
			g += y * duration;
			leftDays -= duration;
		}

		return g >= n;
	}
}