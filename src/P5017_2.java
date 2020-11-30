import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P5017_2 {
	static int numPeople;
	static int tripTime;
	static int[] timeArrive;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(f.readLine());
		numPeople = Integer.parseInt(st.nextToken());
		tripTime = Integer.parseInt(st.nextToken());
		timeArrive = new int[numPeople+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numPeople;i++) 
			timeArrive[i]=Integer.parseInt(st.nextToken());
		Arrays.sort(timeArrive,1,numPeople+1);
		dp = new int[numPeople+1][tripTime*2+1];
		for(int i=0;i<=numPeople;i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE/2);

		System.out.println(search(0,0));
	}
	private static int search(int person, int thisWaitT) {
		if(person == numPeople)
			return 0;
		if(dp[person][thisWaitT]<Integer.MAX_VALUE/2)
			return dp[person][thisWaitT];
		int thisCarArrT = thisWaitT+timeArrive[person]+tripTime;
		if(person==0)
			thisCarArrT=0;
		int sumArrive = 0;
		int moreP=0;
		for(moreP=person+1;moreP<=numPeople;moreP++) {
			if(timeArrive[moreP]<=thisCarArrT) {
				sumArrive+=timeArrive[moreP];
			}
			else
				break;
		}
		moreP--;
		
		int minTotal=Integer.MAX_VALUE/2;
		if(moreP>person)
			minTotal= search(moreP,thisCarArrT-timeArrive[moreP])+thisCarArrT*(moreP-person)-sumArrive;
		for(int extraP = moreP+1; extraP<=numPeople; extraP++) {
			if(timeArrive[extraP]-timeArrive[person+1]>2*tripTime)
				break;
			sumArrive+=timeArrive[extraP];
			int newTotal = timeArrive[extraP]*(extraP-person)-sumArrive;
			minTotal = Math.min(minTotal, search(extraP, 0) + newTotal);
		}
		dp[person][thisWaitT] = minTotal;
		return minTotal;
	}
}
