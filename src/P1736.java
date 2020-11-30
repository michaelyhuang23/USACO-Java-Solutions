import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1736 {
	public static void main(String[] args) throws IOException, InterruptedException {
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
		int[][] leftZero = new int[numRow+1][numCol+1];
		int[][] upZero = new int[numRow+1][numCol+1];
		int max = 0;
		for(int r=1; r<=numRow; r++) {
			for(int c=1; c<=numCol; c++) {
				if(map[r][c]==1) {
					leftUp[r][c]=Math.min(leftUp[r-1][c-1], Math.min(leftZero[r][c-1], upZero[r-1][c]))+1;
					leftZero[r][c]=0;
					upZero[r][c]=0;
					max = Math.max(max, leftUp[r][c]);
				}else {
					leftZero[r][c]=leftZero[r][c-1]+1;
					upZero[r][c]=upZero[r-1][c]+1;
				}
			}
		}
		leftUp=null;
		leftZero=null;
		int[][] rightUp = new int[numRow+1][numCol+2];
		int[][] rightZero = new int[numRow+1][numCol+2];
		for(int r=1; r<=numRow; r++) {
			for(int c=numCol; c>0; c--) {
				if(map[r][c]==1) {
					rightUp[r][c]=Math.min(rightUp[r-1][c+1], Math.min(rightZero[r][c+1], upZero[r-1][c]))+1;
					rightZero[r][c]=0;
					max = Math.max(max, rightUp[r][c]);
				}else {
					rightZero[r][c]=rightZero[r][c+1]+1;
				}
			}
		}
		
		System.out.println(max);
		
	}
}
