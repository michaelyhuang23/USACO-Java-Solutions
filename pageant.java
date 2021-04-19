import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class pageant {
	static int[][] map;
	final static int[][] DIR = {{0,1},{1,0},{-1,0},{0,-1}};
	static int numRow, numCol;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("pageant.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pageant.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRow = Integer.parseInt(st.nextToken());
		numCol = Integer.parseInt(st.nextToken());
		map = new int[numRow][numCol];
		for(int r=0;r<numRow;r++) {
			String input = f.readLine();
			for(int c=0;c<numCol;c++) 
				map[r][c] = (input.charAt(c)=='X') ? -1 : 0;
		}
		int count=1;
		for(int r=0;r<numRow;r++) {
			for(int c=0;c<numCol;c++) {
				if(map[r][c]==-1) {
					floodFill(r,c,count);
					count++;
				}
			}
		}
		int threeWayConnect = 10000;
		int[][] connection = new int[4][4];
		for(int i=1;i<4;i++)
			for(int j=1;j<4;j++)
				connection[i][j]=100000;
		for(int r=0;r<numRow;r++) {
			for(int c=0;c<numCol;c++) {
				if(map[r][c]>0)
					continue;
				int[] toBlockDist = shortPathTo(r,c);
				for(int i=1;i<4;i++) {
					for(int j=1;j<4;j++) {
						connection[i][j] = Math.min(toBlockDist[i]+toBlockDist[j]+1, connection[i][j]);
					}
					threeWayConnect = Math.min(threeWayConnect, toBlockDist[1]+toBlockDist[2]+toBlockDist[3]+1);
				}
				
			}
		}
		int first = connection[1][2]+connection[2][3];
		int second = connection[1][2]+connection[1][3];
		int third = connection[2][3]+connection[1][3];
		
		System.out.println(Math.min(Math.min(first, threeWayConnect), Math.min(second, third)));
		//out.close();
		f.close();
	}
	static boolean[][] visited;
	private static int[] shortPathTo(int r, int c) {
		int[] toBlockDist = new int[4];
		boolean[] toBlockComplete = new boolean[4];
		int blockToVisit=3;
		toBlockComplete[0]=true;
		visited = new boolean[numRow][numCol];
		Queue<Integer> frontier = new ArrayDeque<>();
		frontier.offer(r);
		frontier.offer(c);
		visited[r][c]=true;
		int[][] stepCounter=new int[numRow][numCol];
		while(!frontier.isEmpty()) {
			int thisR = frontier.poll();
			int thisC = frontier.poll();
			for(int i=0;i<4;i++) {
				int newR = thisR+DIR[i][0];
				int newC = thisC+DIR[i][1];
				if(newR<0 || newR>=numRow || newC<0 || newC>=numCol)
					continue;
				if(visited[newR][newC])
					continue;
				stepCounter[newR][newC]=stepCounter[thisR][thisC]+1;
				if(!toBlockComplete[map[newR][newC]]) {
					toBlockDist[map[newR][newC]]=stepCounter[thisR][thisC];
					toBlockComplete[map[newR][newC]]=true;
					blockToVisit--;
					if(blockToVisit==0)
						return toBlockDist;
				}
				visited[newR][newC]=true;
				frontier.offer(newR);
				frontier.offer(newC);
			}
		}
		return new int[]{10000,10000,10000,10000};
	}
	private static void floodFill(int r, int c, int count) {
		map[r][c]=count;
		for(int i=0;i<4;i++) {
			int newR = r+DIR[i][0];
			int newC = c+DIR[i][1];
			if(newR<0 || newR>=numRow || newC<0 || newC>=numCol)
				continue;
			if(map[newR][newC]!=-1)
				continue;
			floodFill(newR, newC, count);
		}
	}
	
	
}
