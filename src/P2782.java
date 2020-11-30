import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2782 {
	static class Pair implements Comparable<Pair>{
		int first, second;
		public Pair(int f, int s) {
			first = f;
			second = s;
		}
		@Override
		public int compareTo(Pair o) {
			return first-o.first;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCity = Integer.parseInt(f.readLine());
		Pair[] allCities = new Pair[numCity];
		for(int i=0;i<numCity;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			allCities[i]=new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(allCities);
		
		int[] dp = new int[numCity];
		// initialize
		dp[0]=1;
		
		for(int i=0;i<numCity;i++) {
			int maxLength = 0;
			for(int j=0;j<i;j++)
				if(allCities[j].second<allCities[i].second && dp[j]>maxLength)
					maxLength = dp[j];
			dp[i] = maxLength+1;
		}
		
		int maxLength = 0;
		for(int i=0;i<numCity;i++)
			maxLength=Math.max(dp[i],maxLength);
		
		System.out.println(maxLength);
		
	}
}
