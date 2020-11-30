import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3609 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numGame = Integer.parseInt(st.nextToken());
		int numChange = Integer.parseInt(st.nextToken());
		int[] FJcards = new int[numGame+1];
		for(int g=1;g<=numGame;g++) {
			char card = f.readLine().charAt(0);
			if(card=='H')
				FJcards[g] = 1;
			if(card=='P')
				FJcards[g] = 2;
			if(card=='S')
				FJcards[g] = 3;
		}
		int[][][] dp = new int[numGame+1][numChange+1][4];
		for(int game=1; game<=numGame; game++) {
			dp[game][0][1]=dp[game-1][0][1]+match(1,FJcards[game]);
			dp[game][0][2]=dp[game-1][0][2]+match(2,FJcards[game]);
			dp[game][0][3]=dp[game-1][0][3]+match(3,FJcards[game]);
		}
		for(int game = 1; game<=numGame; game++) 
			for(int changeUsed = 1; changeUsed<=numChange; changeUsed++) {
				dp[game][changeUsed][1] = Math.max(dp[game-1][changeUsed][1],
						Math.max(dp[game-1][changeUsed-1][2],dp[game-1][changeUsed-1][3]))+match(1,FJcards[game]);
				dp[game][changeUsed][2] = Math.max(dp[game-1][changeUsed][2],
						Math.max(dp[game-1][changeUsed-1][1],dp[game-1][changeUsed-1][3]))+match(2,FJcards[game]);
				dp[game][changeUsed][3] = Math.max(dp[game-1][changeUsed][3],
						Math.max(dp[game-1][changeUsed-1][2],dp[game-1][changeUsed-1][1]))+match(3,FJcards[game]);
			}
		int max = 0;
		for(int i=1;i<=3;i++)
			max = Math.max(max, dp[numGame][numChange][i]);
		System.out.println(max);
	}
	

	private static int match(int i, int j) {
		if(i-j==-2)
			return 1;
		if(i-j==1)
			return 1;
		return 0;
	}
}
