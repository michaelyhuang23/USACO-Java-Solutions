import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HaircutRedo {
	static long[] bit;
	private static int lowbit(int x) {
		return x&(-x);
		// more bit operations: set to zero n=n& ~(1 << k)
		// set to one n=n|(1<<k)
		// toggle bit n=n^(1<<k)
	}
	private static long getSum(int index) {
		long sum=0;
		for(int i=index;i>0;i-=lowbit(i))
			sum+=bit[i];
			// change operation here for different functionality
		return sum;
	}
	private static void update(int index, int value) {
		for(int i=index; i<=n; i+=lowbit(i))
			bit[i]+=value;
			// change operation here for different functionality
	}
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("haircut.in"));
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
		n = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] arr = new int[n+1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		bit = new long[n+2];
		long[] pairs = new long[n+1];
		long total = 0;
		for (int b = 1; b <= n; b++) {
			long pair = getSum(n+1-arr[b]-1); // note it ranges [1,n+1]
			pairs[b]=pair;
			total += pair;
			update(n+1-arr[b],1);
		}
		long[] decre = new long[n+1];
		for (int b = 1; b <= n; b++) {
			decre[arr[b]]+=pairs[b];
		}
		long[] results = new long[n];
		for (int j = n-1; j >= 0; j--) {
			total -= decre[j];
			results[j]=total;
		}
		for (int i = 0; i < n; i++) {
			out.println(results[i]);
		}
		
		out.close();
	}
}
