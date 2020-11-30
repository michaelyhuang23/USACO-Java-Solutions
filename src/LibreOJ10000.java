import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LibreOJ10000{
	static class Interval implements Comparable<Interval>{
		int start, end;
		public Interval(int st, int e) {
			start=st;
			end=e;
		}
		@Override
		public int compareTo(Interval o) {
			return end-o.end;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numInterv = Integer.parseInt(f.readLine());
		Interval[] allIntervs = new Interval[numInterv];
		for(int i=0;i<numInterv;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			allIntervs[i] = new Interval(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(allIntervs);
		int currentEnd = -1;
		int counter=0;
		for(int i=0;i<numInterv;i++) {
			if(allIntervs[i].start>=currentEnd) {
				currentEnd = allIntervs[i].end;
				counter++;
			}
		}
		System.out.println(counter);
	}
}
