
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class moop_EXAMPLE {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("moop.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		Point[] moop = new Point[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			moop[i] = new Point(x, y);
		}

		Arrays.sort(moop, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x) {
					return o1.x - o2.x;
				}
				return o1.y - o2.y;
			}
		});

		int count = 0;
		int[] min = new int[n]; // 联通块中y的最⼩值
		int[] max = new int[n]; // 联通块中y的最⼤值
		for (int i = 0; i < n; i++) {
			count++;
			min[count] = moop[i].y;
			max[count] = moop[i].y;

			// 因为最后一个连通块中只要有一个点 比 倒数第二个连通块中的一个点x,y值都大，两个连通块就能合并掉
			// 联通块的数量 > 1 && 最右边的两个联通块中，最后⼀个联通块中y的最⼤值 >= 倒数第⼆个中y的最⼩值
			while (count > 1 && max[count] >= min[count - 1]) {
				// 将最右边的两个联通块合并到⼀起
				min[count - 1] = Math.min(min[count - 1], min[count]); // 联通块中y的最⼩值
				max[count - 1] = Math.max(max[count - 1], max[count]); // 联通块中y的最⼤值
				count--;
			}
		}

		pw.println(count);
		in.close();
		pw.close();
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
