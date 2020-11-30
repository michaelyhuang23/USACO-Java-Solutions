import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1434 {
	static int[][] heights;
	static int numRow, numCol;
	static int[][] distanceDown;
	static final int[][] DIR = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRow = Integer.parseInt(st.nextToken());
		numCol = Integer.parseInt(st.nextToken());
		distanceDown=new int[numRow][numCol];
		heights = new int[numRow][numCol];
		for(int r=0; r<numRow; r++) {
			st = new StringTokenizer(f.readLine());
			for(int c=0; c<numCol; c++) 
				heights[r][c] = Integer.parseInt(st.nextToken());
			Arrays.fill(distanceDown[r],-1);
		}
		int max = 0;
		for(int r=0; r<numRow; r++) {
			for(int c=0; c<numCol; c++) {
				if(distanceDown[r][c]==-1)
					distanceDown[r][c] = dfs(r,c);
			}
		}
		for(int r=0; r<numRow; r++) 
			for(int c=0; c<numCol; c++) 
				max = Math.max(max, distanceDown[r][c]);
	
		System.out.println(max+1);
		
	}
	private static int dfs(int r, int c) {
		if(distanceDown[r][c]>=0)
			return distanceDown[r][c];
		int distance = 0;
		for(int i = 0; i<4; i++) {
			int newR = r + DIR[i][0];
			int newC = c + DIR[i][1];
			if(newR<0 || newR>=numRow || newC<0 || newC>=numCol)
				continue;
			if(heights[newR][newC]>=heights[r][c])
				continue;
			distance = Math.max(distance, dfs(newR,newC)+1);
		}
		distanceDown[r][c]=distance;
		return distance;
	}
}
