import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class piggyback {
	static int barn;
	static int farm1=0,farm2=1;
	static ArrayList<Integer>[] connections;
	static int[][] allDists;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("piggyback.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOSilverPracticeGraph/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int cow1Cost = Integer.parseInt(st.nextToken()), cow2Cost = Integer.parseInt(st.nextToken()), cowCombCost = Integer.parseInt(st.nextToken());
		int numFarm = Integer.parseInt(st.nextToken()), numConnect = Integer.parseInt(st.nextToken());
		connections = new ArrayList[numFarm];
		allDists=new int[3][numFarm];
		for(int i=0;i<numFarm;i++) {
			allDists[0][i]=-1;
			allDists[1][i]=-1;
			allDists[2][i]=-1;
		}
		for(int i=0;i<numFarm;i++)
			connections[i]=new ArrayList<Integer>();
		barn = numFarm-1;
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int firstFarm = Integer.parseInt(st.nextToken())-1;
			int secondFarm = Integer.parseInt(st.nextToken())-1;
			connections[firstFarm].add(secondFarm);
			connections[secondFarm].add(firstFarm);
		}
		bfs(0,farm1);
		bfs(1,farm2);
		bfs(2,barn);

		int minCost = Integer.MAX_VALUE;
		for(int interFarm=0;interFarm<numFarm;interFarm++) {
			minCost = Math.min(minCost, cow1Cost*allDists[0][interFarm]+cow2Cost*allDists[1][interFarm]+cowCombCost*allDists[2][interFarm]);
		}
		out.println(minCost);
		f.close();
		out.close();
	}
	public static void bfs(int whichOne,int startNode) {
		Queue<Integer> toBeVisited = new ArrayDeque<>();
		toBeVisited.offer(startNode);
		allDists[whichOne][startNode]=0;
		while(!toBeVisited.isEmpty()) {
			int node = toBeVisited.poll();
			for(int connector : connections[node]) {
				if(allDists[whichOne][connector]>=0)
					continue;
				toBeVisited.offer(connector);
				allDists[whichOne][connector]=allDists[whichOne][node]+1;
			}
		}
	}
}
