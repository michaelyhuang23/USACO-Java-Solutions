import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1546 {
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
	static int[] finder;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numFarm = Integer.parseInt(f.readLine());
		int[][] connection = new int[numFarm][numFarm];
		for(int i=0;i<numFarm;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for(int j=0;j<numFarm;j++) {
				if(!st.hasMoreTokens())
					st = new StringTokenizer(f.readLine());
				connection[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		Edge[] edges = new Edge[numFarm*(numFarm-1)/2];
		int count=0;
		for(int i=0;i<numFarm;i++)
			for(int j=0;j<i;j++) {
				edges[count] = new Edge(i,j,connection[i][j]);
				count++;
			}
		
		Arrays.sort(edges);
		finder = new int[numFarm];
		for(int i=0;i<numFarm;i++)
			finder[i]=i;
		int totalLen = 0;
		int counter=0;
		for(int i=0;i<edges.length;i++) {
			if(find(edges[i].first) == find(edges[i].second))
				continue;
			totalLen += edges[i].w;
			merge(edges[i].first,edges[i].second);
			counter++;
			if(counter==numFarm-1)
				break;
			
		}
		System.out.println(totalLen);
				
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
