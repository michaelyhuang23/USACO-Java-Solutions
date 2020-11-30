import java.io.*;
import java.util.*;

/**
input
5 4
0 0 1 0
0 0 0 0
0 0 1 0
0 1 0 0
0 0 0 1
1 1 4 3
output
7
 */
public class DFSSample {
	// direction
	static final int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int row, col;
	static int sx, sy, ex, ey;
	static int min = Integer.MAX_VALUE;
	static int[][] maze;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		maze = new int[row][col];
		visited = new boolean[row][col];
		for(int i=0; i<row; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<col; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(in.readLine());
		sx = Integer.parseInt(st.nextToken())-1;
		sy = Integer.parseInt(st.nextToken())-1;
		ex = Integer.parseInt(st.nextToken())-1;
		ey = Integer.parseInt(st.nextToken())-1;
        
//		long stime = System.currentTimeMillis();
		visited[sx][sy]=true;
		dfs(sx, sy, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println("无法到达终点");
		} else {
			System.out.println(min);
		}
		
//		long etime = System.currentTimeMillis();
//		System.out.println("time cost:" + (etime-stime) + "ms");
		
	}
	
	static void dfs(int x, int y, int step) {
		// 判断是否到达终点
		if(x==ex && y==ey) {
			min = Math.min(min, step);
			return;
		}
		
		// 剪枝优化
		if(step>=min) {
			return;
		}
		
		// 枚举每一种可能的下一步情况
		for(int i=0; i<4; i++) {
			// 计算下一个位置的坐标
			int nx = x + DIRECTION[i][0];
			int ny = y + DIRECTION[i][1];
			
			// 判断是否越界
			if(nx<0 || nx >= row || ny <0 || ny>=col) {
				continue;
			}
			
			// 障碍物 或者 已访问过
			if(maze[nx][ny] == 1 || visited[nx][ny]) {
				continue;
			}
			
			visited[nx][ny] = true;
			dfs(nx, ny, step+1); // 千万别写成step++
			visited[nx][ny] = false; // 回溯  back trace
		}
		
	}
}