
import java.io.*;
import java.util.*;

public class swap_EXAMPLE {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("swap.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		int n, m, k;
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int[][] p = new int[30][100001]; // 倍增数组        p[i][j]表示执行2^i趟循环后第j位的数字
		int[] answer = new int[100001];

		for (int i = 1; i <= n; i++) {
			p[0][i] = i;
			answer[i] = i;
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			reverse(l, r, p[0]); // 一次循环之后的排列
		}

		// 倍增地求出执⾏2, 4, 8, 16...次操作后得到排列
		for (int i = 1; (1 << i) <= k; i++)
			for (int j = 1; j <= n; j++)
				p[i][j] = p[i - 1][p[i - 1][j]];

		for (int i = 0; (1 << i) <= k; i++)
			if (((k >> i) & 1) > 0) {
				for (int j = 1; j <= n; j++)
					answer[j] = p[i][answer[j]];
			}

		for (int i = 1; i <= n; i++)
			pw.println(answer[i]);

		in.close();
		pw.close();
	}

	static void reverse(int l, int r, int[] arr) {
		while (l < r) {
			int t = arr[l];
			arr[l] = arr[r];
			arr[r] = t;
			l++;
			r--;
		}
	}
}