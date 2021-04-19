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
TASK: comehome
*/
class EdgePair{
	public int edgeIndex,length;
	public EdgePair(int edgeIndex, int length) {
		this.edgeIndex=edgeIndex;
		this.length=length;
	}
	public boolean equals(Object other) {
		EdgePair otherP = (EdgePair)other;
		return this.edgeIndex==otherP.edgeIndex && this.length==otherP.length;
	}
	public String toString() {
		return edgeIndex+" "+length;
	}
}
public class comehome {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter2Section2.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		

		LinkedList<EdgePair>[] vertexConnect = new LinkedList[52]; // lower first and upper later
		for(int i=0;i<52;i++)
			vertexConnect[i]=new LinkedList<EdgePair>();
		int numPath = Integer.parseInt(f.readLine());
		for(int i=0;i<numPath;i++) {
			String[] inputs = f.readLine().split(" ",3);
			int firstPast = Character.getNumericValue(inputs[0].charAt(0))-10;
			if(Character.isUpperCase(inputs[0].charAt(0)))
				firstPast+=26;
			int secondPast = Character.getNumericValue(inputs[1].charAt(0))-10;
			if(Character.isUpperCase(inputs[1].charAt(0)))
				secondPast+=26;
			int length = Integer.parseInt(inputs[2]);
			vertexConnect[firstPast].add(new EdgePair(secondPast,length));
			vertexConnect[secondPast].add(new EdgePair(firstPast,length));
		}
		int[] shortestDist = new int[52];
		boolean[] visited = new boolean[52];
		for(int i=0;i<51;i++)
				shortestDist[i]=Integer.MAX_VALUE;
		shortestDist[51]=0;
		int pastIndex=51;
		while(true) {
			visited[pastIndex]=true;
			for(EdgePair edge : vertexConnect[pastIndex]) {
				if(visited[edge.edgeIndex])
					continue;
				shortestDist[edge.edgeIndex]=Math.min(shortestDist[edge.edgeIndex], shortestDist[pastIndex]+edge.length);
			}
			int minDist=Integer.MAX_VALUE-1;
			int minIndex=pastIndex;
			for(int i=0;i<52;i++) {
				if(!visited[i] && shortestDist[i]<minDist) {
					minDist=shortestDist[i];
					minIndex=i;
				}
			}
			if(minIndex==pastIndex)
				break;
			pastIndex=minIndex;
		}
		int minDist=Integer.MAX_VALUE-1;
		int minIndex=0;
		for(int i=26;i<51;i++) {
			if(shortestDist[i]<minDist) {
				minDist=shortestDist[i];
				minIndex=i;
			}
		}

		out.println((char)(minIndex+(int)('A')-26)+" "+minDist);
		out.close();
		f.close();
	}
}
