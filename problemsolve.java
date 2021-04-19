import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problemsolve {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int money = Integer.parseInt(st.nextToken());
		int numProb = Integer.parseInt(st.nextToken());
		int[] As = new int[numProb + 1];
		int[] Bs = new int[numProb + 1];
		for (int i = 1; i <= numProb; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			As[i] = As[i - 1] + a;
			Bs[i] = Bs[i - 1] + b;
		}
		int[][] dp = new int[numProb + 1][numProb + 1];
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE / 2);

		dp[0][0] = 0;
		for (int curLastProb = 1; curLastProb <= numProb; curLastProb++) {
			for (int lastProb = 0; lastProb <= curLastProb; lastProb++) {
				int curPay = As[curLastProb] - As[lastProb];
				if (curPay > money)
					continue;
				for (int lastlastProb = 0; lastlastProb <= lastProb; lastlastProb++) {
					int prePay = Bs[lastProb] - Bs[lastlastProb];
					if (curPay + prePay > money)
						continue;
					dp[curLastProb][lastProb] = Math.min(dp[curLastProb][lastProb], dp[lastProb][lastlastProb] + 1);
				}
			}
		}
		int min = Integer.MAX_VALUE / 2;
		for (int i = 0; i <= numProb; i++) {
			int addi = (Bs[numProb]-Bs[i])/money;
			if(addi*money<Bs[numProb]-Bs[i])
				addi++;
			min = Math.min(min, dp[numProb][i]+addi+1);
		}
		System.out.println(min);
	}
}
