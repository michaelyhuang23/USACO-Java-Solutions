import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class P2330 {
	static int numCross, numRoad;
	static int[] finder;
	static Edge[] edges;
	static class Edge implements Comparable<Edge>{
		int first, second, w;

		public Edge(int f, int s, int w) {
			first = f;
			second = s;
			this.w=w;
		}

		@Override
		public int compareTo(Edge o) {
			return w-o.w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCross = Integer.parseInt(st.nextToken());
		numRoad = Integer.parseInt(st.nextToken());
		edges = new Edge[numRoad];
		for(int i=0;i<numRoad;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(first,second,cost);
		}
		Arrays.sort(edges);
		int leftMaxBound=0, rightMaxBound=10000;
		int curMinMax = 10000;
		while(leftMaxBound<=rightMaxBound) {
			int midBound = (leftMaxBound+rightMaxBound)/2;
			if(check(midBound)) {curMinMax=midBound; rightMaxBound=midBound-1;}
			else {leftMaxBound=midBound+1;}
		}
		System.out.println(numCross-1+" "+curMinMax);
	}
	
	private static boolean check(int midBound) {
		finder = new int[numCross];
		for(int i=0;i<numCross;i++)
			finder[i] = i;
		int count=0;
		for(int i=0;i<numRoad;i++) {
			if(find(edges[i].first)==find(edges[i].second))
				continue;
			merge(edges[i].first,edges[i].second);
			if(edges[i].w>midBound)
				return false;
			count++;
			if(count==numCross-1)
				break;
		}
		return true;
	}

	static void merge(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if(fx != fy) {
			finder[fy] = fx;
		}
	}
	
	static int find(int u) {
		if (finder[u] == u) {
			return u;
		}
		
		finder[u] = find(finder[u]); // 路径压缩
		
		return finder[u];
	}
}
