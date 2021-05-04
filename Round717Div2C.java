import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Round717Div2C {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		// check bipartition
		boolean bipartition=true;
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total+=arr[i];
		}
		if(total%2==1)
			bipartition=false;
		
		if(bipartition) {
			boolean[] dp = new boolean[total/2+1];
			dp[0]=true;
			for (int i = 0; i < n; i++) {
				for (int j = dp.length-1; j >= arr[i]; j--) {
					dp[j]|=dp[j-arr[i]];
				}
			}
			bipartition=dp[total/2];
		}
		if(!bipartition) {
			System.out.println(0);
			return;
		}
		
		int chosen = -1;
		do{
			for (int i = 0; i < arr.length; i++) {
				if(arr[i]%2==1)
					chosen=i;
			}
			for (int i = 0; i < arr.length; i++) {
				arr[i]/=2;
			}
		}while(chosen==-1);
		System.out.println(1);
		System.out.println(chosen+1);
	}
}
