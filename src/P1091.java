import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1091 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numStudent = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] heights = new int[numStudent];
		for(int i=0;i<numStudent;i++) 
			heights[i] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[numStudent][numStudent];
		// initialize
		for(int i=0;i<numStudent;i++)
			dp[i][i]=1;
		for(int currStu = 0; currStu<numStudent; currStu++) {
			for(int turnStu = 0; turnStu<numStudent; turnStu++) {
				if(currStu<=turnStu) {
					int maxLength = 0;
					for(int j=0;j<currStu;j++) 
						if(heights[j]<heights[currStu] && dp[j][turnStu]>maxLength)
							maxLength = dp[j][turnStu];
					dp[currStu][turnStu] = maxLength+1;
				}else {
					int maxLength = 0;
					for(int j=turnStu;j<currStu;j++) 
						if(heights[j]>heights[currStu] && dp[j][turnStu]>maxLength)
							maxLength = dp[j][turnStu];
					dp[currStu][turnStu] = maxLength+1;
				}
			}
		}
		int maxLength = 0;
		for(int i=0;i<numStudent;i++)
			for(int j=0;j<numStudent;j++)
				maxLength = Math.max(maxLength, dp[i][j]);
		System.out.println(numStudent-maxLength);
		
	}
}
