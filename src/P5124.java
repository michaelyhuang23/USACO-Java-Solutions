import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5124 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numCow = Integer.parseInt(st.nextToken());
		int maxConsect = Integer.parseInt(st.nextToken());
		int[] skills = new int[numCow+1];
		for(int i=1;i<=numCow;i++)
			skills[i] = Integer.parseInt(f.readLine());
		
		int[] dp = new int[numCow+1];
		dp[1] = skills[1];
		for(int afterCow = 2; afterCow<=numCow; afterCow++) {
			int currentMax = skills[afterCow];
			for(int cowNum = 1; cowNum<=Math.min(afterCow,maxConsect); cowNum++) {
				currentMax = Math.max(currentMax, skills[afterCow-cowNum+1]);
				dp[afterCow] = Math.max(dp[afterCow], dp[afterCow-cowNum]+currentMax*cowNum);
			}
		}
		System.out.println(dp[numCow]);
	}
}
