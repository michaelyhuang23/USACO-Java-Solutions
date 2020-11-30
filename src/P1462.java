import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1462 {
	static class Edge {
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	static int numCity, numRoad, numHP;
	static int[] cityCost;
	static ArrayList<Edge>[] connector;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCity = Integer.parseInt(st.nextToken());
		numRoad = Integer.parseInt(st.nextToken());
		numHP = Integer.parseInt(st.nextToken());
		cityCost = new int[numCity];
		connector = new ArrayList[numCity];
		int maxCost = 0;
		for(int i=0;i<numCity;i++) {
			cityCost[i] = Integer.parseInt(f.readLine());
			maxCost = Math.max(maxCost, cityCost[i]);
			connector[i] = new ArrayList<>();
		}
		for(int i=0;i<numRoad;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int hpCost = Integer.parseInt(st.nextToken());
			connector[first].add(new Edge(second, hpCost));
			connector[second].add(new Edge(first, hpCost));
		}
		
		int leftCostBound = 1, rightCostBound = maxCost;
		int curMinCost = maxCost;
		while(leftCostBound<=rightCostBound) {
			int midCost = (leftCostBound+rightCostBound)/2;
			if(check(midCost)) {curMinCost=midCost; rightCostBound = midCost-1;}
			else leftCostBound = midCost+1;
		}
		if(check(curMinCost))
			System.out.println(curMinCost);
		else
			System.out.println("AFK");
	}
	private static boolean check(int midCost) {
		int[] dist = new int[numCity];
		Arrays.fill(dist, Integer.MAX_VALUE/2);
		dist[0]=0;
		Queue<Integer> frontier = new ArrayDeque<>();
		frontier.offer(0);
		boolean[] inQueue = new boolean[numCity];
		inQueue[0]=true;
		while(!frontier.isEmpty()) {
			int city = frontier.poll();
			inQueue[city]=false;
			for(Edge e : connector[city]) {
				if(cityCost[e.to]>midCost)
					continue;
				if(dist[e.to]>dist[city]+e.w) {
					dist[e.to] = dist[city]+e.w;
					if(!inQueue[e.to])
						frontier.offer(e.to);
				}
			}
		}
		return dist[numCity-1]<=numHP;
	}
}
