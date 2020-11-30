
import java.io.*;
import java.util.*;

public class P2921_TODO2 {
	static int[] answers;
	static int[] next;
	static int[] inDegree; // 有几头牛指向第i个房间
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		next = new int[n+1];
		inDegree = new int[n+1];
		answers = new int[n+1];
		for(int i=1;i<=n;i++) {
			next[i]=Integer.parseInt(in.readLine());
			inDegree[next[i]]++;
		}

		
		visited = new boolean[n+1];
		Queue<Integer> topo = new ArrayDeque<>();
		for(int i=1; i<=n; i++) {
			if(inDegree[i]==0 && !visited[i]) {
				topo.offer(i);
			}
		}
		
		while(!topo.isEmpty()) {
			int newStall = topo.poll();
			visited[newStall]=true;
			inDegree[next[newStall]]--;
			if(inDegree[next[newStall]]==0)
				topo.offer(next[newStall]);
		}
		
		// 统计每个环的大小
		for(int i=1; i<=n; i++) {
			if(!visited[i]) {
				int count=0;
				for(int j=i;!visited[j];j=next[j]) {
					visited[j]=true;
					count++;
				}
				int current = i;
				for(int k=0;k<count;k++) {
					answers[current]=count;
					current = next[current];
				}
			}
		}
		
		//points outside of loop
		for(int i=1; i<=n; i++) {
			if(inDegree[i]==0 && answers[i]==0) { //not in loops
				toLoop(i);
			}
		}
		
		//print answers
		for(int i=1; i<=n; i++) {
			System.out.println(answers[i]);
		}
	}
	
	public static void toLoop(int start) {
		Stack<Integer> to = new Stack<>();
		to.push(start);
		int currentLen=0;
		while(currentLen==0) {
			int newNode = to.peek();
			if(answers[newNode]>0)
				currentLen=answers[newNode];
			else {
				to.push(next[newNode]);
			}
		}
		while(!to.isEmpty()) {
			answers[to.pop()]=currentLen;
			currentLen++;
		}
	}


	
}