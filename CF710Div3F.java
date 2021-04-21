import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF710Div3F {
	static class Pair implements Comparable<Pair>{
		long a,b;
		public Pair(int r, int c) {
			a=r;
			b=c;
		}
		@Override
		public int compareTo(CF710Div3F.Pair o) {
			return Long.compare(a, o.a);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			int[] rs = new int[n];
			int[] cs = new int[n];
			for (int j = 0; j < n; j++) {
				rs[j]=Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				cs[j]=Integer.parseInt(st.nextToken());
			}
			Pair[] pairs = new Pair[n];
			for (int j = 0; j < n; j++) {
				pairs[j]=new Pair(rs[j],cs[j]);
			}
			Arrays.sort(pairs);
			Pair prev = new Pair(1,1);
			long totalCost=0;
			for (int j = 0; j < n; j++) {
				if(j>0)
					prev = pairs[j-1];
				long pX = prev.a-prev.b;
				long pY = prev.b-1;
				long x = pairs[j].a-pairs[j].b;
				long y = pairs[j].b-1;
				long cost = (x-pX)/2;
				if(x-pX>0 && x%2==0 && 2*cost!=x-pX)
					cost++;
				if(x==pX && x%2==0)
					cost=y-pY;
				totalCost+=cost;
				assert x>=pX;
				
			}
			System.out.println(totalCost);
		}
	}
}
