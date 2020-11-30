import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P1140 {
	static HashMap<Character, Integer> keyToNum = new HashMap<Character, Integer>();
	public static void main(String[] args) throws IOException {
		keyToNum.put(' ', 0);
		keyToNum.put('A', 1);
		keyToNum.put('C', 2);
		keyToNum.put('G', 3);
		keyToNum.put('T', 4);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int[][] values = {{0,-3,-4,-2,-1},{-3,5,-1,-2,-1},{-4,-1,5,-3,-2},{-2,-2,-3,5,-2},{-1,-1,-2,-2,5}};
		StringTokenizer st = new StringTokenizer(f.readLine());
		int first = Integer.parseInt(st.nextToken());
		String firstStr = st.nextToken();
		st = new StringTokenizer(f.readLine());
		int second = Integer.parseInt(st.nextToken());
		String secondStr = st.nextToken();
		int[][] dp = new int[first+1][second+1];
		for(int i=1;i<=second;i++) {
			int numSecond = keyToNum.get(secondStr.charAt(i-1));
			dp[0][i]=dp[0][i-1]+values[0][numSecond];
		}
		for(int i=1;i<=first;i++) {
			int numFirst = keyToNum.get(firstStr.charAt(i-1));
			dp[i][0]=dp[i-1][0]+values[numFirst][0];
		}
		for(int onFirst = 1; onFirst<=first; onFirst++) {
			for(int onSecond = 1; onSecond<=second; onSecond++) {
				int numFirst = keyToNum.get(firstStr.charAt(onFirst-1));
				int numSecond = keyToNum.get(secondStr.charAt(onSecond-1));
				int value1 = dp[onFirst-1][onSecond-1]+values[numFirst][numSecond];
				int value2 = dp[onFirst-1][onSecond]+values[numFirst][0];
				int value3 = dp[onFirst][onSecond-1]+values[0][numSecond];
				dp[onFirst][onSecond] = Math.max(value1, Math.max(value2, value3));
			}
		}
		System.out.println(dp[first][second]);
		
	}
}
