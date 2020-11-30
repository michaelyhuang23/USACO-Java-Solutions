import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
public class P1330 {
	static int[] color;
	static ArrayList<Integer>[] connections;
	static boolean[] visited;
	static int[] counter= new int[3];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numVertex = Integer.parseInt(st.nextToken());
		int numEdge = Integer.parseInt(st.nextToken());
		color= new int[numVertex];
		visited= new boolean[numVertex];
		Arrays.fill(color, 0);
		connections = new ArrayList[numVertex];
		for(int i=0;i<numVertex;i++)
			connections[i] = new ArrayList<Integer>();
		
		for(int i=0;i<numEdge;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			connections[first].add(second);
			connections[second].add(first);
		}
		
		int totalShrimp=0;
		for(int i=0;i<numVertex;i++) {
			if(!visited[i]) {
				color[i]=1;
				counter[1]++;
				if(dfsColor(i)) {
					totalShrimp+=Math.min(counter[1], counter[2]);
				}else {
					System.out.println("Impossible");
					return;
				}
				Arrays.fill(counter, 0);
			}
		}
		System.out.println(totalShrimp);
	}
	public static boolean dfsColor(int start) {
		if(visited[start])
			return true;
		visited[start]=true;
		for(int connect : connections[start]) {
			if(color[connect]==0) {
				color[connect]=3-color[start];
				counter[3-color[start]]++;
			}else if(color[connect]==color[start])
				return false;
			
			if(!dfsColor(connect))
				return false;
				
		}
		return true;
	}
}
