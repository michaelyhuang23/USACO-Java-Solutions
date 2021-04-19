import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: agrinet
*/
public class agrinet {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		int numFarm = Integer.parseInt(f.readLine());
		int[][] connectivityMap = new int[numFarm][numFarm];
		for(int i=0;i<numFarm;i++) {
			ArrayList<String> totalLines = new ArrayList<String>();
			do {
				String[] newLine = f.readLine().split(" ");
				totalLines.addAll(Arrays.asList(newLine));
			}while(totalLines.size()<numFarm);
			for(int j=0;j<numFarm;j++) {
				connectivityMap[i][j]=Integer.parseInt(totalLines.get(j));
				connectivityMap[j][i]=connectivityMap[i][j];
			}
		}
		
		boolean[] inTree = new boolean[numFarm];
		int[] sourceFarm = new int[numFarm];
		for(int i=0;i<numFarm;i++)
			sourceFarm[i] = -1;
		int[] distance = new int[numFarm];
		for(int i=0;i<numFarm;i++)
			distance[i] = Integer.MAX_VALUE;
		inTree[0]=true;
		distance[0]=0;
		int currentFarm = 0;
		int totalDistance=0;
		for(int i=0;i<numFarm;i++) {
			for(int connectedFarm = 0;connectedFarm<numFarm;connectedFarm++) {
				if(inTree[connectedFarm])
					continue;
				if(connectivityMap[currentFarm][connectedFarm]<distance[connectedFarm]) {
					distance[connectedFarm] = connectivityMap[currentFarm][connectedFarm];
					sourceFarm[connectedFarm] = currentFarm;
				}
			}
			int minDist = Integer.MAX_VALUE-1;
			int minFarm = currentFarm;
			for(int nextFarm = 0; nextFarm<numFarm; nextFarm++) {
				if(inTree[nextFarm])
					continue;
				if(distance[nextFarm]<minDist) {
					minDist = distance[nextFarm];
					minFarm = nextFarm;
				}
			}
			if(minFarm == currentFarm) {
				break;
			}
			inTree[minFarm] = true;
			totalDistance += distance[minFarm];
			
			currentFarm = minFarm;
			
		}
		out.println(totalDistance);
		f.close();
		out.close();
		
	}
}
