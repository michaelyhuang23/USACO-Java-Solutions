import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1004 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(f.readLine());
		int[][] map = new int[size+1][size+1];
		while(true) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			if(row==0 && col==0 && val==0)
				break;
			map[row][col]=val;
		}
		int[][][][] dp = new int[size+1][size+1][size+1][size+1];
		for(int r1 = 1; r1<=size; r1++) {
			for(int c1 = 1; c1<=size; c1++) {
				for(int r2 = 1; r2<=size; r2++) {
					for(int c2 = 1; c2<=size; c2++) {
						dp[r1][c1][r2][c2] = Math.max(Math.max(dp[r1][c1-1][r2][c2-1], dp[r1][c1-1][r2-1][c2]), Math.max(dp[r1-1][c1][r2-1][c2], dp[r1-1][c1][r2][c2-1]))+map[r1][c1]+map[r2][c2];
						if(r1==r2 && c1==c2)
							dp[r1][c1][r2][c2]-=map[r1][c1];
					}
				}
			}
		}
		System.out.println(dp[size][size][size][size]);
	}
}
