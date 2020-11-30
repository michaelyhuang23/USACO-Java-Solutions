import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class P5122 {
	static class Edge{
		int to, weight;
		public Edge(int t, int w) {
			to=t;
			weight=w;
		}
	}
	static class Vertex implements Comparable<Vertex>{
		int id;
		int dist;
		public Vertex(int id, int dist) {
			this.id=id;
			this.dist=dist;
		}
		@Override
		public int compareTo(Vertex o) {
			if(dist==o.dist)
				return id-o.id;
			return dist-o.dist;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numFarm = Integer.parseInt(st.nextToken());
		int numRoad = Integer.parseInt(st.nextToken());
		int numFood = Integer.parseInt(st.nextToken());
		Vertex[] allFarms = new Vertex[numFarm+1];
		ArrayList<Edge>[] connector = new ArrayList[numFarm];
		for(int i=0;i<numFarm;i++) {
			allFarms[i] = new Vertex(i,Integer.MAX_VALUE/2);
			connector[i] = new ArrayList<>();
		}
		
		allFarms[numFarm-1].dist=0;
		for(int i=0;i<numRoad;i++) {
			st = new StringTokenizer(f.readLine());
			int firstI = Integer.parseInt(st.nextToken())-1;
			int secondI = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			connector[firstI].add(new Edge(secondI, weight));
			connector[secondI].add(new Edge(firstI, weight));
		}
		PriorityQueue<Vertex> frontier = new PriorityQueue<Vertex>();
		frontier.offer(allFarms[numFarm-1]);
		boolean[] visited = new boolean[numFarm];
		int[] firstDist = new int[numFarm];
		while(!frontier.isEmpty()) {
			Vertex thisV = frontier.poll();
			if(visited[thisV.id])
				continue;
			visited[thisV.id]=true;
			for(Edge e : connector[thisV.id]) {	
				if(thisV.dist+e.weight<allFarms[e.to].dist) {
					allFarms[e.to].dist = thisV.dist+e.weight;
					firstDist[e.to]=allFarms[e.to].dist;
					frontier.offer(allFarms[e.to]);
				}				
			}
		}
		for(int i=0;i<numFarm;i++)
			allFarms[i].dist=Integer.MAX_VALUE/2;
		allFarms[numFarm] = new Vertex(numFarm, 0);
		ArrayList<Edge>[] newConnector = new ArrayList[numFarm+1];
		for(int i=0;i<numFarm;i++) {
			newConnector[i] = new ArrayList<>();
			for(Edge e : connector[i]) {
				newConnector[i].add(e);
			}
		}
		newConnector[numFarm]=new ArrayList<>();
		for(int i=0;i<numFood;i++) {
			st = new StringTokenizer(f.readLine());
			int id = Integer.parseInt(st.nextToken())-1;
			int delic = Integer.parseInt(st.nextToken());
			newConnector[numFarm].add(new Edge(id,firstDist[id]-delic));
		}
		
		frontier.offer(allFarms[numFarm]);
		visited = new boolean[numFarm+1];

		while(!frontier.isEmpty()) {
			Vertex thisV = frontier.poll();
			if(visited[thisV.id])
				continue;
			visited[thisV.id]=true;
			for(Edge e : newConnector[thisV.id]) {	
				if(thisV.dist+e.weight<allFarms[e.to].dist) {
					allFarms[e.to].dist = thisV.dist+e.weight;
					frontier.offer(allFarms[e.to]);
				}				
			}
		}
		
		for(int i=0;i<numFarm-1;i++) {
			//System.out.println(allFarms[i].dist +" "+firstDist[i]);
			if(allFarms[i].dist<=firstDist[i])
				System.out.println(1);
			else
				System.out.println(0);
		}
	}
}
