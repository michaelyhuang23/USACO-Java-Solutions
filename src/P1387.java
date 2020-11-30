import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1387 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numRow = Integer.parseInt(st.nextToken());
		int numCol = Integer.parseInt(st.nextToken());
		int[][] map = new int[numRow+1][numCol+1];
		for(int r=1; r<=numRow; r++) {
			st = new StringTokenizer(f.readLine());
			for(int c=1;c<=numCol;c++) 
				map[r][c]=Integer.parseInt(st.nextToken());
		}
		int[][] leftUp = new int[numRow+1][numCol+1];
		int max=0;
		for(int r=1;r<=numRow;r++) {
			for(int c=1;c<=numCol;c++) {
				if(map[r][c]==1)
					leftUp[r][c] = Math.min(leftUp[r-1][c-1], Math.min(leftUp[r][c-1], leftUp[r-1][c]))+1;
				else
					leftUp[r][c]=0;
				max = Math.max(max, leftUp[r][c]);
			}
		}
		System.out.println(max);
	}
}
