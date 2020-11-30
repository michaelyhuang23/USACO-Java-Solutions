import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1314 {
	static int[] intervalEnd,intervalStart;
	static int[] mineralW,mineralV;
	static int numMineral, numInterv;
	static long deviationStandard;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		numMineral = Integer.parseInt(st.nextToken());
		numInterv = Integer.parseInt(st.nextToken());
		deviationStandard = Long.parseLong(st.nextToken());
		mineralW = new int[numMineral+1];
		mineralV = new int[numMineral+1];
		for(int i=1; i<=numMineral; i++) {
			st = new StringTokenizer(f.readLine());
			mineralW[i] = Integer.parseInt(st.nextToken());
			mineralV[i] = Integer.parseInt(st.nextToken());
		}
		intervalStart = new int[numInterv];
		intervalEnd = new int[numInterv];
		for(int i=0; i<numInterv; i++) {
			st = new StringTokenizer(f.readLine());
			intervalStart[i] = Integer.parseInt(st.nextToken());
			intervalEnd[i] = Integer.parseInt(st.nextToken());
		}
		
		int leftWBound=-1, rightWBound=1000001;
		long prevDiff = Long.MAX_VALUE;
		int midMinW = rightWBound;
		int prevMid = leftWBound;
		while(midMinW!=prevMid) {
			prevMid = midMinW;
			midMinW = (leftWBound+rightWBound)/2;
			long result = getResult(midMinW);
			if(Math.abs(result-deviationStandard)<prevDiff)
				prevDiff = Math.abs(result-deviationStandard);
			if(result<deviationStandard) {	//shift left
				rightWBound = midMinW;
			}else {
				leftWBound = midMinW;
			}
		}
		System.out.println(prevDiff);
		
	}

	private static long getResult(int midMinW) {
		long[] prefixCount = new long[numMineral+1];
		long[] prefixSum = new long[numMineral+1];
		
		for(int i=1;i<=numMineral;i++) {
			prefixCount[i]=prefixCount[i-1];
			prefixSum[i]=prefixSum[i-1];
			if(mineralW[i]>=midMinW) {
				prefixCount[i]++;
				prefixSum[i]+=mineralV[i];
			}
		}
		long totalResult=0;
		for(int i=0;i<numInterv;i++) {
			int start = intervalStart[i];
			int end = intervalEnd[i];
			long result = (prefixCount[end]-prefixCount[start-1])*(prefixSum[end]-prefixSum[start-1]);
			totalResult+=result;
		}
		
		// TODO Auto-generated method stub
		return totalResult;
	}
}
