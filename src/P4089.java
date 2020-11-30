import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4089 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCow = Integer.parseInt(f.readLine());
		int[] shuffle = new int[numCow];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0;i<numCow;i++) {
			shuffle[i]=Integer.parseInt(st.nextToken())-1;
		}
		int[] indeg = new int[numCow];
		for(int i=0;i<numCow;i++) {
			indeg[shuffle[i]]++;
		}
		Queue<Integer> topoStart = new ArrayDeque<>();
		for(int i=0;i<numCow;i++)
			if(indeg[i]==0)
				topoStart.offer(i);
		
		while(!topoStart.isEmpty()) {
			int newNode = topoStart.poll();
			indeg[shuffle[newNode]]--;
			if(indeg[shuffle[newNode]]==0)
				topoStart.offer(shuffle[newNode]);
		}
		int counter=0;
		for(int i=0;i<numCow;i++)
			if(indeg[i]>0)
				counter++;
		System.out.println(counter);
	}
}
