import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class shortcut {
	static class Edge{
		int to, weight;
		public Edge(int t, int w) {
			to=t;
			weight=w;
		}
	}
	static class Vertex implements Comparable<Vertex>{
		int id, parent;
		long dist;
		public Vertex(int id, long dist) {
			this.id=id;
			this.dist=dist;
		}
		@Override
		public int compareTo(Vertex o) {
			if(dist==o.dist)
				return id-o.id;
			return Long.compare(dist, o.dist);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("shortcut.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		int numField, numConnect, cutLength;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numField = Integer.parseInt(st.nextToken());
		numConnect = Integer.parseInt(st.nextToken());
		cutLength = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] fieldCow = new int[numField];
		for(int i=0;i<numField;i++)
			fieldCow[i] = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] connectors = new ArrayList[numField];
		for(int i=0;i<numField;i++)
			connectors[i] = new ArrayList<Edge>();
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			connectors[first].add(new Edge(second, weight));
			connectors[second].add(new Edge(first, weight));
		}
		Vertex[] allVertex = new Vertex[numField];
		allVertex[0] = new Vertex(0, 0);
		for(int i=1;i<numField;i++)
			allVertex[i]=new Vertex(i,Long.MAX_VALUE-Integer.MAX_VALUE);
		PriorityQueue<Vertex> frontier = new PriorityQueue<Vertex>();
		allVertex[0].parent=-1;
		frontier.offer(allVertex[0]);
		long[] numCowPass = new long[numField];
		for(int i=0;i<numField;i++)
			numCowPass[i]=fieldCow[i];
		int[] numChildren = new int[numField];
		boolean[] visited = new boolean[numField];
		while(!frontier.isEmpty()) {
			Vertex thisV = frontier.poll();
			if(visited[thisV.id])
				continue;
			visited[thisV.id]=true;
			//System.out.println(thisV.dist);
			for(Edge e : connectors[thisV.id]) {	//implementation of this thing is very important and can not be messed up! or there will be a lot of bugs
				if(thisV.dist+e.weight<allVertex[e.to].dist) {
					allVertex[e.to].dist = thisV.dist+e.weight;
					numChildren[allVertex[e.to].parent]--;
					allVertex[e.to].parent = thisV.id;
					numChildren[thisV.id]++;
					frontier.offer(allVertex[e.to]);
				}else if(thisV.dist+e.weight==allVertex[e.to].dist && thisV.id<allVertex[e.to].parent) {
					System.out.println(thisV.id+" "+allVertex[e.to].parent);
					numChildren[allVertex[e.to].parent]--;
					allVertex[e.to].parent = thisV.id;
					numChildren[thisV.id]++;
				}
				
			}
		}
//		for(int i=0;i<numField;i++)
//			System.out.println(allVertex[i].dist+" "+fieldCow[i]);

		boolean[] isChild = new boolean[numField];
		for(int node=1;node<numField;node++) {
			if(numChildren[node]==0)
				isChild[node]=true;
		}
//		System.out.println(numChildren[4993]);
		//System.out.println("Step 2");
		for(int node=0;node<numField;node++) {
			if(!isChild[node])
				continue;
			int curVert = node;
			while(allVertex[curVert].parent>=0) {
				numCowPass[allVertex[curVert].parent]+=numCowPass[curVert];
				//System.out.println(curVert+" "+allVertex[curVert].parent+" "+numCowPass[curVert]+" "+numCowPass[allVertex[curVert].parent]);
				//System.out.println(curVert+" "+allVertex[curVert].parent+" "+numChildren[allVertex[curVert].parent]);
				
				curVert = allVertex[curVert].parent;
				numChildren[curVert]--;

				if(numChildren[curVert]>0)
					break;
			}
			
		}
		//System.out.println("step 3");
		//System.out.println();
		long maxSaveTime = 0;
		for(int i=0;i<numField;i++) {
//			if(numCowPass[i]*(allVertex[i].dist-cutLength)>0)

			maxSaveTime = Math.max(maxSaveTime, numCowPass[i]*(allVertex[i].dist-cutLength));
		}
		//System.out.println("HI");
		System.out.println(maxSaveTime);
		//out.close();
		f.close();
		
		
		
	}
}
