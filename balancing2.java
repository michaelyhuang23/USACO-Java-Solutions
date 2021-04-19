import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class balancing2 {
	static class Cow implements Comparable<Cow>{
		int x, y;
		public Cow(int xi, int yi) {
			x=xi;
			y=yi;
		}
		@Override
		public int compareTo(Cow o) {
			return x-o.x;
		}
		
	}
	static Cow[] allCows;
	static int numCow;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("balancing.in")); //new FileReader("balancing.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		numCow = Integer.parseInt(f.readLine());
		allCows = new Cow[numCow];
		for(int i=0;i<numCow;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			allCows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(allCows);
		int[] xPoints = new int[numCow];
		int colCount=0;
		xPoints[0]=allCows[0].x;
		colCount++;
		for(int i=0;i<numCow;i++) {
			if(allCows[i].x == xPoints[colCount-1])
				continue;
			xPoints[colCount]=allCows[i].x;
			colCount++;
		}
		int realMinMax=numCow;
		for(int i=0;i<colCount-1;i++) {
			int yUpBound=1000000, yLowBound=0;
			int lastMidY = 0;
			int midY = 1000000;
			int minMax = numCow;
			while(lastMidY!=midY) {
				lastMidY = midY;
				midY = (yUpBound+yLowBound)/2;
				int[] maxes = getMax(midY,xPoints[i]);
				minMax = Math.min(minMax, Math.max(maxes[0], maxes[1]));
				if(maxes[0]>maxes[1]) {yUpBound=midY;}
				else yLowBound=midY;
			}
			realMinMax = Math.min(realMinMax, minMax);
			
		}
		out.println(realMinMax);
		out.close();
		
	}
	private static int[] getMax(int midY,int midX) {
		int[][] cohortCount = {{0,0},{0,0}};
		for(int i=0;i<numCow;i++) {
			if(allCows[i].x>=midX && allCows[i].y>=midY)
				cohortCount[1][1]++;
			if(allCows[i].x<midX && allCows[i].y<midY)
				cohortCount[0][0]++;
			if(allCows[i].x>=midX && allCows[i].y<midY)
				cohortCount[0][1]++;
			if(allCows[i].x<midX && allCows[i].y>=midY)
				cohortCount[1][0]++;
		}
		int[] result = {Math.max(cohortCount[0][0], cohortCount[0][1]),Math.max(cohortCount[1][0], cohortCount[1][1])};
		return result;
	}
}
