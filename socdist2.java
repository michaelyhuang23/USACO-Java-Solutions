import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class socdist2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("socdist2.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACO4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist2.out")));
		int numCows = Integer.parseInt(f.readLine());
		int[] cowPos = new int[numCows];
		HashMap<Integer, Boolean> posToInfected = new HashMap<Integer, Boolean>();
		boolean[] cowInfected = new boolean[numCows];
		for(int i=0;i<numCows;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			cowPos[i] = Integer.parseInt(st.nextToken());
			posToInfected.put(cowPos[i], st.nextToken().equals("1") ? true : false);
		}
		Arrays.parallelSort(cowPos);
		for(int i=0;i<numCows;i++)
			cowInfected[i] = posToInfected.get(cowPos[i]);
		posToInfected=null;
		
		//finding max R
		int maxR = Integer.MAX_VALUE;
		for(int i=0;i<numCows;i++) {
			if(!cowInfected[i]) {
				if(i>0 && cowInfected[i-1])
					maxR = Math.min(maxR, cowPos[i]-cowPos[i-1]-1);
				if(i<numCows-1 && cowInfected[i+1])
					maxR = Math.min(maxR, cowPos[i+1]-cowPos[i]-1);
			}
		}
		
		//finding how many different infected groups there are, which equals the minimal number of zero patients
		int groupCounter=0;
		int[] marker = new int[numCows];
		for(int cowNo=0;cowNo<numCows;cowNo++) {
			if(marker[cowNo]!=0 || !cowInfected[cowNo])
				continue;
			if(cowNo==0 || marker[cowNo-1]!=groupCounter || cowPos[cowNo]-cowPos[cowNo-1]>maxR) 
				groupCounter++;

			marker[cowNo]=groupCounter;
			
		}
		out.println(groupCounter);
		f.close();
		out.close();
		
	}
	
}
