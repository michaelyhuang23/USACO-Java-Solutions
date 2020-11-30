import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class clocktree {
	static LinkedList<Integer>[] roomConnect;
	static int[] roomVisitNeed;
	static int numRoom;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("clocktree.in")); //new FileReader("clocktree.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
		numRoom = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		roomVisitNeed = new int[numRoom];
		for(int i=0;i<numRoom;i++)
			roomVisitNeed[i] = 12 - Integer.parseInt(st.nextToken());
		int[] visitNeedCopy = roomVisitNeed.clone();
		roomConnect = new LinkedList[numRoom];
		for(int i=0;i<numRoom;i++)
			roomConnect[i] = new LinkedList<Integer>();
		for(int i=0;i<numRoom-1;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			roomConnect[first].add(second);
			roomConnect[second].add(first);
		}
		int posCount=0;
		for(int i=0;i<numRoom;i++) {
			visited = new boolean[numRoom];
			roomVisitNeed=visitNeedCopy.clone();
			roomVisitNeed[i]++;
			traverseRoom(i);
			if(roomVisitNeed[i]%12==0 || (roomVisitNeed[i]+1)%12==0)
				posCount++;
		}
		out.println(posCount);
		out.close();
		f.close();
	}
	static boolean work = true;
	static boolean[] visited;
	
	static void traverseRoom(int root) { 
		roomVisitNeed[root]--;
		if(roomVisitNeed[root]<0)
			roomVisitNeed[root]+=12;
		
		visited[root]=true;
		for(int branch : roomConnect[root]) {
			if(visited[branch])
				continue;
			traverseRoom(branch);
			roomVisitNeed[root]-=roomVisitNeed[branch]+1;
			roomVisitNeed[branch]=0;
			if(roomVisitNeed[root]<0)
				roomVisitNeed[root]+=12;
			
		}
	}
}
