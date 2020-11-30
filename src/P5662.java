import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5662 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numDay = Integer.parseInt(st.nextToken());
		int numMemo = Integer.parseInt(st.nextToken());
		int[][] memoPrices = new int[numMemo+1][numDay+1];
		int[][] memoValues = new int[numMemo+1][numDay+1];
		int initialMoney = Integer.parseInt(st.nextToken());
		for(int i = 1; i<=numDay; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j = 1; j<=numMemo; j++) {
				memoPrices[j][i] = Integer.parseInt(st.nextToken());
				memoValues[j][i-1] = memoPrices[j][i] - memoPrices[j][i-1];
			}
		}
		int moneyToday = initialMoney;

		for(int date = 2; date <= numDay; date++) {
			int[] dp = new int[moneyToday+1];

			for(int memo = 1; memo <= numMemo; memo++) {
				for(int moneyAllowed=memoPrices[memo][date-1]; moneyAllowed<=moneyToday; moneyAllowed++) {
					dp[moneyAllowed] = Math.max(dp[moneyAllowed], dp[moneyAllowed-memoPrices[memo][date-1]]+memoValues[memo][date-1]);
				}
			}
			moneyToday+=dp[moneyToday];
		}
		System.out.println(moneyToday);
		
	}
}
