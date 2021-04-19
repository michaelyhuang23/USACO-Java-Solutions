import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class wormsort {
	static class Portal implements Comparable<Portal>{
		int a, b, width;
		public Portal(int a, int b, int w) {
			this.a=a;
			this.b=b;
			width = w;
		}
		public int compareTo(Portal other) {
			return width-other.width;
		}
	}
	static int[] finder;
	static Portal[] allPorts;
	static int numPortal;
	static int numCow;
	static int[] cows;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wormsort.in")); //new FileReader("wormsort.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		numPortal = Integer.parseInt(st.nextToken());
		cows = new int[numCow];
		finder = new int[numCow];
		for(int i=0;i<numCow;i++)
			finder[i]=i;
		allPorts = new Portal[numPortal];
		st = new StringTokenizer(f.readLine());
		boolean isInOrder = true;
		for(int i=0;i<numCow;i++) {
			cows[i]=Integer.parseInt(st.nextToken())-1;
			if(i!=0 && cows[i]<cows[i-1])
				isInOrder=false;
		}
		if(isInOrder) {
			out.println(-1);
			out.close();
			f.close();
			return;
		}
		
		for(int i=0;i<numPortal;i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int wi = Integer.parseInt(st.nextToken());
			allPorts[i]=new Portal(a,b,wi);
		}
		Arrays.sort(allPorts);
		int currentMaxI=0;
		int leftWI=0, rightWI=numPortal-1;
		while(leftWI<=rightWI) {
			int midI = (leftWI+rightWI)/2;
			if(check(midI)) {
				currentMaxI = midI;
				leftWI = midI+1;
			}else
				rightWI = midI-1;
		}
		out.println(allPorts[currentMaxI].width);
		out.close();
		f.close();
	}
	public static boolean check(int cutoffIndex) {
		for(int i=0;i<numCow;i++)
			finder[i]=i;
		for(int i=numPortal-1; i>=cutoffIndex; i--) {
			merge(allPorts[i].a,allPorts[i].b);
		}
		for(int i=0;i<numCow;i++) {
			if(find(i)!=find(cows[i]))
				return false;
		}
		return true;
	}
	
	static boolean merge(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if(fx != fy) {
			finder[fy] = fx;
			return true;
		}
		return false;
	}
	
	static int find(int u) {
		if (finder[u] == u) {
			return u;
		}
		
		finder[u] = find(finder[u]); // 路径压缩
		
		return finder[u];
	}
}
