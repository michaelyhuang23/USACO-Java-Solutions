import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;


public class perimeter {
	static int gridLength;
	static boolean[][] icy;
	static boolean[][] visited;
	static int bestArea=0, bestPeri=0; 
	static final int[][] DIRECTION = { { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOSilverPracticeGraph/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		gridLength = Integer.parseInt(f.readLine());
		icy=new boolean[gridLength][gridLength];
		visited=new boolean[gridLength][gridLength];
		for(int r=0;r<gridLength;r++) {
			String line = f.readLine();
			for(int c=0;c<gridLength;c++) {
				icy[r][c]=line.charAt(c)=='#';
			}
		}
		for(int r=0;r<gridLength;r++) {
			for(int c=0;c<gridLength;c++) {
				if(!icy[r][c] || visited[r][c])
					continue;
				bfs(r,c);
			}
		}
		out.println(bestArea+" "+bestPeri);
		out.close();
		f.close();
	}
	public static void bfs(int r, int c) {
		Queue<Point> toBeVisited = new ArrayDeque<>();
		toBeVisited.offer(new Point(r,c));
		visited[r][c]=true;
		int thisPeri=0, thisArea=0;
		while(!toBeVisited.isEmpty()) {
			Point node = toBeVisited.poll();
			thisArea++;
			for(int i=0;i<4;i++) {
				int newR = node.r+DIRECTION[i][0];
				int newC = node.c+DIRECTION[i][1];
				if(newR<0 || newR>=gridLength || newC<0 || newC>=gridLength || !icy[newR][newC]) { //the perimeter is not what the usual sense perimeter is, 
					thisPeri++;													//it counts one non-ice twice if there were two ice blocks adjacent to it
					continue;
				}
				if(visited[newR][newC])
					continue;
				visited[newR][newC]=true;
				toBeVisited.offer(new Point(newR,newC));
				
			}
		}
		if(thisArea>bestArea || thisArea==bestArea && thisPeri<bestPeri) {
			bestArea=thisArea;
			bestPeri=thisPeri;
		}
	}
	static class Point{
		public int r,c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
