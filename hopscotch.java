import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hopscotch {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hopscotch.in")); //new FileReader("hopscotch.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		int numRow, numCol, number;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRow = Integer.parseInt(st.nextToken());
		numCol = Integer.parseInt(st.nextToken());
		number = Integer.parseInt(st.nextToken());
		int[][] map = new int[numRow][numCol];
		for(int r=0;r<numRow;r++) {
			st = new StringTokenizer(f.readLine());
			for(int c=0;c<numCol;c++) 
				map[r][c]=Integer.parseInt(st.nextToken());
		}
		
		int[][] numWaysTo = new int[numRow][numCol];
		numWaysTo[0][0]=1;
		for(int r=0;r<numRow;r++) {
			for(int c=0;c<numCol;c++){
				for(int newR = r+1; newR<numRow; newR++) {
					for(int newC = c+1; newC<numCol; newC++) {
						if(map[newR][newC]!=map[r][c]) {
							numWaysTo[newR][newC]+=numWaysTo[r][c];
							numWaysTo[newR][newC]%=1000000007;
						}
					}
				}
			}
		}
		out.println(numWaysTo[numRow-1][numCol-1]%1000000007);
		out.close();
	}
}
