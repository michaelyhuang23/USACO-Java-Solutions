import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class taming {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("taming.in")); //new FileReader("taming.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		int numDay = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] origLog = new int[numDay+1];
		for(int i=1;i<=numDay;i++)
			origLog[i] = Integer.parseInt(st.nextToken());
		int[][] prep = new int[numDay+1][numDay+1];
		for(int start = 1; start<=numDay; start++) {
			for(int end = start; end<=numDay; end++) {
				prep[start][end]=prep[start][end-1] + ((end-start)==origLog[end] ? 0 : 1);
			}
		}
		int[][] dp = new int[numDay+1][numDay+1];
		for(int i=0; i<=numDay;i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE/2);
		dp[0][0] = 0;
		for(int day = 1; day <= numDay; day++) {
			for(int numBreak = 1; numBreak <= numDay; numBreak++) {
				int numDiff = 0;
				for(int lastDay = numBreak-1; lastDay < day; lastDay++) {
					numDiff = prep[lastDay+1][day] + dp[lastDay][numBreak-1];
					dp[day][numBreak] = Math.min(dp[day][numBreak], numDiff);
				}
			}
		}
		for(int i=1;i<=numDay;i++) {
			out.println(dp[numDay][i]);
		}
		out.close();
	}
}
