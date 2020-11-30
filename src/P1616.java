import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1616 {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int costLimit = Integer.parseInt(st.nextToken());
		int numHerb = Integer.parseInt(st.nextToken());
		int[] cost = new int[numHerb+1];
		int[] values = new int[numHerb+1];
		for(int i=1;i<=numHerb;i++) {
			st = new StringTokenizer(f.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[costLimit+1];
		
		for(int herb=1; herb<=numHerb; herb++) 
			for(int currentCost=cost[herb];currentCost<=costLimit;currentCost++) 
				dp[currentCost] = Math.max(dp[currentCost], dp[currentCost-cost[herb]]+values[herb]);

		System.out.println(dp[costLimit]);
	}
}
