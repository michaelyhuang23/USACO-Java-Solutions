import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rblock {
	static int numVert, numEdge;
	static int[][] connections;
	static int[] dist;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("rblock.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numVert = Integer.parseInt(st.nextToken());
		numEdge = Integer.parseInt(st.nextToken());
		connections = new int[numVert][numVert];
		for(int i=0;i<numVert;i++)
			Arrays.fill(connections[i], Integer.MAX_VALUE/2);
		for(int i=0;i<numEdge;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int length = Integer.parseInt(st.nextToken());
			connections[first][second] = length;
			connections[second][first] = length;
		}
		
		long initDist = Dijkstra();
		ArrayList<Integer> path = new ArrayList<>();
		for(int current = numVert-1; current>0; current = parent[current])
			path.add(current);
		path.add(0);
		long longestDist = 0;
		for(int vertI=0;vertI<path.size()-1;vertI++) {
			connections[path.get(vertI)][path.get(vertI+1)]*=2;
			connections[path.get(vertI+1)][path.get(vertI)]*=2;
			longestDist = Math.max(Dijkstra(),longestDist);
			connections[path.get(vertI)][path.get(vertI+1)]/=2;
			connections[path.get(vertI+1)][path.get(vertI)]/=2;
		}
		System.out.println(longestDist-initDist);
		
	}
	static boolean[] visited;
	private static long Dijkstra() {
		visited = new boolean[numVert];
		dist = new int[numVert];
		parent = new int[numVert];
		Arrays.fill(dist, Integer.MAX_VALUE/2);
		dist[0]=0;
		for(int i=0; i<numVert-1; i++) {		//loop until all vertices are relieved
			int min = Integer.MAX_VALUE;
			int k = 0;
			for(int j=0; j<numVert; j++) {
				if(!visited[j] && dist[j] < min) {		//finding next closest vertex not yet visited
					min = dist[j];
					k = j;
				}
			}
			
			visited[k] = true;	//visit that vertex
			for(int j=0; j<numVert; j++) {
				if(!visited[j] && connections[k][j] < Integer.MAX_VALUE/2) {	//relief edges from that vertex
					if(dist[k] + connections[k][j] < dist[j]) {
						dist[j] = dist[k] + connections[k][j];
						parent[j] = k;
					}
				}
			}
		}
		return dist[numVert-1];
	}
}
