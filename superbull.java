import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class superbull {
	static class Vertex implements Comparable<Vertex>{
		int id;
		long dist;
		public Vertex(int i, long d) {
			id = i;
			dist = d;
		}
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(o.dist, dist);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("superbull.in")); //new FileReader("superbull.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		int numPlayer = Integer.parseInt(f.readLine());
		int[] allPlayers = new int[numPlayer];
		for(int i=0;i<numPlayer;i++) 
			allPlayers[i]=Integer.parseInt(f.readLine());
		
		PriorityQueue<Vertex> frontier = new PriorityQueue<Vertex>();
		frontier.offer(new Vertex(0, 0));
		boolean[] inTree = new boolean[numPlayer];
		long[] dist = new long[numPlayer];
		Arrays.fill(dist, Long.MIN_VALUE);
		dist[0]=0;
		long totalDist = 0;
		while(!frontier.isEmpty()) {
			Vertex thisV = frontier.poll();
			if(inTree[thisV.id])
				continue;
			totalDist+=dist[thisV.id];
			dist[thisV.id]=0;
			inTree[thisV.id]=true;
			for(int i=0;i<numPlayer;i++) {
				if(!inTree[i] && (allPlayers[thisV.id]^allPlayers[i])>dist[i]) {
					dist[i]=(allPlayers[thisV.id]^allPlayers[i]);
					frontier.offer(new Vertex(i, dist[i]));
				}
			}
		}

		out.println(totalDist);
		out.close();
	}
	

}
