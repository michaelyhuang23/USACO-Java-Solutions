import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1074 {
	static class Pair implements Comparable<Pair>{
		public int x, y;
		public Pair(int r, int c) {
			x=r;
			y=c;
		}
		public int compareTo(Pair other) {
			return y-other.y;
		}
	}
	static int maxScore=0;
	static int[] colOpen;
	static int[] rowOpen;
	static boolean[][] colNumFilled;
	static boolean[][] rowNumFilled;
	static boolean[][][] squareNumFilled;
	static int[][] currentBoard;
	static int[][] scoreBoard;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		colOpen=new int[9];
		rowOpen=new int[9];
		Arrays.fill(colOpen, 9);
		Arrays.fill(rowOpen, 9);

		colNumFilled=new boolean[9][10];
		rowNumFilled=new boolean[9][10];
		squareNumFilled=new boolean[3][3][10];
		currentBoard=new int[9][9];
		scoreBoard=new int[9][9];
		for(int r=0;r<9;r++) 
			for(int c=0;c<9;c++) 
				scoreBoard[r][c]=10-Math.max(Math.abs(r-4), Math.abs(c-4));		
		for(int r=0;r<9;r++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for(int c=0;c<9;c++) {
				int num=Integer.parseInt(st.nextToken());
				currentBoard[r][c]=num;
				if(num>0) {
					rowNumFilled[r][num]=true;
					colNumFilled[c][num]=true;
					squareNumFilled[r/3][c/3][num]=true;
					colOpen[c]--;
					rowOpen[r]--;
				}
			}
		}

		permute();
		if(maxScore==0)
			System.out.println(-1);
		else
			System.out.println(maxScore);
	}
	
	public static Pair findNewPos() {
		int minr=-1,minc=-1;
		int minOpen=Integer.MAX_VALUE;
		for(int r=0;r<9;r++) {
			if(rowOpen[r]==0)
				continue;
			for(int c=0;c<9;c++) {
				if(colOpen[c]==0 || currentBoard[r][c]!=0)
					continue;
				if(rowOpen[r]+colOpen[c]<minOpen) {
					minOpen=rowOpen[r]+colOpen[c];
					minr=r;
					minc=c;
				}
			}
		}

		return new Pair(minr,minc);
	}
	
	public static void permute() {

		Pair newPos = findNewPos();
		int newRow = newPos.x;
		int newCol = newPos.y;
		if(newRow==-1 && newCol==-1) {
			int newResult=0;
			for(int r=0;r<9;r++) {
				for(int c=0;c<9;c++)
					newResult+=currentBoard[r][c]*scoreBoard[r][c];
			}
			maxScore = Math.max(newResult, maxScore);
			return;
		}

		for(int number=1;number<=9;number++) {
			if(rowNumFilled[newRow][number] || colNumFilled[newCol][number] || squareNumFilled[newRow/3][newCol/3][number])
				continue;

			colOpen[newCol]--;
			rowOpen[newRow]--;
			currentBoard[newRow][newCol]=number;
			rowNumFilled[newRow][number]=true;
			colNumFilled[newCol][number]=true;
			squareNumFilled[newRow/3][newCol/3][number]=true;
			permute();
			colOpen[newCol]++;
			rowOpen[newRow]++;
			currentBoard[newRow][newCol]=0;
			rowNumFilled[newRow][number]=false;
			colNumFilled[newCol][number]=false;
			squareNumFilled[newRow/3][newCol/3][number]=false;
		}
	}
}
