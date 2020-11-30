import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2196_REDO {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numHole = Integer.parseInt(f.readLine());
		int[] mineInHole = new int[numHole+1];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numHole;i++)
			mineInHole[i]=Integer.parseInt(st.nextToken());
		
		boolean[][] connectionTo = new boolean[numHole+1][numHole+1];
		for(int i=1;i<=numHole-1;i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=i+1;j<=numHole;j++) 
				connectionTo[i][j] = st.nextToken().charAt(0)=='1' ? true : false;
		}
		
		int[] dp = mineInHole.clone();
		int[] dp2 = new int[numHole+1];
		for(int hole = 1; hole<=numHole; hole++) {
			for(int prevHole = 1; prevHole < hole; prevHole++) {
				if(connectionTo[prevHole][hole])
					if(dp[hole]<dp[prevHole]+mineInHole[hole]) {
						dp[hole] = dp[prevHole]+mineInHole[hole];
						dp2[hole] = prevHole;
					}
			}
		}
		int maxMine = 0;
		for(int i=1; i<=numHole; i++)
			if(dp[maxMine]<dp[i])
				maxMine = i;
		Stack<Integer> holes = new Stack<>();
		for(int hole = maxMine; hole>0; hole = dp2[hole])
			holes.push(hole);
		StringBuilder str = new StringBuilder();
		while(!holes.isEmpty())
			str.append(holes.pop()+" ");
		str.deleteCharAt(str.length()-1);
		System.out.println(str);
		System.out.println(dp[maxMine]);
		
	}
}
