import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CFRound108Div2D {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		long[] as = new long[n];
		long[] bs = new long[n];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			as[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			bs[i]=Integer.parseInt(st.nextToken());
		}
		long totalSum = 0;
		for (int c = 0; c < n; c++) {
			totalSum+=(long)as[c]*bs[c];
		}
		long max = totalSum;
		for (int c = 0; c <= 2*n-2; c++) {
			if(c%2==0) {
				long reverseSum = totalSum;

				for (int k = 0; k+c/2 < n && c/2-k>=0; k++) {
					reverseSum+=as[k+c/2]*bs[c/2-k]+as[c/2-k]*bs[c/2+k];
					reverseSum-=as[k+c/2]*bs[c/2+k]+as[c/2-k]*bs[c/2-k];
					max = Math.max(max, reverseSum);
				}
			}else {
				long reverseSum = totalSum;

				for (int k = 1; c/2+k < n && c/2+1-k>=0; k++) {
					int right = c/2+k;
					int left = c/2+1-k;
					reverseSum+=as[right]*bs[left]+as[left]*bs[right];
					reverseSum-=as[right]*bs[right]+as[left]*bs[left];
					max = Math.max(max, reverseSum);
				}
			}
		}
		System.out.println(max);
	}
}
