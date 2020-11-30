import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3958 {
	static int[] finder;
	static class PosNode{
		long x,y,z;
		public PosNode(int x,int y, int z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}
		public long distanceSquare(PosNode other) {
			return (other.x-x)*(other.x-x)+(other.y-y)*(other.y-y)+(other.z-z)*(other.z-z);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numQueries = Integer.parseInt(f.readLine());
		for(int i=0;i<numQueries;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int numHole = Integer.parseInt(st.nextToken());
			long height = Integer.parseInt(st.nextToken());
			long radius = Integer.parseInt(st.nextToken());
			PosNode[] allHoles = new PosNode[numHole];
			finder = new int[numHole+2];
			for(int j=0;j<numHole+2;j++)
				finder[j]=j;
			for(int j=0;j<numHole;j++) {
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				allHoles[j] = new PosNode(x,y,z);
				if(height-z<=radius)
					merge(1,j+2);
				if(z<=radius)
					merge(0,j+2);
				for(int k=0;k<j;k++) {
					if(allHoles[k].distanceSquare(allHoles[j])<=4*radius*radius) 
						merge(k+2,j+2);		
				}
			}
			
			if(find(0)==find(1))
				System.out.println("Yes");
			else
				System.out.println("No");
		}
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
