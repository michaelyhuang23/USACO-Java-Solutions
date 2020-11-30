import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class maxcross {
	static int[] breaks;
	static int numBreak, leastCross, numCross;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maxcross.in")); //new FileReader("maxcross.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCross = Integer.parseInt(st.nextToken());
		leastCross = Integer.parseInt(st.nextToken());
		numBreak = Integer.parseInt(st.nextToken());
		breaks = new int[numBreak];
		for(int i=0;i<numBreak;i++)
			breaks[i]=Integer.parseInt(f.readLine())-1;
		Arrays.sort(breaks);
		int maxBrokenSpace=breaks[0];
		for(int i=1;i<numBreak;i++) {
			maxBrokenSpace=Math.max(maxBrokenSpace, breaks[i]-breaks[i-1]-1);
		}
		maxBrokenSpace=Math.max(maxBrokenSpace, numCross-breaks[numBreak-1]-1);
		if(maxBrokenSpace>=leastCross) {
			out.println(0);
			out.close();
			System.exit(0);
		}
		int fixLeftBound=0, fixRightBound=numBreak;
		int currentMinFix=numBreak;
		while(fixLeftBound<=fixRightBound) {
			int midFix = (fixLeftBound+fixRightBound)/2;
			if(check(midFix)) {currentMinFix=midFix; fixRightBound=midFix-1;}
			else fixLeftBound=midFix+1;
		}

		out.println(currentMinFix);
		out.close();
		f.close();
	}

	private static boolean check(int midFix) {
		for(int startFix=0; startFix+midFix<=numBreak; startFix++) {
			int numSpace;
			if(midFix==numBreak)
				numSpace = numCross;
			else if(startFix==0)
				numSpace=breaks[startFix+midFix];
			else if(startFix+midFix==numBreak)
				numSpace = numCross-breaks[startFix-1]-1;
			else
				numSpace = breaks[startFix+midFix]-breaks[startFix-1]-1;
			//System.out.println(numSpace);
			if(numSpace>=leastCross)
				return true;
		}
		return false;
	}
}
