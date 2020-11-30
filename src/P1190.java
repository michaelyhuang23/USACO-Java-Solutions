import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1190 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numPeople = Integer.parseInt(st.nextToken());
		int numHose = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> hoseLine = new PriorityQueue<Integer>();
		for(int i=0;i<numHose;i++)
			hoseLine.offer(0);
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<numPeople;i++) {
			int personT = Integer.parseInt(st.nextToken());
			hoseLine.offer(hoseLine.poll()+personT);
		}
		for(int i=0;i<numHose-1;i++)
			hoseLine.poll();
		System.out.println(hoseLine.poll());
	}
}
