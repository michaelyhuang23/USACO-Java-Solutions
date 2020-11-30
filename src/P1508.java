import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1508 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int[][] map = new int[row+1+1][col+1];
		for(int r = 1; r<=row; r++) {
			st = new StringTokenizer(f.readLine());
			for(int c = 1; c<=col; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[row+1+1][col+1];
		for(int c=1;c<=col;c++)
			dp[1][c] = map[1][c];
		for(int r = 2; r<=row+1; r++) 
			for(int c = 1; c<=col; c++) {
				dp[r][c] = Math.max(dp[r][c],dp[r-1][c]+map[r][c]);
				if(c>0)
					dp[r][c] = Math.max(dp[r][c],dp[r-1][c-1]+map[r][c]);
				if(c<col)
					dp[r][c] = Math.max(dp[r][c],dp[r-1][c+1]+map[r][c]);
			}
		System.out.println(dp[row+1][col/2+1]);
			
	}
}
