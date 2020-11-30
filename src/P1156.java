import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1156 {
	static class Garbage implements Comparable<Garbage>{
		int time, energy, depth;
		public Garbage(int t, int e, int d) {
			time = t;
			energy = e;
			depth = d;
		}
		@Override
		public int compareTo(Garbage o) {
			return time - o.time;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int totalDepth = Integer.parseInt(st.nextToken());
		int numGar = Integer.parseInt(st.nextToken());
		Garbage[] allGar = new Garbage[numGar+1];
		for(int i=1;i<=numGar;i++) {
			st = new StringTokenizer(f.readLine());
			int time = Integer.parseInt(st.nextToken());
			int energy = Integer.parseInt(st.nextToken());
			int depth = Integer.parseInt(st.nextToken());
			allGar[i] = new Garbage(time, energy, depth);
		}
		Arrays.sort(allGar,1,numGar+1);
		int[] dp = new int[totalDepth+1];
		dp[0]=10;
		for(int gar = 1; gar<=numGar; gar++) {
			for(int depth = totalDepth; depth>=0; depth--) {
				if(dp[depth]<allGar[gar].time) {
					continue;
				}
				if(depth+allGar[gar].depth>=totalDepth) {
					System.out.println(allGar[gar].time);
					return;
				}
				dp[depth+allGar[gar].depth] = Math.max(dp[depth],dp[depth+allGar[gar].depth]);
				dp[depth] += allGar[gar].energy;
			}
		}
		
		System.out.println(dp[0]);
		
	}
}
