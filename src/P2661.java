import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2661 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numPeople = Integer.parseInt(f.readLine());
		int[] connections = new int[numPeople];
		int[] inDeg = new int[numPeople];
		boolean[] visited = new boolean[numPeople];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0;i<numPeople;i++) {
			connections[i]=Integer.parseInt(st.nextToken())-1;
			inDeg[connections[i]]++;
		}
		Queue<Integer> topo = new ArrayDeque<>();
		for(int i=0;i<numPeople;i++)
			if(inDeg[i]==0)
				topo.offer(i);

		while(!topo.isEmpty()) {
			int newPerson = topo.poll();
			inDeg[connections[newPerson]]--;
			visited[newPerson]=true;
			if(inDeg[connections[newPerson]]==0)
				topo.offer(connections[newPerson]);
		}

		int minCycle = Integer.MAX_VALUE;
		for(int i=0;i<numPeople;i++)
			if(inDeg[i]>0 && !visited[i]) {
				int counter=0;
				for(int j=i;!visited[j];j=connections[j]) {
					counter++;
					visited[j]=true;
				}
				minCycle = Math.min(counter, minCycle);
			}
		
		System.out.println(minCycle);
				
		
		
	}
}
