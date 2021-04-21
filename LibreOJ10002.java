import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LibreOJ10002 {
	static class Interval implements Comparable<Interval>{
		double start, end;
		public Interval(double st, double e) {
			start=st;
			end=e;
		}
		@Override
		public int compareTo(Interval o) {
			return Double.compare(start, o.start);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numGroup = Integer.parseInt(f.readLine());
		for(int round=0; round<numGroup; round++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int numSprayer = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			double widthHalf = Integer.parseInt(st.nextToken())/2.0;
			Interval[] allSprayers = new Interval[numSprayer];
			for(int i=0;i<numSprayer;i++) {
				st = new StringTokenizer(f.readLine());
				double x = Integer.parseInt(st.nextToken());
				double radius = Integer.parseInt(st.nextToken());
				double displacement = Math.sqrt(radius*radius-widthHalf*widthHalf);
				allSprayers[i] = new Interval(x-displacement, x+displacement);
			}
			Arrays.sort(allSprayers);
			if(allSprayers[0].start>0) {
				System.out.println(-1);
				continue;
			}
			double currentEnd=0;
			int counter=0;

			for(int i=0;i<numSprayer;i++) {
				int maxI=-1;
				double maxEnd=0;
				while(i<numSprayer && currentEnd>=allSprayers[i].start) {
					if(allSprayers[i].end>maxEnd) {
						maxEnd = allSprayers[i].end;
						maxI = i;
					}
					i++;
				}
				i--;
				if(maxI==-1) {
					counter=-1;
					break;
				}
				counter++;
				currentEnd=maxEnd;
				if(currentEnd>=length)
					break;
			}
			if(currentEnd<length)
				System.out.println(-1);
			else
				System.out.println(counter);
		}
	}
}
