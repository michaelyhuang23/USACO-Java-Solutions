import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4265_REDO {
	static int numTile, numBoot;
	static int[] snowDepth;
	static int[] bootDepth;
	static int[] bootStep;
	static boolean[][] callVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numTile = Integer.parseInt(st.nextToken());
		numBoot = Integer.parseInt(st.nextToken());
		snowDepth = new int[numTile+1];
		bootDepth = new int[numBoot+1];
		bootStep = new int[numBoot+1];
		callVisited = new boolean[numTile+1][numBoot+1];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<numTile;i++) {
			snowDepth[i+1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<numBoot;i++) {
			st = new StringTokenizer(f.readLine());
			int depth = Integer.parseInt(st.nextToken());
			int step = Integer.parseInt(st.nextToken());
			bootDepth[i+1]=depth;
			bootStep[i+1]=step;
		}
		callVisited[1][0]=true;
		dfs(1,0);
		for(int i=1; i<=numBoot; i++) {
			if(callVisited[numTile][i]) {
				System.out.println(i-1);
				return;
			}
				
		}
	}
	private static void dfs(int pos, int bootOn) {
		if(bootOn<numBoot && !callVisited[pos][bootOn+1]) {
			callVisited[pos][bootOn+1]=true;
			dfs(pos, bootOn+1);
		}
		if(snowDepth[pos]>bootDepth[bootOn]) 
			return;
		for(int nextP = Math.min(pos+bootStep[bootOn],numTile); nextP > pos; nextP--) {
			if(snowDepth[nextP]>bootDepth[bootOn] || callVisited[nextP][bootOn])
				continue;
			callVisited[nextP][bootOn]=true;
			dfs(nextP,bootOn);
		}
	}
}
