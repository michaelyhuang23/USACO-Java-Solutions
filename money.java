import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class money {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numCoinType = Integer.parseInt(st.nextToken());
		int costLimit = Integer.parseInt(st.nextToken());
		int[] cost = new int[numCoinType+1];
		for(int i=1;i<=numCoinType;i++) {
			st = new StringTokenizer(f.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[costLimit+1];
		dp[0]=1;
		for(int coinType=1; coinType<=numCoinType; coinType++) 
			for(int currentCost=cost[coinType];currentCost<=costLimit;currentCost++) 
				dp[currentCost] = dp[currentCost]+dp[currentCost-cost[coinType]];

		System.out.println(dp[costLimit]);
	}
}
