import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class helpcross {
	static class Interval implements Comparable<Interval>{
		int start, end;
		public Interval(int s, int e) {
			start=s;
			end=e;
		}
		@Override
		public int compareTo(Interval o) {
			//System.out.println(Integer.compare(end, o.end)+" "+Integer.compare(start, o.start));
			if(Integer.compare(end, o.end)==Integer.compare(start, o.start))
				return Integer.compare(end, o.end);
			else if(Integer.compare(end, o.end)==-Integer.compare(start, o.start))
				return end-o.end;
			else {
				if(end==o.end)
					return start-o.start;
				return end-o.end;
			}
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("helpcross.in")); //new FileReader("helpcross.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		int numChick, numCow;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numChick = Integer.parseInt(st.nextToken());
		numCow = Integer.parseInt(st.nextToken());
		int[] chickT = new int[numChick];
		Interval[] cowT = new Interval[numCow];
		for(int i=0;i<numChick;i++)
			chickT[i] = Integer.parseInt(f.readLine());
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			cowT[i] = new Interval(start,end);
		}
		Arrays.sort(cowT);
		Arrays.sort(chickT);
		int pairCount=0;
		boolean[] chickUsed = new boolean[numChick];
		for(int cowI = 0; cowI<numCow; cowI++) {
			int index=Arrays.binarySearch(chickT, cowT[cowI].start);
			int endIndex = Arrays.binarySearch(chickT, cowT[cowI].end);
			if(index<0)
				index=-index-1;
			if(endIndex<0)
				endIndex=-endIndex-2;
			while(index<numChick && chickUsed[index] && index<=endIndex) 
				index++;
			if(index>=numChick || index>endIndex)
				continue;
			pairCount++;
			chickUsed[index]=true;
		}
		out.println(pairCount);
		out.close();
		f.close();
	}
}
