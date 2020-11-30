import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1049 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int costLimit = Integer.parseInt(f.readLine());
		int numObject = Integer.parseInt(f.readLine());
		int[] costs = new int[numObject+1];
		for(int i=1;i<=numObject;i++) {
			int price = Integer.parseInt(f.readLine());
			costs[i] = price;
		}
		boolean[] dp = new boolean[costLimit+1];
		dp[0]=true;
		for(int allowedObj = 1; allowedObj<=numObject; allowedObj++) 
			for(int moneyAllowed = costLimit; moneyAllowed>=costs[allowedObj]; moneyAllowed--) 
				dp[moneyAllowed] |= dp[moneyAllowed-costs[allowedObj]];
		for(int i=costLimit; i>=1; i--)
			if(dp[i]) {
				System.out.println(costLimit-i);
				return;
			}
	}
}
