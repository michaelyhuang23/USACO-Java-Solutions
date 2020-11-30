import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class socdist1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("socdist1.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACO4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist1.out")));
		
		int numStalls = Integer.parseInt(f.readLine());
		String strStalls = f.readLine();
		boolean[] stalls = new boolean[numStalls];
		for(int i=0;i<numStalls;i++) 
			stalls[i]=strStalls.charAt(i)=='1' ? true : false;
		int firstLength=numStalls*2-2, lastLength=0, LongLen=0, subLongLen=0, currentD = Integer.MAX_VALUE;
		int previousStallIndex = -1;
		for(int i=0;i<numStalls;i++) {
			if(stalls[i]) {
				if(previousStallIndex==-1)
					firstLength = i;
				else {
					if(i-previousStallIndex>LongLen) {
						subLongLen=LongLen;
						LongLen=i-previousStallIndex;
					}else if(i-previousStallIndex>subLongLen)
						subLongLen = i-previousStallIndex;
					currentD = Math.min(currentD, i-previousStallIndex);
				}
				previousStallIndex = i;
			}
		}
		if(previousStallIndex!=-1)
			lastLength = numStalls - previousStallIndex-1;
		
		int newD = 0;
		newD = Math.max(newD, firstLength/2);
		newD = Math.max(newD, lastLength/2);
		newD = Math.max(newD, Math.min(firstLength, lastLength));
		newD = Math.max(newD, Math.min(Math.max(firstLength, lastLength), LongLen/2));
		newD = Math.max(newD, LongLen/3);
		newD = Math.max(newD, Math.min(LongLen/2, subLongLen/2));
		
		out.println(Math.min(newD, currentD));
		
		f.close();
		out.close();
	}
}
