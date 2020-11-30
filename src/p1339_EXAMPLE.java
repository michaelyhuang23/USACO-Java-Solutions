
import java.io.*;
import java.util.*;

public class p1339_EXAMPLE {

	static class Edge {
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(tk.nextToken());
		int m = Integer.parseInt(tk.nextToken());
		int s = Integer.parseInt(tk.nextToken());
		int t = Integer.parseInt(tk.nextToken());
		int[] dis = new int[n + 1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		ArrayList<Edge>[] graph = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			tk = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(tk.nextToken());
			int v = Integer.parseInt(tk.nextToken());
			int w = Integer.parseInt(tk.nextToken());
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] isInQueue = new boolean[n + 1];
		Arrays.fill(isInQueue, false);
		q.offer(s);
		isInQueue[s] = true;
		dis[s] = 0;
		while (!q.isEmpty()) {
			int u = q.poll();
			isInQueue[u] = false;
			for (Edge e : graph[u]) {
				if (dis[e.to] > dis[u] + e.w) {
					dis[e.to] = dis[u] + e.w;
					if (!isInQueue[e.to]) {
						q.offer(e.to);
						isInQueue[e.to] = true;
					}
				}
			}
		}
		System.out.println(dis[t]);
	}
}