import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class convention2 {
	static class Cow implements Comparable<Cow>{
		int arriveT, duration, priority;
		public Cow(int arr, int dur, int prior) {
			arriveT=arr;
			duration=dur;
			priority=prior;
		}
		public int compareTo(Cow other) {
			if(arriveT==other.arriveT)
				return priority-other.priority;
			return arriveT-other.arriveT;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("convention2.in")); //new FileReader("convention.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		int numCow = Integer.parseInt(f.readLine());
		Cow[] allCows = new Cow[numCow];
		StringTokenizer st;
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			int arrT = Integer.parseInt(st.nextToken());
			int dure = Integer.parseInt(st.nextToken());
			allCows[i]=new Cow(arrT,dure,i);
		}
		Arrays.sort(allCows);
		PriorityQueue<Cow> cowInLine = new PriorityQueue<Cow>(new Comparator<Cow>() {
			public int compare(Cow o1, Cow o2) {
				return o1.priority-o2.priority;
			}
		});
		
		int lastFinish=0;
		int maxWaitTime = 0;
		for(int currentCow=0; currentCow<numCow;currentCow++) {
			while(allCows[currentCow].arriveT>lastFinish && !cowInLine.isEmpty()) {
				Cow newGrazer = cowInLine.poll();
				maxWaitTime = Math.max(lastFinish-newGrazer.arriveT,maxWaitTime);
				lastFinish += newGrazer.duration;
			}
			if(cowInLine.isEmpty() && lastFinish<allCows[currentCow].arriveT) 
				lastFinish = allCows[currentCow].arriveT+allCows[currentCow].duration;
			else 
				cowInLine.offer(allCows[currentCow]);
				
		}
		out.println(maxWaitTime);
		f.close();
		out.close();
	}
}
