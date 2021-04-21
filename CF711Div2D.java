import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF711Div2D {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] ts = new int[n+1];
		long[] xs = new long[n+1];
		int[] ys = new int[n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			int t = Integer.parseInt(st.nextToken());
			long x = Long.parseLong(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ts[i]=t;
			xs[i]=x;
			ys[i]=y;
		}
		
		int[][] dp = new int[n+1][m+1];
		int[][] dp2 = new int[n+1][m+1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0]=0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if(dp[i][j]==-1)
					continue;
				if(ts[i]==1) {
					int xnot = (int)(xs[i]/100000L);
					if(xnot*100000L!=xs[i])
						xnot++;
					int k = j+xnot;
					if(k<=m && dp[i][k]==-1 && dp2[i][j]<ys[i]) {
						dp[i][k]=i;
						dp2[i][k]=dp2[i][j]+1;
					}
				}else {
					long k = xs[i]*j/100000L;
					if(k*100000!=xs[i]*j)
						k++;
					if(k<= m && dp[i][(int)k]==-1 && dp2[i][j]<ys[i]) {
						dp[i][(int) k]=i;
						dp2[i][(int)k]=dp2[i][j]+1;
					}
				}
				if(i==n)
					continue;
				if(dp[i+1][j]==-1) 
					dp[i+1][j]=dp[i][j];
			}
		}
		for (int i = 1; i < m; i++) {
			System.out.print(dp[n][i]+" ");
		}
		System.out.println(dp[n][m]);
	}
}
