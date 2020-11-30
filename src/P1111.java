import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class P1111 {
	static class Connection implements Comparable<Connection>{
		int first, second, time;
		public Connection(int f, int s, int t) {
			first= f;
			second=s;
			time=t;
		}
		public int compareTo(Connection other) {
			return time-other.time;
		}
	}
	static int[] finder;
	static Connection[] connectors;
	static int counter;
	
	static int numConnect;
	static int numVillage;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numVillage = Integer.parseInt(st.nextToken());
		numConnect = Integer.parseInt(st.nextToken());
		connectors = new Connection[numConnect];
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int time = Integer.parseInt(st.nextToken());
			connectors[i] = new Connection(first,second,time);
		}
		Arrays.sort(connectors);
		
		int leftIBound=0, rightIBound=numConnect-1;
		int currentAns=0;
		while(leftIBound<=rightIBound) {
			int mid = (leftIBound + rightIBound)/2;
			if(check(mid)) { 
				currentAns = mid;
				rightIBound=mid-1;
			}
			else 
				leftIBound=mid+1;
		}
		if(leftIBound>numConnect-1)
			System.out.println(-1);
		else
			System.out.println(connectors[currentAns].time);
		f.close();
	}
	public static boolean check (int index) {
		finder = new int[numVillage];
		counter=numVillage;
		for(int i=0;i<numVillage;i++)
			finder[i]=i;
		for(int i=0;i<=index;i++) {
			merge(connectors[i].first,connectors[i].second);
		}

		return counter==1;
	}
	static void merge(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if(fx != fy) {
			finder[fy] = fx;
			counter--;
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
