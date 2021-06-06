import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class AndoverCP_cookies_in_tummy {

	private static long solve(String path) throws IOException {
		// BufferedReader f = new BufferedReader(new FileReader(path));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long l = Long.parseLong(st.nextToken());

		long[] xs = new long[m];
		int[] as = new int[m];
		int[] ps = new int[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(f.readLine());
			xs[i] = Long.parseLong(st.nextToken());
			as[i] = Integer.parseInt(st.nextToken());
			ps[i] = Integer.parseInt(st.nextToken());
		}

		assert (xs[0] == 0);

		ArrayList<Integer> cookies = new ArrayList<>();
		long cost = 0;
		for (int i = 0; i < m; i++) {
			if (i != 0) {
				ArrayList<Integer> tmp = new ArrayList<>();
				assert (xs[i] - xs[i - 1] <= cookies.size());
				for (int j = 0; j < xs[i] - xs[i - 1]; j++) {
					cost += cookies.get(j);
				}
				for (int j = (int) (xs[i] - xs[i - 1]); j < cookies.size(); j++) {
					tmp.add(cookies.get(j));
				}
				cookies = new ArrayList<>(tmp);
			}
			ArrayList<Integer> del = new ArrayList<>();
			while (!cookies.isEmpty() && cookies.get(cookies.size() - 1) > ps[i]) {
				del.add(cookies.get(cookies.size() - 1));
				cookies.remove(cookies.size() - 1);
			}
			while (cookies.size() < n) {
				if (as[i] > 0) {
					as[i]--;
					cookies.add(ps[i]);
				} else if (del.size() > 0) {
					cookies.add(del.get(del.size() - 1));
					del.remove(del.size() - 1);
				} else
					break;
			}
		}
		assert (l - xs[m - 1] <= cookies.size());
		for (int j = 0; j < l - xs[m - 1]; j++) {
			cost += cookies.get(j);
		}
		System.out.println(cost);
		return cost;
	}

	private static void genTests() throws IOException {
		Random rand = new Random();
		for (int i = 0; i < 15; i++) {
			String count = String.format("%02d", i + 5);
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("/Users/michaelhyh/Downloads/cookies_testcases/input/input" + count + ".txt")));
			int n = rand.nextInt(5000) + 1;
			int m = rand.nextInt(5000) + 1;
			int cookieCount = 0;
			long[] pos = new long[m];
			int[] as = new int[m];
			int[] ps = new int[m];
			for (int j = 0; j < m; j++) {
				if (j > 0) {
					int dist = rand.nextInt(cookieCount) + 1;
					pos[j] = pos[j - 1] + dist;
					cookieCount -= dist;
				}

				int add = rand.nextInt(n) + 1;
				cookieCount = Math.min(n, cookieCount + add);
				as[j] = rand.nextInt(97) + add;
				ps[j] = rand.nextInt(5000) + 1;
			}
			int dist = rand.nextInt(cookieCount) + 1;
			long l = pos[m - 1] + dist;
			out.println(n + " " + m + " " + l);
			for (int j = 0; j < m; j++) {
				out.println(pos[j] + " " + as[j] + " " + ps[j]);
			}
			out.close();

			out = new PrintWriter(new BufferedWriter(
					new FileWriter("/Users/michaelhyh/Downloads/cookies_testcases/output/output" + count + ".txt")));
			out.println(solve("/Users/michaelhyh/Downloads/cookies_testcases/input/input" + count + ".txt"));
			out.close();
		}
	}

	public static void main(String[] args) throws IOException {
		solve("");
	}
}
