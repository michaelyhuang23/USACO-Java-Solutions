import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4084 {
	static int numBarn, numPainted;
	static ArrayList<Integer>[] connect;
	static int[] colors;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("moop.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numBarn = Integer.parseInt(st.nextToken());
		numPainted = Integer.parseInt(st.nextToken());
		connect = new ArrayList[numBarn+1];
		for(int i=1;i<=numBarn;i++) 
			connect[i] = new ArrayList<Integer>();
		for(int i=1;i<=numBarn-1;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			connect[first].add(second);
			connect[second].add(first);
		}
		colors = new int[numBarn+1];
		for(int i=1;i<=numPainted;i++) {
			st = new StringTokenizer(f.readLine());
			int barn = Integer.parseInt(st.nextToken());
			int color = Integer.parseInt(st.nextToken());
			colors[barn]=color;
		}
		dp = new long[numBarn+1][4];
		for(int i=0;i<=numBarn;i++)
			Arrays.fill(dp[i], -1);
		long sum=0;
		for(int color=1; color<=3; color++) {
			sum+=search(1,-1,color);
			sum%=1e9+7;
		}
		System.out.println(sum);
		f.close();
	}
	private static long search(int node, int parent, int color) {
		if(colors[node]!=0 && color!=colors[node])
			return 0;
		if(dp[node][color]>-1)
			return dp[node][color];
		long product = 1;
		for(int child : connect[node]) {
			if(child==parent)
				continue;
			long sum = 0;
			for(int nextColor = 1; nextColor <=3; nextColor++) {
				if(nextColor==color)
					continue;
				sum += search(child, node, nextColor);
				sum %= 1e9+7;
			}
			product *= sum;
			product %= 1e9+7;
		}
		
		dp[node][color]=product;
		return product;
	}
}
