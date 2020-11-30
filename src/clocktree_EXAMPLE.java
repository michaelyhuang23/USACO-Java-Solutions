
import java.io.*;
import java.util.*;

public class clocktree_EXAMPLE {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("clocktree.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
		int n = Integer.parseInt(in.readLine());

		int[] clock = new int[100001];
		int[] fa = new int[100001];
		int[] depth = new int[100001];
		ArrayList<Integer>[] tree = new ArrayList[100001];
		for(int i=0; i<100001; i++) {
			tree[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= n; i++)
			clock[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			tree[u].add(v);
			tree[v].add(u);
		}

		Queue<Integer> queue = new ArrayDeque<Integer>();
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : tree[u])
				if (v != fa[u]) {
					queue.offer(v);
					fa[v] = u;
					depth[v] = depth[u] + 1;
				}
		}
		
		// 黑白染色，深度为奇数的点和root颜色不一样，深度为偶数的点和root颜色一样
		int S = 0;
		int[] answer = new int[2];
		for (int i = 1; i <= n; i++)
			if ((depth[i] & 1) > 0) { // dep[i]为奇数
				answer[1]++;
				S -= clock[i];
			} else {
				answer[0]++;
				S += clock[i];
			}

		S = (S % 12 + 12) % 12; // 防止有负数
		if (S == 0)
			pw.println(answer[0] + answer[1]);
		else if (S == 1)
			pw.println(answer[0]);
		else if (S == 11)
			pw.println(answer[1]);
		else
			pw.println(0);

		in.close();
		pw.close();
	}
}