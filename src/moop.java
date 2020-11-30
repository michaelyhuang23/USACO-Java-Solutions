import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class moop {
	
	static class Particle implements Comparable<Particle>{
		int x, y, indexByY;
		public Particle(int x, int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Particle o) {
			if(x-o.x==0)
				return y-o.y;
			return x-o.x;
		}
		public boolean connectedTo(Particle o) {
			if(x<=o.x && y<=o.y)
				return true;
			return false;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moop.in")); //new FileReader("moop.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
		int numParticle = Integer.parseInt(f.readLine());
		Particle[] allParticles = new Particle[numParticle];
		for(int i=0;i<numParticle;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			allParticles[i] = new Particle(x,y);
		}
		Arrays.sort(allParticles);
		Queue<Particle> verticeToVisit = new ArrayDeque<Particle>();
		for(int i=0;i<numParticle;i++)
			verticeToVisit.offer(allParticles[i]);
		int groupCount=0;
		int currentFurthest=verticeToVisit.peek().x-1;
		while(!verticeToVisit.isEmpty()) {
			Particle newStartParticle = verticeToVisit.poll();
			if(newStartParticle.x>currentFurthest)
				groupCount++;
			//System.out.println(newStartParticle.x+" "+newStartParticle.y);
			int iterations = verticeToVisit.size();
			for(int i=0;i<iterations;i++) {
				Particle thisVertex = verticeToVisit.poll();
				if(!newStartParticle.connectedTo(thisVertex))
					verticeToVisit.offer(thisVertex);
				else {
					currentFurthest=Math.max(currentFurthest, thisVertex.x);
				}
			}
		}
		out.println(groupCount);
		out.close();
		f.close();
	}
}
