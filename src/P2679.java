import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2679 {
	final static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int lengthA = Integer.parseInt(st.nextToken());
		int lengthB = Integer.parseInt(st.nextToken());
		int numChunks = Integer.parseInt(st.nextToken());
		String strA = " " + f.readLine();
		String strB = " " + f.readLine();
		int[][] dp = new int[lengthB + 1][numChunks + 1];
		int[][] dpMustUse = new int[lengthB + 1][numChunks + 1];
		dp[0][0]=1;
		dpMustUse[0][0] = 1;

		for (int posOnA = 1; posOnA <= lengthA; posOnA++)
			for (int posOnB = lengthB; posOnB >0; posOnB--)
				for (int chunkUsed = 1; chunkUsed <= numChunks; chunkUsed++) {
					if (strA.charAt(posOnA) == strB.charAt(posOnB)) {
						dpMustUse[posOnB][chunkUsed] = (dpMustUse[posOnB - 1][chunkUsed] + dp[posOnB - 1][chunkUsed - 1])%MOD;
						dp[posOnB][chunkUsed] = (dp[posOnB][chunkUsed]+ dpMustUse[posOnB][chunkUsed])%MOD;
					}else {
						dpMustUse[posOnB][chunkUsed]=0;
					}
				}
		
		System.out.println(dp[lengthB][numChunks]);
	}
}
