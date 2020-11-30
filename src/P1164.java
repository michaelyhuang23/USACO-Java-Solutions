import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1164 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numObject = Integer.parseInt(st.nextToken());
		int costLimit = Integer.parseInt(st.nextToken());
		int[] costs = new int[numObject+1];
		
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numObject;i++) {
			if(st.hasMoreTokens()) {
				int price = Integer.parseInt(st.nextToken());
				costs[i] = price;
			}else {
				st = new StringTokenizer(f.readLine());
				int price = Integer.parseInt(st.nextToken());
				costs[i] = price;
			}
		}
		int[] dp = new int[costLimit+1];
		dp[0]=1;
		for(int onObject = 1; onObject<=numObject; onObject++) 
			for(int moneyUsed = costLimit; moneyUsed>=costs[onObject]; moneyUsed--) 
				dp[moneyUsed] = dp[moneyUsed] + dp[moneyUsed-costs[onObject]];
		System.out.println(dp[costLimit]);
	}
}
