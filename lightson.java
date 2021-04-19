import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lightson {
	final static int[][] DIR = {{1,0},{0,1},{-1,0},{0,-1}};
	static class Pair{
		int row, col;
		public Pair(int r, int c) {
			row=r;
			col=c;
		}
	}
	static ArrayList<Pair>[][] roomConnect;
	static boolean[][] lit;
	static boolean[][] visited;
	static int gridLength;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lightson.in")); //new FileReader("lightson.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		gridLength = Integer.parseInt(st.nextToken());
		int numConnect = Integer.parseInt(st.nextToken());
		roomConnect = new ArrayList[gridLength][gridLength];
		lit = new boolean[gridLength][gridLength];
		visited = new boolean[gridLength][gridLength];
		for(int i=0;i<gridLength;i++)
			for(int j=0;j<gridLength;j++)
				roomConnect[i][j]=new ArrayList<Pair>();
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int startcol = Integer.parseInt(st.nextToken())-1;
			int startrow = Integer.parseInt(st.nextToken())-1;
			int endcol = Integer.parseInt(st.nextToken())-1;
			int endrow = Integer.parseInt(st.nextToken())-1;
			roomConnect[startrow][startcol].add(new Pair(endrow,endcol));
		}
		visited[0][0]=true;
		lit[0][0]=true;
		dfs(0,0);
		int lightCounter=0;
		for(int i=0;i<gridLength;i++)
			for(int j=0;j<gridLength;j++)
				if(lit[i][j])
					lightCounter++;
		out.println(lightCounter);
		out.close();
		f.close();
	}
	private static void dfs(int r, int c) {
		for(Pair toLight : roomConnect[r][c]) {
			boolean firstTimeOn = lit[toLight.row][toLight.col]==false;
			lit[toLight.row][toLight.col]=true;
			if(visited[toLight.row][toLight.col] && firstTimeOn)
				dfs(toLight.row,toLight.col);
		}
			
		for(int i=0;i<4;i++) {
			int newR = r+DIR[i][0];
			int newC = c+DIR[i][1];
			if(newR<0 || newR>=gridLength || newC<0 || newC>=gridLength || visited[newR][newC])
				continue;
			visited[newR][newC]=true;
			if(!lit[newR][newC])
				continue;
			dfs(newR,newC);
		}
	}
}
