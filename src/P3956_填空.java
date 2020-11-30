
import java.util.*;
import java.io.*;

public class P3956_填空
{
    public static int[][] board;       // board[i][j]存放该点原始颜色
    public static int m;
    public static boolean[][] booked;  // 记录当前路径中已走过的点
    public static int[][] dirArr = {   // 方向数组
            {0,1}, 
            {0,-1},
            {1,0},
            {-1,0}
    };
    public static int[][] tracker;   // 记录到当前点的最小值
    public static void main(String args[]) throws IOException
    {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(f.readLine());
        m = Integer.parseInt(tk.nextToken());
        int n = Integer.parseInt(tk.nextToken());
        board = new int[m][m];
        booked = new boolean[m][m];
        tracker = new int[m][m];
        for(int i = 0; i < tracker.length; i++)
        {
            Arrays.fill(tracker[i], Integer.MAX_VALUE); // 对一维数组赋值
        }
        
        //0: no color, 1: red, 2:yellow
        for(int i = 0; i < n; i++)
        {
            tk = new StringTokenizer(f.readLine());
            int xCo = Integer.parseInt(tk.nextToken()) -1; // 注意修正下标
            int yCo = Integer.parseInt(tk.nextToken()) -1;
            int color = Integer.parseInt(tk.nextToken());
            board[xCo][yCo] = color + 1; // 对输入的颜色值做处理，默认的0表示透明
        }
        
        booked[0][0] = true;
        dfs(0,0,0,board[0][0]);
        if(tracker[m-1][m-1] == Integer.MAX_VALUE)
        {
            System.out.println(-1);
        }else{
        	System.out.println(tracker[m-1][m-1]);
        }
    }

    public static void dfs(int r, int c, int currentCoins, int currentCol) {
  
    	if(currentCoins>=tracker[r][c]) {
    		return;
    	}else {
    		tracker[r][c]=currentCoins;
    	}
    	if(r==m-1 && c==m-1) {
    		return;
    	}
    	for(int i=0;i<4;i++) {
    		// 计算下一个位置的坐标
    		int nx = r + dirArr[i][0];
    		int ny = c + dirArr[i][1];

			// 判断是否越界
			if (nx < 0 || nx >= m || ny < 0 || ny >= m) {
				continue;
			}
			
			if(booked[nx][ny] || board[r][c]==0 && board[nx][ny]==0)
				continue;
			booked[nx][ny]=true;
			if(board[nx][ny]==0) {
				dfs(nx,ny,currentCoins+2,currentCol);
			}else {
				if(board[nx][ny]!=currentCol) {
					dfs(nx,ny,currentCoins+1,board[nx][ny]);
				}else {
					dfs(nx,ny,currentCoins,board[nx][ny]);
				}
			}
			booked[nx][ny]=false;
    					
    	}
    }

}