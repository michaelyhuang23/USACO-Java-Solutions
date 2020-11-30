import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OpenJudge2718 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		long[][] waysToGo = new long[m+1][n+1];
		for(int i=0;i<=m;i++) 
			waysToGo[i][1]=1;
		for(int i=0;i<=n;i++) 
			waysToGo[1][i]=1;
		for(int i=2; i<=m; i++) 
			for(int j=2; j<=n; j++) 
				waysToGo[i][j] = waysToGo[i-1][j]+waysToGo[i][j-1];
		System.out.println(waysToGo[m][n]);
		
	}
}
