import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4265 {
	static int numTile,numBoot;
	static int[] snowDepth;
	static int[] bootDepth;
	static int[] bootStep;
	static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		numTile = Integer.parseInt(st.nextToken());
		numBoot = Integer.parseInt(st.nextToken());
		snowDepth = new int[numTile];
		bootDepth = new int[numBoot];
		bootStep = new int[numBoot];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<numTile;i++)
			snowDepth[i]=Integer.parseInt(st.nextToken());
		for(int i=0;i<numBoot;i++) {
			st = new StringTokenizer(br.readLine());
			bootDepth[i] = Integer.parseInt(st.nextToken());
			bootStep[i] = Integer.parseInt(st.nextToken());
		}
		memo= new int[numTile][numBoot];
		for(int i=0;i<numTile;i++) 
			for(int j=0;j<numBoot;j++)
				memo[i][j]=-1;
		int firstStep=0;
		for(firstStep=0;firstStep<numTile;firstStep++)
			if(snowDepth[firstStep]>0)
				break;
		System.out.println(bestPath(firstStep-1,0));
		br.close();
	}
	
	public static int bestPath(int tileNum, int bootNum) { //both start from 0 and go to size-1
		if(tileNum>=numTile-1) {
			//System.out.println(tileNum+" "+bootNum);
			return bootNum;
		}
		if(bootNum>=numBoot || snowDepth[tileNum]>bootDepth[bootNum])
			return numBoot+1;
		if(memo[tileNum][bootNum]>-1)
			return memo[tileNum][bootNum];
		int nextBoot;
		for(nextBoot=bootNum+1; nextBoot<numBoot;nextBoot++)
			if(snowDepth[tileNum]<=bootDepth[nextBoot])
				break;
		int currentBest=bestPath(tileNum,nextBoot);
		for(int stepSize=bootStep[bootNum]; stepSize>0; stepSize--) {
			currentBest = Math.min(bestPath(tileNum+stepSize,bootNum),currentBest);
		}
		memo[tileNum][bootNum]=currentBest;
		return currentBest;
	}
	
}
