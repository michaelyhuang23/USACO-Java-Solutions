import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF707Div1C {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] A = new int[R][C];
		int[][] B = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < C; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < C; j++) 
				B[i][j] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] used = new boolean[C];
		while(true) {
			for (int c = 0; c < C; c++) {
				if(used[c])
					continue;
				
			}
		}
	}
}
