import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1162 {
	static int[][] DIR = {{0,1},{1,0},{-1,0},{0,-1}};
	static int[][] map;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(f.readLine());
		map = new int[size][size];
		for(int r=0;r<size;r++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for(int c=0;c<size;c++) {
				map[r][c] = Integer.parseInt(st.nextToken())==1 ? 1 : 2;
			}
		}
		
		for(int c=0;c<size;c++) {
			if(map[0][c]!=2)
				continue;
			map[0][c]=0;
			floodfill(0, c);
		}
		for(int c=0;c<size;c++) {
			if(map[size-1][c]!=2)
				continue;
			map[size-1][c]=0;
			floodfill(size-1, c);
		}
		for(int r=0;r<size;r++) {
			if(map[r][0]!=2)
				continue;
			map[r][0]=0;
			floodfill(r, 0);
		}
		for(int r=0;r<size;r++) {
			if(map[r][size-1]!=2)
				continue;
			map[r][size-1]=0;
			floodfill(r, size-1);
		}
		
		for(int r=0;r<size;r++) {
			StringBuilder str = new StringBuilder();
			for(int c=0;c<size;c++)
				str.append(map[r][c]+" ");
			str.deleteCharAt(str.length()-1);
			System.out.println(str);
		}
	}
	private static void floodfill(int r, int c) {
		for(int i=0;i<4;i++) {
			int newR = r + DIR[i][0];
			int newC = c + DIR[i][1];
			if(newR<0 || newC<0 || newR>=size || newC>=size)
				continue;
			if(map[newR][newC]!=2)
				continue;
			map[newR][newC]=0;
			floodfill(newR, newC);
		}
	}
}
