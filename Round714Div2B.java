import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round714Div2B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		long MOD = (long) (1e9 + 7);
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				arr[j]= Integer.parseInt(st.nextToken());
			}
			int min = arr[0];
			for (int j = 0; j < n; j++) {
				min &= arr[j];
			}
			long minCount = 0;
			for (int j = 0; j < n; j++) {
				if(arr[j]==min)
					minCount++;
			}
			long bChoice = minCount*(minCount-1);
			bChoice%=MOD;
			long iChoice = factorial(n-2,MOD);
			long totalChoice = iChoice*bChoice;
			totalChoice%=MOD;
			System.out.println(totalChoice);
		}
	}

	private static long factorial(long num, long MOD) {
		long fac = 1;
		for (int i = 1; i <= num; i++) {
			fac*=i;
			fac%=MOD;
		}
		return fac;
	}
}
