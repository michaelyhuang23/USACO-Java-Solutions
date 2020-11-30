import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1090 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numGroup = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		PriorityQueue<Integer> groups = new PriorityQueue<Integer>();
		for(int i=0;i<numGroup;i++)
			groups.add(Integer.parseInt(st.nextToken()));
		int energyCount=0;
		while(groups.size()>1) {
			int newSum = groups.poll()+groups.poll();
			groups.offer(newSum);
			energyCount+=newSum;
		}
		System.out.println(energyCount);
	}
}
