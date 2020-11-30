
import java.io.*;
import java.util.*;

public class gatesTODO {
	// direction
	static final int[][] DIRECTION = { { -1, 0 },
			                           { 1, 0 },
			                           { 0, -1 },
			                           { 0, 1 } };
	static int n;
	static boolean[][] visited;
	static int counter=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));

		n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		boolean[][] isFence = new boolean[n*4][n*4];
		int currentX=n*2,currentY=n*2;
		visited= new boolean[4*n][4*n];
		for(int i=0;i<n;i++){
			switch(s.charAt(i)){
				case 'N':
					visited[--currentY][currentX]=true;
					visited[--currentY][currentX]=true;
					break;
				case 'S':
					visited[++currentY][currentX]=true;
					visited[++currentY][currentX]=true;
					break;
				case 'E':
					visited[currentY][++currentX]=true;
					visited[currentY][++currentX]=true;
					break;
				case 'W':
					visited[currentY][--currentX]=true;
					visited[currentY][--currentX]=true;
					break;
			}
		}

		for(int row=0;row<4*n;row++){
			for(int col=0;col<4*n;col++){
				if(visited[row][col] || isFence[row][col])
					continue;
				bfs(row,col);
				counter++;
			}
		}
		// TODO
		pw.println(counter-1);
		br.close();
		pw.close();
	}

	static void bfs(int i, int j) {
		Queue<Point> toBeVisited = new ArrayDeque<>();
		toBeVisited.offer(new Point(i,j));
		visited[i][j]=true;
		while(!toBeVisited.isEmpty()){
			Point thisPoint = toBeVisited.poll();
			for(int dir=0;dir<4;dir++){
				int newR = thisPoint.r+DIRECTION[dir][0];
				int newC = thisPoint.c+DIRECTION[dir][1];
				if(newR<0 || newR>=4*n || newC<0 || newC>= 4*n)
					continue;
				if(visited[newR][newC])
					continue;
				toBeVisited.offer(new Point(newR,newC));
				visited[newR][newC]=true;
			}
		}

	}

	static class Point {
		public int r, c;

		public Point(int rIn, int cIn) {
			r = rIn;
			c = cIn;
		}
	}

}