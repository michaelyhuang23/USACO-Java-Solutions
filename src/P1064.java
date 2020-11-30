import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1064 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int costLimit = Integer.parseInt(st.nextToken())/10;
		int numObject = Integer.parseInt(st.nextToken());
		int[][] costs = new int[numObject+1][3];
		int[][] values = new int[numObject+1][3];
		int[] objCount = new int[numObject+1];
		for(int i=1;i<=numObject;i++) {
			st = new StringTokenizer(f.readLine());
			int price = Integer.parseInt(st.nextToken())/10;
			int importance = Integer.parseInt(st.nextToken());
			int belongTo = Integer.parseInt(st.nextToken());
			if(belongTo==0) {
				costs[i][0]=price;
				values[i][0]=price*importance;
				objCount[i]++;
			}else {
				costs[belongTo][objCount[belongTo]]=price;
				values[belongTo][objCount[belongTo]]=price*importance;
				objCount[belongTo]++;
			}
		}
		for(int i=1;i<=numObject;i++) 
			for(int j=1;j<objCount[i];j++) {
				costs[i][j]+=costs[i][j-1];
				values[i][j]+=values[i][j-1];
			}
		
		
		
		int[] dp = new int[costLimit+1];
		for(int allowedObj = 1; allowedObj<=numObject; allowedObj++) 
			for(int moneyAllowed = costLimit; moneyAllowed>=costs[allowedObj][0]; moneyAllowed--) 
				for(int sub=0; sub<objCount[allowedObj]; sub++) {
					if(moneyAllowed<costs[allowedObj][sub])
						break;
					dp[moneyAllowed] = Math.max(dp[moneyAllowed], dp[moneyAllowed-costs[allowedObj][sub]]+values[allowedObj][sub]);
				}
		System.out.println(dp[costLimit]*10);
	}
}
