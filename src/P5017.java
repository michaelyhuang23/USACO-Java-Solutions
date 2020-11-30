import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P5017 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numPeople = Integer.parseInt(st.nextToken());
		int tripTime = Integer.parseInt(st.nextToken());
		int[] timeArrive = new int[numPeople+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numPeople;i++) 
			timeArrive[i]=Integer.parseInt(st.nextToken());
		Arrays.sort(timeArrive,1,numPeople+1);
		int[][] dp = new int[numPeople+1][2*tripTime+1];
		for(int i=0;i<=numPeople;i++)
			Arrays.fill(dp[i],Integer.MAX_VALUE/2);
		for(int wt = 0; wt<=2*tripTime; wt++)
			dp[1][wt]=wt;
		
		for(int person = 2; person<=numPeople; person++) {
			for(int lastWaitT = 0; lastWaitT<=2*tripTime; lastWaitT++) {
				if(dp[person-1][lastWaitT]>=Integer.MAX_VALUE/2)
					continue;
				int carArrTime = timeArrive[person-1]+lastWaitT;
				int thisWaitT = timeArrive[person-1]+lastWaitT-timeArrive[person];
				if(thisWaitT>=0)
					dp[person][thisWaitT] = Math.min(dp[person][thisWaitT], dp[person-1][lastWaitT]+thisWaitT);
				else if(timeArrive[person]<carArrTime+tripTime) 
					for(int carT=0;carT+tripTime+carArrTime-timeArrive[person]<=2*tripTime;carT++) {
						thisWaitT = carT+tripTime+carArrTime-timeArrive[person];
						dp[person][thisWaitT] = Math.min(dp[person][thisWaitT], dp[person-1][lastWaitT]+thisWaitT);

					}
				else if(carArrTime+tripTime<=timeArrive[person])
					for(int perT=0;perT<=tripTime;perT++) {
						thisWaitT = perT;
						dp[person][thisWaitT] = Math.min(dp[person][thisWaitT], dp[person-1][lastWaitT]+thisWaitT);
					}
			}
		}
		int minT = Integer.MAX_VALUE;
		for(int wt=0;wt<=2*tripTime;wt++)
			minT = Math.min(minT, dp[numPeople][wt]);
		System.out.println(minT);
		
	}
}
