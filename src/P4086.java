import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P4086 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		long[] suffixSum = new long[n+1];
		long bestSum=0, bestCount=1;
		long lowestScore=num[n-1];
		ArrayList<Integer> Ks = new ArrayList<Integer>();
		suffixSum[n-1]=num[n-1];
		for(int k=n-2;k>0;k--) {
			lowestScore = Math.min(lowestScore, num[k]);
			suffixSum[k]=suffixSum[k+1]+num[k];
			if((suffixSum[k]-lowestScore)*bestCount>bestSum*(n-k-1)) {
				bestSum = (suffixSum[k]-lowestScore);
				bestCount = (n-k-1);
				Ks.clear();
				Ks.add(k);
			}else if((suffixSum[k]-lowestScore)*bestCount==bestSum*(n-k-1)) {
				Ks.add(k);
			}
		}
		
		for(int i=Ks.size()-1;i>=0;i--)
			System.out.println(Ks.get(i));

		br.close();
	}

}
