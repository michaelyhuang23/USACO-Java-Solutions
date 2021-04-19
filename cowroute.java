import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowroute {
	static class Edge{
		int toCity, route;
		public Edge(int to, int r) {
			toCity=to;
			route=r;
		}
	}
	static class Point implements Comparable<Point>{
		int city, fromRoute, step;
		long dist;
		public Point(int c, long dist,int s,int r) {
			city =c;
			fromRoute =r;
			this.dist = dist;
			step = s;
		}
		@Override
		public int compareTo(Point o) {
			if(dist==o.dist)
				return step-o.step;
			return Long.compare(dist, o.dist);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowroute.in")); //new FileReader("cowroute.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));
		int origin, target, numRoute;
		StringTokenizer st = new StringTokenizer(f.readLine());
		origin = Integer.parseInt(st.nextToken())-1;
		target = Integer.parseInt(st.nextToken())-1;
		numRoute = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] cityConnector = new ArrayList[1000];
		int[] routeCosts = new int[numRoute];
		for(int i=0;i<1000;i++)
			cityConnector[i] = new ArrayList<Edge>();
		for(int i=0;i<numRoute;i++) {
			st = new StringTokenizer(f.readLine());
			int routeCost = Integer.parseInt(st.nextToken());
			routeCosts[i]=routeCost;
			int numCity = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			int[] cities = new int[numCity];
			for(int j=0;j<numCity;j++) {
				int city = Integer.parseInt(st.nextToken())-1;
				cities[j]=city;
				if(j>0) {
					cityConnector[cities[j-1]].add(new Edge(city, i));
				}
			}	
		}
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		long[][] dist = new long[1000][numRoute];
		int[][] steps = new int[1000][numRoute];
		for(int i=0;i<1000;i++) {
			Arrays.fill(dist[i], Long.MAX_VALUE/2);
			Arrays.fill(steps[i], Integer.MAX_VALUE/2);
		}
		boolean[][] visited = new boolean[1000][numRoute];
		pq.offer(new Point(origin, 0,0,-1));
		int[][] parent = new int[1000][numRoute];
		Arrays.fill(parent[origin],origin);
		Arrays.fill(dist[origin],0);
		Arrays.fill(steps[origin],0);
		while(!pq.isEmpty()) {
				Point node = pq.poll(); // 距离源点距离最短的点
				if(node.fromRoute==-1) {
					Arrays.fill(visited[node.city],true);
				}else {
					if(visited[node.city][node.fromRoute]) continue;
					visited[node.city][node.fromRoute] = true;
				}
				if(node.city==target) {
					out.println(dist[target][node.fromRoute]+" "+steps[target][node.fromRoute]);
//					for(int t=target; t!=origin; t=parent[t])
//						System.out.println(t+" "+dist[t]+" "+steps[t]);
//					System.out.println(origin+" "+dist[origin]+" "+steps[origin]);
					out.close();
					return;
				}
					
				for(Edge edge : cityConnector[node.city]) {
//					if(node.city==23 || edge.toCity==23)
//						System.out.println(node.city+" "+edge.toCity+" "+edge.route+" "+node.fromRoute+" "+parent[node.city]);
					long cost = 0;
					if(edge.route!=node.fromRoute)
						cost+=routeCosts[edge.route];
					long distToNode;
					int stepToNode;
					if(node.fromRoute<0) {
						distToNode=0;
						stepToNode=0;
					}
					else {
						distToNode = dist[node.city][node.fromRoute];
						stepToNode = steps[node.city][node.fromRoute];
					}
					if(dist[edge.toCity][edge.route] > distToNode + cost) {
						dist[edge.toCity][edge.route] = distToNode + cost;
						steps[edge.toCity][edge.route]=stepToNode + 1;
						//System.out.println(edge.toCity+" "+dist[edge.toCity]+" "+edge.route);
						parent[edge.toCity][edge.route]=node.city;
						pq.offer(new Point(edge.toCity, dist[edge.toCity][edge.route],node.step+1, edge.route));	//we must offer a new Point for each update of a vertex 
					}
					else if(dist[edge.toCity][edge.route] == distToNode + cost && steps[edge.toCity][edge.route]>stepToNode + 1){
						steps[edge.toCity][edge.route]=stepToNode + 1;
						//System.out.println(edge.toCity+" "+dist[edge.toCity]+" "+edge.route);
						parent[edge.toCity][edge.route]=node.city;
						pq.offer(new Point(edge.toCity, dist[edge.toCity][edge.route],node.step+1, edge.route));
					}
				}
		}
		out.println("-1 -1");
		out.close();
	}
}
