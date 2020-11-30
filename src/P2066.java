import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2066 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine().trim());
		int numComp = Integer.parseInt(st.nextToken());
		int numMachine = Integer.parseInt(st.nextToken());
		int[][] values = new int[numComp + 1][numMachine + 1];
		for (int i = 1; i <= numComp; i++) {
			st = new StringTokenizer(f.readLine().trim());
			for (int j = 1; j <= numMachine; j++) {
				if (!st.hasMoreTokens())
					st = new StringTokenizer(f.readLine().trim());
				values[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dp = new int[numMachine + 1];
		int[][] dp2 = new int[numComp + 1][numMachine + 1];
		for (int comp = 1; comp <= numComp; comp++) {
			for (int machAllowed = numMachine; machAllowed >= 1; machAllowed--) {
				for (int machThis = 0; machThis <= machAllowed; machThis++) {
					if (dp[machAllowed - machThis] + values[comp][machThis] >= dp[machAllowed]) {
						dp[machAllowed] = dp[machAllowed - machThis] + values[comp][machThis];
						dp2[comp][machAllowed] = machThis;
					}
				}
			}
		}
		int mach = numMachine;
		Stack<Integer> machs = new Stack<>();
		for (int comp = numComp; comp > 0; comp--) {
			machs.push(dp2[comp][mach]);
			mach = mach - dp2[comp][mach];
		}

		System.out.println(dp[numMachine]);

		for (int comp = 1; comp <= numComp; comp++) {
			System.out.println(comp + " " + machs.pop());
		}

	}
}
