import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LibreOJ10001 {
	static class Interval implements Comparable<Interval>{
		int start, end, requireNum;
		public Interval(int st, int e, int t) {
			start=st;
			end=e;
			requireNum=t;
		}
		@Override
		public int compareTo(Interval o) {
			return end-o.end;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int length = Integer.parseInt(f.readLine().trim());
		int numInterv = Integer.parseInt(f.readLine().trim());
		Interval[] allIntervs = new Interval[numInterv];
		for(int i=0;i<numInterv;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int tree = Integer.parseInt(st.nextToken());
			allIntervs[i] = new Interval(start,end,tree);
		}
		boolean[] allTrees = new boolean[length+1];
		int lastEnd = 0;
		Arrays.sort(allIntervs);
		int treeCount=0;
		for(int i=0;i<numInterv;i++) {
			int treeNeed;
			treeNeed = allIntervs[i].requireNum;
			for(int j=allIntervs[i].start;j<=lastEnd;j++)
				if(allTrees[j])
					treeNeed--;

			int j=allIntervs[i].end;
			while(treeNeed>0) {
				while(allTrees[j]) 
					j--;
				allTrees[j]=true;
				treeCount++;
				treeNeed--;
			}
			lastEnd = allIntervs[i].end;
		}
		System.out.println(treeCount);
	}
}
