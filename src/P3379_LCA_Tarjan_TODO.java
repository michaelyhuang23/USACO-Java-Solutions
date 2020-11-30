import java.util.*;
import java.io.*;

public class P3379_LCA_Tarjan_TODO {
	static ArrayList<Edge>[] graph, qgraph;
	static int[] LCA, fa;
	static boolean[] visited;

	static class Edge {
		public int to, index;

		public Edge(int to, int index) {
			this.to = to;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken()) - 1;
		graph = new ArrayList[N];
		qgraph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			qgraph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			graph[x].add(new Edge(y, i));
			graph[y].add(new Edge(x, i));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			qgraph[x].add(new Edge(y, i));
			qgraph[y].add(new Edge(x, i));
		}

		LCA = new int[2 * M];
		visited = new boolean[N];
		fa = new int[N];
		for (int i = 0; i < N; i++) {
			fa[i] = i;
		}
		tarjan(S);

		for (int i = 0; i < M; i++) {
			System.out.println(LCA[i] + 1);
		}
	}

	/**
	 * Tarjan(离线)算法时间复杂度是O(n+q) 在一次遍历中把所有询问一次性解决，所以其时间复杂度是O(n+q)
	 * 
	 * 基本思路： 1.任选一个点为根节点，从根节点开始。 2.遍历该点u所有子节点v，并标记这些子节点v已被访问过。 3.若是v还有子节点，返回2，否则下一步。
	 * 4.合并v到u上。 5.寻找与当前点u有询问关系的点v。 6.若是v已经被访问过了，则可以确认u和v的最近公共祖先为v被合并到的父亲节点a。
	 * 
	 * 利用并查集合并两个点
	 */
	static void tarjan(int curr) {
		visited[curr] = true;
		for (Edge next : graph[curr]) {
			if (visited[next.to])
				continue;
			tarjan(next.to);
			fa[next.to] = curr;
		}
		for (Edge query : qgraph[curr]) {
			if (visited[query.to]) {
				LCA[query.index] = find(query.to);
				// if(query.index%2==1){
				// LCA[query.index-1] = LCA[query.index];
				// }else{
				// LCA[query.index+1] = LCA[query.index];
				// }
			}
		}
	}

	static int find(int curr) {
		if (fa[curr] == curr) {
			return curr;
		}

		fa[curr] = find(fa[curr]);
		return fa[curr];
	}
}
