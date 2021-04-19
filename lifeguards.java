import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lifeguards {
	static class Interval implements Comparable<Interval>{
		int start, end, length;
		public Interval(int st, int en) {
			start=st;
			end=en;
			length=end-start;
		}
		public int compareTo(Interval other) {
			return start - other.start;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		int numInterv = Integer.parseInt(f.readLine());
		Interval[] allIntervs = new Interval[numInterv];
		StringTokenizer st;
		for(int i=0;i<numInterv;i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			allIntervs[i]=new Interval(start,end);
		}
		Arrays.sort(allIntervs);
		int shortestReduct = Math.min(allIntervs[1].start-allIntervs[0].start, allIntervs[numInterv-1].end-allIntervs[numInterv-2].end);
		for(int i=1;i<numInterv-1;i++) {
			shortestReduct=Math.min(shortestReduct, Math.min(allIntervs[i].end,allIntervs[i+1].start)-allIntervs[i-1].end);	
		}
		shortestReduct = Math.max(shortestReduct, 0);
		int totalLength=0;
		int currentPos=allIntervs[0].start;
		for(int i=0;i<numInterv;i++) {
			if(allIntervs[i].end-currentPos>0) {
				totalLength+=Math.min(allIntervs[i].end-currentPos, allIntervs[i].length);
				currentPos=allIntervs[i].end;
			}
		}
		out.println(totalLength-shortestReduct);
		f.close();
		out.close();
	}
}
