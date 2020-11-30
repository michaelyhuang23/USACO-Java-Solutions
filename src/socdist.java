import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class socdist {
	static class Interval implements Comparable<Interval>{
		long start, end;
		public Interval(long s, long e) {
			start=s;
			end=e;
		}
		@Override
		public int compareTo(Interval o) {
			return Long.compare(start, o.start);
		}

	}
	static Interval[] allGrass;
	static int numGrass, numCow;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("socdist.in")); //new FileReader("socdist.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		numGrass = Integer.parseInt(st.nextToken());
		allGrass = new Interval[numGrass];
		long minX=Long.MAX_VALUE, maxX=0;
		for(int i=0;i<numGrass;i++) {
			st = new StringTokenizer(f.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			allGrass[i]=new Interval(x,y);
			minX = Math.min(minX, x);
			maxX = Math.max(maxX, y);
		}
		
		Arrays.sort(allGrass);
		long leftBound = 1, rightBound = (maxX-minX)/(numCow-1);
		long currentAns=0;
		while(leftBound<=rightBound) {
			long mid = (leftBound+rightBound)/2;
			if(check(mid)) {
				currentAns=mid;
				leftBound=mid+1;
			}else
				rightBound=mid-1;
		}
		out.println(currentAns);
		out.close();
		f.close();
		
	}
	private static boolean check(long mid) {
		int currCow=0;
		long lastCow=-mid;
		for(int i=0;i<numGrass;i++) {
			long range = allGrass[i].end-Math.max(allGrass[i].start,lastCow+mid);
			//System.out.println(mid+" "+range);
			if(range<0)
				continue;
			currCow+=range/mid+1;
			lastCow = allGrass[i].end-range%mid;
			if(currCow>=numCow)
				return true;
		}
		return false;
		
	}
}
