import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2845 {
	static class Pos{
		public int row, col;
		public Pos(int r, int c) {
			row=r;
			col=c;
		}
	}
	static final int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N,M,counter;
	static boolean[][] maze;
	static boolean[][] visited;
	static ArrayList<Pos>[][] connections;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new boolean[N][N];
		visited = new boolean[N][N];
		connections = new ArrayList[N][N];
		for(int r=0;r<N;r++)
			for(int c=0;c<N;c++)
				connections[r][c]=new ArrayList<Pos>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			int thisR=Integer.parseInt(st.nextToken())-1, thisC=Integer.parseInt(st.nextToken())-1;
			connections[thisR][thisC].add(new Pos(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1));
		}
        
//		long stime = System.currentTimeMillis();
		maze[0][0]=true;
		counter=1;
		dfs(0, 0);
		System.out.println(counter);
//		long etime = System.currentTimeMillis();
//		System.out.println("time cost:" + (etime-stime) + "ms");
		
	}
	
	static void dfs(int r, int c) {
		if(visited[r][c])
			return;
		else
			visited[r][c]=true;
		for(Pos newPos:connections[r][c])
			if(!maze[newPos.row][newPos.col]) {
				maze[newPos.row][newPos.col]=true;
				counter++;
				for(int i=0;i<4;i++) {
					int nr = newPos.row + DIRECTION[i][0];
					int nc = newPos.col + DIRECTION[i][1];
					if(nr<0 || nr >= N || nc <0 || nc>=N) {
						continue;
					}
					if(visited[nr][nc]) {
						dfs(newPos.row,newPos.col);
						break;
					}
				}
			}
		// 枚举每一种可能的下一步情况
		for(int i=0; i<4; i++) {
			// 计算下一个位置的坐标
			int nr = r + DIRECTION[i][0];
			int nc = c + DIRECTION[i][1];
			
			// 判断是否越界
			if(nr<0 || nr >= N || nc <0 || nc>=N) {
				continue;
			}
			
			// 障碍物 或者 已访问过
			if(!maze[nr][nc]) {
				continue;
			}
			
			dfs(nr, nc); // 千万别写成step++
		}
		
	}
}
