import java.io.*;
import java.util.*;

public class P1346_PriorityQueue_EXAMPLE {
	
	static class Edge {
		int to;
		int w;
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	
	static class Point {
		int id;
		int len; // 距离源点的距离
		public Point(int id, int len) {
			this.id = id;
			this.len = len;
		}
	}
	
	public static void main(String[] args) throws IOException {	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n, s, e;
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] f = new ArrayList[n+1];
		int[] dis = new int[n+1];
		boolean[] visited = new boolean[n+1];
		
		for(int i=1; i<=n; i++) {
			f[i] = new ArrayList<>();
			st = new StringTokenizer(in.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j=1; j<=m; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(j==1) {
					f[i].add(new Edge(x, 0));
				} else {
					f[i].add(new Edge(x, 1));
				}
			}
		}
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[s] = 0; // 起点为s
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				return Integer.compare(o1.len, o2.len); 
			}
		});
		pq.offer(new Point(s, 0));
		
		while(!pq.isEmpty()) {
			int id = pq.poll().id; // 距离源点距离最短的点
			if(visited[id]) continue;
			visited[id] = true;
			
			for(Edge edge : f[id]) {
				if(dis[edge.to] > dis[id] + edge.w) {
					dis[edge.to] = dis[id] + edge.w;
					pq.offer(new Point(edge.to, dis[edge.to]));
				}
			}
		}
		
		if(dis[e] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dis[e]);
		}
		
	}
}