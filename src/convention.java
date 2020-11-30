import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class convention {
	static int numCow,numBus,cowLimit;
	static int[] allCowTime;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("convention.in")); //new FileReader("convention.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		numBus = Integer.parseInt(st.nextToken());
		cowLimit = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		allCowTime = new int[numCow];
		for(int i=0;i<numCow;i++) {
			allCowTime[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(allCowTime);
		int maxTimeSpan = allCowTime[numCow-1]-allCowTime[0];
		int leftM=0,rightM=maxTimeSpan;
		int minTime=Integer.MAX_VALUE;
		while(leftM<=rightM) {
			int mid = (leftM+rightM)/2;
			if(check(mid)) {
				minTime=mid;
				rightM=mid-1;
			}else {
				leftM = mid+1;
			}
		}
		//System.out.println(minTime);
		out.println(minTime);
		f.close();
		out.close();
	}
	public static boolean check(int hypoMaxSpan) {
		int lastBusT = allCowTime[0];
		int passengerCount=0;
		int busCount=1;
		for(int currentCow=0;currentCow<numCow;currentCow++) {
			passengerCount++;
			if(allCowTime[currentCow]-lastBusT>hypoMaxSpan || passengerCount>cowLimit) {
				lastBusT=allCowTime[currentCow];
				busCount++;
				passengerCount=1;
			}
		}
		return busCount<=numBus;
	}
}
