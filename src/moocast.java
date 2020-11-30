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

public class moocast {
	static class Cow{
		int x,y, power;
		public Cow(int xI, int yI, int pI) {
			x=xI;
			y=yI;
			power=pI;
		}
		public int distanceSquare(Cow o) {
			return (x-o.x)*(x-o.x)+(y-o.y)*(y-o.y);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moocast.in")); //new FileReader("moocast.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int numCow = Integer.parseInt(f.readLine());
		Cow[] allCows = new Cow[numCow];
		ArrayList<Integer>[] allConnect = new ArrayList[numCow];
		for(int i=0;i<numCow;i++)
			allConnect[i]=new ArrayList<Integer>();
		for(int i=0;i<numCow;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			allCows[i]=new Cow(x,y,p);
			for(int j=0;j<i;j++) {
				int distSquare = allCows[i].distanceSquare(allCows[j]);
				if(distSquare<=allCows[i].power*allCows[i].power)
					allConnect[i].add(j);
				if(distSquare<=allCows[j].power*allCows[j].power)
					allConnect[j].add(i);
			}
		}
		int maxReach=0;
		for(int start=0;start<numCow;start++) {
			boolean[] visited = new boolean[numCow];
			Queue<Integer> frontier = new ArrayDeque<Integer>();
			frontier.offer(start);
			int counter=0;
			visited[start]=true;
			while(!frontier.isEmpty()) {
				counter++;
				int currentCow = frontier.poll();
				for(int otherCow:allConnect[currentCow]) {
					if(visited[otherCow])
						continue;
					visited[otherCow]=true;
					frontier.offer(otherCow);
					
				}
			}
			maxReach = Math.max(maxReach, counter);
		}
		out.println(maxReach);
		out.close();
		f.close();
	}
}
