import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pails2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pails.in")); //new FileReader("pails.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int firstPail = Integer.parseInt(st.nextToken());
		int secondPail = Integer.parseInt(st.nextToken());
		int numOp = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		boolean[][][] dp = new boolean[numOp+1][firstPail+1][secondPail+1];
		dp[0][0][0] = true;
		for(int op=0;op<numOp;op++) {
			for(int pail1M=0;pail1M<=firstPail;pail1M++) {
				for(int pail2M=0;pail2M<=secondPail;pail2M++) {
					if(!dp[op][pail1M][pail2M])
						continue;
					if(pail1M<firstPail) {
						dp[op+1][firstPail][pail2M]=true;
					}
					if(pail2M<secondPail) {
						dp[op+1][pail1M][secondPail]=true;
					}
					if(pail1M>0) {
						dp[op+1][0][pail2M]=true;
					}
					if(pail2M>0) {
						dp[op+1][pail1M][0]=true;
					}
					if(pail1M<firstPail && pail2M>0) { // pour one to other
						dp[op+1][firstPail][pail2M]=true;
					}
				}
			}
		}
	}
}
