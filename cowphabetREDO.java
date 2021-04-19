import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class cowphabetREDO {
	private static int charInt(char chr) {
		return Character.getNumericValue(chr)-Character.getNumericValue('a');
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		int[] char2Int = new int[26];
		Arrays.fill(char2Int, -1);
		int[][] seq = new int[26][26];
		int count = 0;
		for (int i = 0; i < input.length(); i++) {
			int charInt = charInt(input.charAt(i));
			if(char2Int[charInt]==-1) {
				char2Int[charInt]=count;
				count++;
			}
			if(i>0) {
				int prevCharInt = charInt(input.charAt(i-1));
				seq[char2Int[prevCharInt]][char2Int[charInt]]++;
			}
		}

		
		int[] dp = new int[1<<count];
		Arrays.fill(dp, Integer.MAX_VALUE/2);
		dp[0]=0;
		for (int state = 0; state < (1<<count); state++) {
			for (int let = 0; let < count; let++) {
				if((state & (1<<let))>0)
					continue;
				int newState = state ^ (1<<let);
				int invertCount = 0;
				for (int prev = 0; prev < count; prev++) {
					if((newState & (1<<prev))==0)
						continue;
					invertCount+=seq[let][prev];
				}
				dp[newState] = Math.min(dp[newState], dp[state]+invertCount);
			}
		}
		
		System.out.println(dp[(1<<count)-1]+1);
		
	}
}
