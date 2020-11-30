import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class P1265Kruskal {
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
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCity = Integer.parseInt(f.readLine());
		int[] posX = new int[numCity];
		int[] posY = new int[numCity];
		Edge[] connector = new Edge[numCity*(numCity-1)/2];
		int count=0;
		for(int i=0;i<numCity;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			posX[i]=x;
			posY[i]=y;
			for(int j=0;j<i;j++) {
				double dX = posX[i]-posX[j];
				double dY = posY[i]-posY[j];
				double distance =  Math.sqrt(dX*dX+dY*dY);
				connector[count] = new Edge(i, j, distance);
				count++;
			}
		}
		Arrays.sort(connector);
		finder = new int[numCity];
		for(int i=0;i<numCity;i++)
			finder[i]=i;
		int counter = 0;
		double totalL = 0;
		for(int i=0;i<connector.length;i++) {
			if(find(connector[i].first)==find(connector[i].second))
				continue;
			merge(connector[i].first,connector[i].second);
			counter++;
			totalL += connector[i].w;
			if(counter==numCity-1)
				break;
		}
		System.out.printf("%.2f\n",totalL);
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
