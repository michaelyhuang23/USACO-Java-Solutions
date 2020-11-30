import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int length = Integer.parseInt(st.nextToken());
		int numCard = Integer.parseInt(st.nextToken());
		int[] scores = new int[length+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=length;i++)
			scores[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] cardCount = new int[5];
		for(int i=1;i<=numCard;i++)
			cardCount[Integer.parseInt(st.nextToken())]++;
		int[][][][] dp = new int[cardCount[1]+1][cardCount[2]+1][cardCount[3]+1][cardCount[4]+1];
		
		for(int first=0; first<dp.length;first++)
			for(int second=0; second<dp[0].length; second++)
				for(int third=0; third<dp[0][0].length; third++)
					for(int fourth=0; fourth<dp[0][0][0].length; fourth++) {
						int position = 1+first+second*2+third*3+fourth*4;
						if(first>0)
							dp[first][second][third][fourth] = Math.max(dp[first][second][third][fourth], dp[first-1][second][third][fourth]+scores[position]);
						if(second>0)
							dp[first][second][third][fourth] = Math.max(dp[first][second][third][fourth], dp[first][second-1][third][fourth]+scores[position]);
						if(third>0)
							dp[first][second][third][fourth] = Math.max(dp[first][second][third][fourth], dp[first][second][third-1][fourth]+scores[position]);
						if(fourth>0)
							dp[first][second][third][fourth] = Math.max(dp[first][second][third][fourth], dp[first][second][third][fourth-1]+scores[position]);
					}
		System.out.println(dp[dp.length-1][dp[0].length-1][dp[0][0].length-1][dp[0][0][0].length-1]+scores[1]);
	}
}
