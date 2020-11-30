import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class meeting {
	static class Edge{
		int to, cost;
		public Edge(int t, int c) {
			to=t;
			cost=c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("meeting.in")); //new FileReader("meeting.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
		int numFarm, numConnect;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numFarm = Integer.parseInt(st.nextToken());
		numConnect = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] BconnectionFrom = new ArrayList[numFarm];
		ArrayList<Edge>[] EconnectionFrom = new ArrayList[numFarm];

		for(int i=0;i<numFarm;i++) {
			BconnectionFrom[i]=new ArrayList<Edge>();
			EconnectionFrom[i]=new ArrayList<Edge>();
		}
		int maxBCost = 0, maxECost = 0;
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int BCost = Integer.parseInt(st.nextToken());
			int ECost = Integer.parseInt(st.nextToken());
			BconnectionFrom[first].add(new Edge(second, BCost));
			EconnectionFrom[first].add(new Edge(second, ECost));
			maxBCost = Math.max(BCost, maxBCost);
			maxECost = Math.max(ECost, maxECost);
		}
		
		
		boolean[][] BReached = new boolean[numFarm][(numFarm-1)*maxBCost+1];
		boolean[][] EReached = new boolean[numFarm][(numFarm-1)*maxECost+1];
		BReached[0][0]=true;
		EReached[0][0]=true;
		for(int onFarm=0;onFarm<numFarm;onFarm++) {
			for(int dist=0; dist<BReached[onFarm].length;dist++) {
				if(!BReached[onFarm][dist])
					continue;
				for(Edge e : BconnectionFrom[onFarm]) {
					int newDist = dist + e.cost;
					BReached[e.to][newDist]=true;
				}
			}
		}
		for(int onFarm=0;onFarm<numFarm;onFarm++) {
			for(int dist=0; dist<EReached[onFarm].length;dist++) {
				if(!EReached[onFarm][dist])
					continue;
				for(Edge e : EconnectionFrom[onFarm]) {
					int newDist = dist + e.cost;
					EReached[e.to][newDist]=true;
				}
			}
		}
		for(int dist=0;dist<Math.min(BReached[0].length, EReached[0].length);dist++) {
			if(BReached[numFarm-1][dist] && EReached[numFarm-1][dist]) {
				out.println(dist);
				out.close();
				return;
			}
		}
		out.println("IMPOSSIBLE");
		out.close();
	}
}
