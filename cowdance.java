import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowdance {
	static int[] cowTime;
	static int numCow, timeLimit;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowdance.in")); //new FileReader("cowdance.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		StringTokenizer st =new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		timeLimit = Integer.parseInt(st.nextToken());
		cowTime = new int[numCow];
		for(int i=0;i<numCow;i++)
			cowTime[i] = Integer.parseInt(f.readLine());
		int leftSBound=1,rightSBound=numCow;
		int currentMinBound=1;
		while(leftSBound<=rightSBound) {
			int midSBound = (leftSBound+rightSBound)/2;
			if(check(midSBound)) {currentMinBound=midSBound; rightSBound=midSBound-1;}
			else leftSBound=midSBound+1;
		}
		out.println(currentMinBound);
		f.close();
		out.close();
	}
	
	private static boolean check(int stageSize) {
		PriorityQueue<Integer> finishTime = new PriorityQueue<Integer>();
		for(int i=0;i<stageSize;i++)
			finishTime.offer(cowTime[i]);
		for(int i=stageSize; i<numCow;i++) {
			finishTime.offer(finishTime.poll()+cowTime[i]);
		}
		for(int i=0;i<stageSize-1;i++)
			finishTime.poll();
		return  finishTime.poll()<=timeLimit;
	}
	
}
