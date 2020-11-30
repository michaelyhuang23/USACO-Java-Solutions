import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1006 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int[][] map = new int[row][col];
		for(int r = 0; r<row; r++) {
			st = new StringTokenizer(f.readLine());
			for(int c = 0; c<col; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		int[][][][] dp = new int[row][col][row][col];
		for(int step = 1; step<=row+col-2; step++)
			for(int r1 = 0; r1<=Math.min(row-1, step); r1++)
				for(int r2 = 0; r2<=Math.min(row-1, step); r2++) {
					if(r1==r2 && step!=row+col-2)
						continue;
					int c1 = step - r1;
					int c2 = step - r2;
					if(c1>=col || c2>=col)
						continue;
					if(r1>0 && r2>0)
						dp[r1][c1][r2][c2] = Math.max(dp[r1][c1][r2][c2], dp[r1-1][c1][r2-1][c2]+map[r1][c1]+map[r2][c2]);
					if(r1>0 && c2>0)
						dp[r1][c1][r2][c2] = Math.max(dp[r1][c1][r2][c2], dp[r1-1][c1][r2][c2-1]+map[r1][c1]+map[r2][c2]);
					if(c1>0 && r2>0)
						dp[r1][c1][r2][c2] = Math.max(dp[r1][c1][r2][c2], dp[r1][c1-1][r2-1][c2]+map[r1][c1]+map[r2][c2]);
					if(c1>0 && c2>0)
						dp[r1][c1][r2][c2] = Math.max(dp[r1][c1][r2][c2], dp[r1][c1-1][r2][c2-1]+map[r1][c1]+map[r2][c2]);
				}
		System.out.println(dp[row-1][col-1][row-1][col-1]);
	}
}
