import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class marathon {
	static int[] xpos, ypos;
	private static int distance(int first, int second) {
		return Math.abs(xpos[first]-xpos[second])+Math.abs(ypos[first]-ypos[second]);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("marathon.in"));  //new InputStreamReader(System.in)  //new FileReader("marathon.in") 
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		int numNode, numSkip;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numNode = Integer.parseInt(st.nextToken());
		numSkip = Integer.parseInt(st.nextToken());
		numSkip = Math.min(numSkip, numNode-2);
		int numActive = numNode-2-numSkip;
		xpos = new int[numNode];
		ypos = new int[numNode];

		for(int i=0;i<numNode;i++) {
			st = new StringTokenizer(f.readLine());
			xpos[i] = Integer.parseInt(st.nextToken());
			ypos[i] = Integer.parseInt(st.nextToken());
		}
		int[][][] dp = new int[numActive+2][numNode][numNode];
		for(int r=0;r<dp.length;r++) {
			for(int c=0;c<dp[0].length;c++)
				Arrays.fill(dp[r][c], Integer.MAX_VALUE/2);
		}
		dp[0][0][0]= distance(numNode-1, 0);
		dp[0][1][0] = dp[0][0][0];
		dp[1][1][0] = distance(numNode-1,1)+distance(0,1);
		//System.out.println("preprocessing");
		for(int activated=0; activated<=numActive; activated++) {
			for(int onNode=0; onNode<numNode-1; onNode++) {
				for(int lastActive=activated; lastActive<onNode; lastActive++) {
					if(dp[activated][onNode][lastActive]>Integer.MAX_VALUE/2-1000)
						continue;
					// not activate this Node
					dp[activated][onNode+1][lastActive] = Math.min(dp[activated][onNode+1][lastActive],
							dp[activated][onNode][lastActive]);
					// activate this Node
					int distChange = distance(lastActive,onNode)+distance(onNode, numNode-1)-
							distance(lastActive, numNode-1);
					dp[activated+1][onNode+1][onNode] = Math.min(dp[activated+1][onNode+1][onNode],
							dp[activated][onNode][lastActive]+distChange);
				}
			}
		}
		int minTime = Integer.MAX_VALUE;
		for(int i=0; i<numNode-1;i++)
			minTime = Math.min(minTime, dp[numActive][numNode-1][i]);
//		for(int j=0;j<dp[0].length;j++) {
//			for(int i=0;i<dp.length;i++) {
//				for(int k=0;k<dp[0][0].length;k++) {
//					if(j==numNode-2 && i==numActive)
//						System.out.print("!"+dp[i][j][k]+" ");
//					else
//						System.out.print(dp[i][j][k]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println();
//		}
			
		out.println(minTime);
		out.close();
		f.close();
	}
}
