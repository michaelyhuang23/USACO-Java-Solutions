import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1525 {
	static int numP, numConnect;
	static ArrayList<Edge>[] connectors;
	static class Edge{
		int otherP, weight;
		public Edge(int o, int w) {
			otherP = o;
			weight = w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numP = Integer.parseInt(st.nextToken());
		numConnect = Integer.parseInt(st.nextToken());
		connectors = new ArrayList[numP];
		for(int i=0;i<numP;i++)
			connectors[i]=new ArrayList<Edge>();
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			connectors[first].add(new Edge(second, weight));
			connectors[second].add(new Edge(first, weight));
		}
		long leftWBound=0, rightWBound=1000000000;
		int ansW=0;
		
		while(leftWBound<=rightWBound) {
			long midWL = ((leftWBound+rightWBound)/2);
			int midW = (int)midWL;
			if(check(midW)) {ansW=midW; rightWBound=midW-1;}
			else {leftWBound=midW+1;}
			
		}
		System.out.println(ansW);
		
	}
	static int[] color;
	private static boolean check(int midW) {
		color = new int[numP];
		for(int i=0;i<numP;i++) {
			if(color[i]!=0)
				continue;
			color[i]=1;
			if(!dfs(i,midW))
				return false;
		}
		
		return true;
	}
	
	private static boolean dfs(int person, int weightLimit) {
		boolean success=true;
		if(connectors[person].size()>0)
			for(Edge e : connectors[person]) {
				if(e.weight>weightLimit) {
					if(color[e.otherP]==color[person])
						return false;
					if(color[e.otherP]!=0)
						continue;
					color[e.otherP]=3-color[person];
					success&=dfs(e.otherP,weightLimit);
				}
			}
		return success;
	}
}
