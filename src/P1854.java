import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1854 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numFlower = Integer.parseInt(st.nextToken());
		int numVase = Integer.parseInt(st.nextToken());
		int[][] values = new int[numFlower+1][numVase+1];
		for(int i=1;i<=numFlower; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=1; j<=numVase; j++) 
				values[i][j] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[numFlower+1];
		Arrays.fill(dp, Integer.MIN_VALUE/2);
		dp[0]=0;
		int[][] parent = new int[numVase+1][numFlower+1];
		for(int onVase = 1; onVase<=numVase; onVase++) 
			for(int onFlower = numFlower; onFlower>0; onFlower--) {
				if(dp[onFlower]<dp[onFlower-1]+values[onFlower][onVase]) {
					dp[onFlower]=dp[onFlower-1]+values[onFlower][onVase];
					parent[onVase][onFlower]=onFlower-1;
				}else {
					parent[onVase][onFlower]=onFlower;
				}				
			}
		int[] flowerToVase = new int[numFlower+1];
		Arrays.fill(flowerToVase, Integer.MAX_VALUE/3);
		System.out.println(dp[numFlower]);
		int currentFlower = numFlower;
		for(int vase=numVase; vase>0; vase--) {
			int nextFlower = parent[vase][currentFlower];
			if(nextFlower<currentFlower)
				flowerToVase[currentFlower]=vase;
			currentFlower=nextFlower;
		}
		StringBuilder str = new StringBuilder();
		for(int flower = 1; flower<=numFlower; flower++)
			str.append(flowerToVase[flower]+" ");
		str.deleteCharAt(str.length()-1);
		System.out.println(str);
	}
}
