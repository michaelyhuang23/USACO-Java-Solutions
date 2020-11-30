import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3956 {
	static int[][] board;
	static int[][] boardSolu;
	static final int[][] DIR = {{1,0},{0,1},{-1,0},{0,-1}};
	static int boardSize;
	static boolean[][] visited;
	static int coinUsage = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		int numColor = Integer.parseInt(st.nextToken());
		board = new int[boardSize][boardSize];
		boardSolu = new int[boardSize][boardSize];
		visited = new boolean[boardSize][boardSize];
		for(int i=0;i<boardSize;i++)
			Arrays.fill(boardSolu[i], Integer.MAX_VALUE/2);
		for(int i=0;i<numColor;i++) {
			st = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st.nextToken())-1;
			int col = Integer.parseInt(st.nextToken())-1;
			int color = Integer.parseInt(st.nextToken())+1;
			board[row][col] = color;
		}
		
		dfs(0,0,false);
		if(boardSolu[boardSize-1][boardSize-1]>=Integer.MAX_VALUE/2-1)
			System.out.println(-1);
		else
			System.out.println(boardSolu[boardSize-1][boardSize-1]);
		
	}
	private static void dfs(int r, int c, boolean usingMagic) {
		if(coinUsage>=boardSolu[r][c])
			return;
		boardSolu[r][c]=coinUsage;
		int thisColor = board[r][c];
		if(usingMagic)
			board[r][c]=0;
		for(int i=0;i<4;i++) {
			int newR = r+DIR[i][0];
			int newC = c+DIR[i][1];
			if(newR<0 || newR>=boardSize || newC<0 || newC>=boardSize)
				continue;
			if(visited[newR][newC])
				continue;
			visited[newR][newC]=true;
			if(board[newR][newC]==0) {
				if(!usingMagic) {
					coinUsage+=2;
					board[newR][newC]=board[r][c];
					//System.out.println(r+" "+c+"  "+newR+" "+newC);
					dfs(newR,newC,true);
					board[newR][newC]=0;
					coinUsage-=2;
				}
			}else if(board[newR][newC]==thisColor) {
				dfs(newR,newC,false);
			}else { 
				coinUsage++;
				dfs(newR,newC,false);
				coinUsage--;
			}
			visited[newR][newC]=false;
		}
	}
}
