import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class mooyomooyo {
	static int[][] board;
	static int minBlock,numRow;
	static boolean[][] visited;
	static boolean[][] overallVisited;
	static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mooyomooyo.in")); //new FileReader("mooyomooyo.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRow = Integer.parseInt(st.nextToken());
		minBlock = Integer.parseInt(st.nextToken());
		board = new int[numRow][10];
		
		for(int r=0;r<numRow;r++) {
			String input = f.readLine();
			for(int c=0;c<10;c++)
				board[r][c]=Character.getNumericValue(input.charAt(c));
		}

		
		while(clearBlock()) {
			applyGravity();
		}

		
		for(int r=0;r<numRow;r++) {
			for(int c=0;c<10;c++)
				out.print(board[r][c]);
			out.println();
		}
		out.close();
		f.close();
	}
	public static void applyGravity() {
		for(int col=0;col<10;col++) {
			int height=numRow-1;
			for(int row=numRow-1; row>=0; row--) {
				if(board[row][col]!=0) {
					int temp=board[row][col];
					board[row][col]=0;
					board[height][col]=temp;
					height--;
				}
			}
		}
	}
	public static boolean clearBlock() {
		overallVisited = new boolean[numRow][10];
		visited = new boolean[numRow][10];
		boolean success=false;
		for(int r=0; r<numRow; r++) {
			for(int c=0; c<10; c++) {
				if(board[r][c]==0 || overallVisited[r][c])
					continue;
				
				int numBlock = dfs(r,c);
				
				for(int newR=0;newR<numRow;newR++)
					for(int newC=0;newC<10;newC++)
						if(visited[newR][newC]!=overallVisited[newR][newC]) {
							if(numBlock>=minBlock) {
								board[newR][newC]=0;
								success=true;
							}
							overallVisited[newR][newC]=visited[newR][newC];
						}
			}
		}
		return success;
	}
	public static int dfs(int row, int col) {
		int counter=1;
		visited[row][col]=true;
		for(int i=0;i<4;i++) {
			int newR = row + DIR[i][0];
			int newC = col + DIR[i][1];
			if(newR<0 || newR>=numRow || newC<0 || newC>=10)
				continue;
			if(board[row][col]!=board[newR][newC] || visited[newR][newC])
				continue;
			counter+=dfs(newR,newC);
		}
		return counter;
	}
}
