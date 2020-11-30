import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P1020 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		ArrayList<Integer> targetHeights = new ArrayList<Integer>();
		while(st.hasMoreTokens()) 
			targetHeights.add(Integer.parseInt(st.nextToken()));
		
		int[] dp = new int[targetHeights.size()];
		dp[0]=1;
		// initialize
		for(int i=0;i<dp.length;i++) {
			int thisHeight = targetHeights.get(i);
			int maxLength = 0;
			for(int j=0;j<i;j++) 
				if(targetHeights.get(j)>=thisHeight && dp[j]>maxLength)
					maxLength=dp[j];
			dp[i] = maxLength+1;
		}
		int maxKill = 0;
		for(int i=0;i<dp.length;i++)
			maxKill=Math.max(maxKill, dp[i]);
		
		TreeSet<Integer> missiles = new TreeSet<>();
		for(int i=0;i<targetHeights.size();i++) {	//it's easily provable that there are no two missiles with same height
			int height = targetHeights.get(i);
			Integer missileToUse = missiles.ceiling(height);
			if(missileToUse==null) {
				missiles.add(height);
			}else {
				missiles.remove(missileToUse);
				missiles.add(height);
			}
			
		}
		
		System.out.println(maxKill);
		System.out.println(missiles.size());
		
	}
}
