import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2196 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numHole = Integer.parseInt(f.readLine());
		int[] mineInHole = new int[numHole];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0;i<numHole;i++)
			mineInHole[i]=Integer.parseInt(st.nextToken());
		
		boolean[][] connectionTo = new boolean[numHole][numHole];
		for(int i=0;i<numHole-1;i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=i+1;j<numHole;j++) 
				connectionTo[i][j] = st.nextToken().charAt(0)=='1' ? true : false;
		}
		
		
		int[] dp = mineInHole.clone();
		int[] parent = new int[numHole];
		for(int i=0;i<numHole;i++)
			parent[i]=i;
		//initialize
		
		for(int i=0;i<numHole;i++) {
			for(int j=i+1;j<numHole;j++) {
				if(connectionTo[i][j] && dp[i]+mineInHole[j] > dp[j]) {
					dp[j] = dp[i]+mineInHole[j];
					parent[j]=i;
				}
			}
		}
		int maxMine = 0;
		int endHole = 0;
		for(int i=0;i<numHole;i++) {
			if(maxMine<dp[i]) {
				maxMine = dp[i];
				endHole = i;
			}
		}
		Stack<Integer> holes = new Stack<>();
		for(int hole = endHole; true; hole = parent[hole]) {
			holes.push(hole);
			if(hole == parent[hole])
				break;
		}
		StringBuilder str = new StringBuilder();
		while(!holes.isEmpty()) {
			str.append(holes.pop()+1);
			str.append(' ');
		}
		str.deleteCharAt(str.length()-1);
		System.out.println(str);
		
		System.out.println(maxMine);
		
		
	}
}
