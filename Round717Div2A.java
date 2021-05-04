import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round717Div2A {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < n-1; j++) {
				if(k>arr[j]) {
					k-=arr[j];
					arr[n-1]+=arr[j];
					arr[j]=0;
				}else {
					arr[j]-=k;
					arr[n-1]+=k;
					k=0;
				}
			}
			for (int j = 0; j < n-1; j++) {
				System.out.print(arr[j]+" ");
			}
			System.out.println(arr[n-1]);
			
		}
	}
}
