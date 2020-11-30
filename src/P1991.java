import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1991 {
	static class Edge implements Comparable<Edge>{
		int first, second;
		double w;

		public Edge(int f, int s, double w) {
			first = f;
			second = s;
			this.w=w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(w,o.w);
		}
	}
	static int[] finder;
	static int numSate, numSite, numConnectNeed;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numSate = Integer.parseInt(st.nextToken());
		numSite = Integer.parseInt(st.nextToken());
		numConnectNeed = numSite-numSate;
		int[] posX = new int[numSite];
		int[] posY = new int[numSite];
		Edge[] connector = new Edge[numSite*(numSite-1)/2];
		int count=0;
		for(int i=0;i<numSite;i++) {
			st = new StringTokenizer(f.readLine());
			posX[i] = Integer.parseInt(st.nextToken());
			posY[i] = Integer.parseInt(st.nextToken());
			for(int j=0;j<i;j++) {
				int dX = posX[i]-posX[j];
				int dY = posY[i]-posY[j];
				connector[count] = new Edge(i,j,Math.sqrt(dX*dX+dY*dY));
				count++;
			}
		}
		Arrays.sort(connector);
		finder = new int[numSite];
		for(int i=0;i<numSite;i++)
			finder[i] = i;
		int counter=0;
		double lastEdge = 0;
		for(int i=0;i<connector.length;i++) {
			if(find(connector[i].first)==find(connector[i].second))
				continue;
			counter++;
			merge(connector[i].first,connector[i].second);
			lastEdge = connector[i].w;
			if(counter == numConnectNeed)
				break;
		}
		System.out.printf("%.2f",lastEdge);
		
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
