import java.util.*;
import java.io.*;

public class P3957_EXAMPLE {
	static int[][] grids;
	static int n, d, k; // 格子的数目，改进前机器人弹跳的固定距离，以及希望至少获得的分数

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(f.readLine());
		n = Integer.parseInt(tk.nextToken());
		d = Integer.parseInt(tk.nextToken());
		k = Integer.parseInt(tk.nextToken());
		grids = new int[n + 1][2]; // 含起点在内，共有n+1个点
		for (int i = 1; i <= n; i++) {
			tk = new StringTokenizer(f.readLine());
			grids[i][0] = Integer.parseInt(tk.nextToken());
			grids[i][1] = Integer.parseInt(tk.nextToken());
		}
		int low = 0;
		int high = grids[n][0]; // 由于有负数存在，故最大需要能直接跳到最后一个
		int ans = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (check(mid)) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(ans);
	}

	public static boolean check(int g) {
		// dp[i] 表示跳到第i格的最高得分
		long[] dp = new long[n + 1];
		int lpos = Math.max(1, d - g); // 跳的最短距离
		int rpos = d + g; // 跳的最长距离
		Deque<Integer> deq = new ArrayDeque<>(); // 存储上一步可能的位置
		int previousPos = 0;
		for (int i = 1; i <= n; i++) {
			// 每次入队都必须把队列中小于等于自己的数全部删掉
			while (previousPos < i && grids[i][0] - grids[previousPos][0] >= lpos) {
				while (!deq.isEmpty() && dp[previousPos] >= dp[deq.peekLast()]) {
					deq.pollLast();
				}
				deq.offerLast(previousPos);
				previousPos++;
			}
			// 如果范围超限就出队
			while (!deq.isEmpty() && grids[i][0] - grids[deq.peekFirst()][0] > rpos) {
				deq.pollFirst();
			}

			if (deq.size() == 0) {
				dp[i] = Long.MIN_VALUE / 2; // 每个格子分数可能为负数, 防止向下溢出
			} else {
				// 状态转移方程，只需要取队首元素即可。
				dp[i] = dp[deq.peekFirst()] + grids[i][1];
			}

			if (dp[i] >= k)
				return true;
		}
		return false;
	}

}
