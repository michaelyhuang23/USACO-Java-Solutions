import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2758 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String origin = f.readLine();
		String target = f.readLine();
		
		int[][] dp = new int[origin.length()+1][target.length()+1];
		for(int tar=0; tar<=target.length();tar++) 
			dp[0][tar]=tar;
		for(int org=0; org<=origin.length();org++) 
			dp[org][0]=org;
		for(int org = 1; org <= origin.length(); org++) {
			for(int tar = 1; tar <= target.length(); tar++) {
				if(origin.charAt(org-1)==target.charAt(tar-1)) 
					dp[org][tar] = dp[org-1][tar-1];
				else
					dp[org][tar] = Math.min(dp[org-1][tar-1], Math.min(dp[org-1][tar], dp[org][tar-1]))+1;
			}
		}
		System.out.println(dp[origin.length()][target.length()]);
	}
}
