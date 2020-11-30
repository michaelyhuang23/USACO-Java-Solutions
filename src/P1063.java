import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1063 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numBead = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] allBeads = new int[numBead*2+1];
		for(int bead=1; bead<=numBead; bead++) {
			allBeads[bead] = Integer.parseInt(st.nextToken());
			allBeads[bead+numBead] = allBeads[bead];
		}
		int[][] dp = new int[numBead*2+1][numBead*2+1];

		for(int start = numBead*2; start>0; start--) {
			for(int end = start+1; end<=numBead*2; end++) {
				for(int mid = start; mid<end; mid++) {
					int lastBead;
					if(end+1>numBead*2)
						lastBead = allBeads[1];
					else
						lastBead = allBeads[end+1];
					dp[start][end] = Math.max(dp[start][end], dp[start][mid]+dp[mid+1][end]+allBeads[start]*allBeads[mid+1]*lastBead);
				}
			}
		}
		int max = 0;
		for(int start = 1; start<=numBead; start++) 
			max = Math.max(dp[start][start+numBead-1],max);
		System.out.println(max);
	}
}
