import java.io.*;
import java.util.*;

public class P1346_EXAMPLE {
	public static void main(String[] args) throws IOException {	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n, s, e;
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		int[][] f = new int[n+1][n+1];
		int[] dis = new int[n+1];
		// initialization
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i==j) {
					f[i][j] = 0;
				} else {
					f[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(in.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j=1; j<=m; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(j==1) {
					f[i][x] = 0;
				} else {
					f[i][x] = 1;
				}
			}
		}

		for(int i=1; i<=n; i++) {
			dis[i] = f[s][i];
		}

		dis[s] = 0;
		boolean[] visited = new boolean[n+1];
		visited[s] = true;
		for(int i=1; i<=n-1; i++) {
			int min = Integer.MAX_VALUE;
			int k = 0;
			for(int j=1; j<=n; j++) {
				if(!visited[j] && dis[j] < min) {
					min = dis[j];
					k = j;
				}
			}
			
			visited[k] = true;
			for(int j=1; j<=n; j++) {
				if(!visited[j] && f[k][j] < Integer.MAX_VALUE) {
					dis[j] = Math.min(dis[j], dis[k] + f[k][j]);
				}
			}
		}
		
		if(dis[e] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dis[e]);
		}
		
	}
}