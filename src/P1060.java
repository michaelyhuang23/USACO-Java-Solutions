import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1060 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int costLimit = Integer.parseInt(st.nextToken());
		int numObject = Integer.parseInt(st.nextToken());
		int[] costs = new int[numObject+1];
		int[] values = new int[numObject+1];
		for(int i=1;i<=numObject;i++) {
			st = new StringTokenizer(f.readLine());
			int price = Integer.parseInt(st.nextToken());
			int importance = Integer.parseInt(st.nextToken());
			costs[i] = price;
			values[i] = price*importance;
		}
		int[] dp = new int[costLimit+1];
		for(int allowedObj = 1; allowedObj<=numObject; allowedObj++) 
			for(int moneyAllowed = costLimit; moneyAllowed>=costs[allowedObj]; moneyAllowed--) 
				dp[moneyAllowed] = Math.max(dp[moneyAllowed], dp[moneyAllowed-costs[allowedObj]]+values[allowedObj]);
		System.out.println(dp[costLimit]);
	}
}
